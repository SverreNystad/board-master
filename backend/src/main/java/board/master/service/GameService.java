package board.master.service;

import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import board.master.model.games.GameStateHandlerFactory;
import board.master.model.StateHandler;
import board.master.model.agents.Agent;
import board.master.model.agents.AgentFactory;
import board.master.model.communication.Game;
import board.master.model.communication.GameResponse;
import board.master.model.communication.GameStartRequest;
import board.master.model.communication.MoveRequest;
import board.master.model.Action;

import java.util.HashMap;

@Service
public class GameService {
    /**
     * Map of games and their ids that are currently running
     */
    private HashMap<String, Game> games = new HashMap<>();

    private ScheduledExecutorService throttlingScheduler = Executors.newScheduledThreadPool(1);
    private ScheduledExecutorService timeScheduler = Executors.newScheduledThreadPool(1);

    /*
     * Time to live for a game in minutes
     */
    private static final long timeToLive = 1;

    /**
     * Logic to handle game creation
     * 
     * This method creates a new game and adds it to the list of running games.
     * It checks that the agent type and game type are valid.
     * 
     * @param request from the player
     * @return The {@link GameResponse} game state and id of the new game
     * @throws IllegalArgumentException if the agent type or game type are invalid
     */
    public GameResponse startGame(GameStartRequest request) throws IllegalArgumentException{
        validateAgentAndGameType(request.getBotType(), request.getGameType());

        Agent agent = AgentFactory.createAgent(request.getBotType());
        StateHandler stateHandler = GameStateHandlerFactory.createGameHandler(request.getGameType());

        Game game = new Game(generateGameId(), agent, stateHandler);
        games.put(game.getGameId(), game);

        // Add task to scheduler to remove game after 1 hour
        timeScheduler.schedule(() -> games.remove(game.getGameId()), 1, java.util.concurrent.TimeUnit.HOURS);

        return new GameResponse(game.getGameId(), getBoardStatus(game.getStateHandler(), true), game.getBoard());
    }

    private void validateAgentAndGameType(String agentType, String gameType) throws IllegalArgumentException {
        if (!AgentFactory.isValidAgentType(agentType)) {
            throw new IllegalArgumentException("Invalid agent type" + agentType);
        }
        if (!GameStateHandlerFactory.isValidGameHandler(gameType)) {
            throw new IllegalArgumentException("Invalid game type" + gameType);
        }
    }

    /**
     * Logic to handle player's move 
     * 
     * It checks that the {@link MoveRequest}s game id is valid and that the move is valid.
     * 
     * 
     * @param request from the player
     * @return updated game state
     * @throws IllegalArgumentException if the game id is invalid
     */
    public GameResponse playerMove(MoveRequest request) throws IllegalArgumentException {
        // Check validity of game id
        Game game = null;

        if (games.containsKey(request.getGameId())) {
            game = games.get(request.getGameId());
        }
        else {
            throw new IllegalArgumentException("Invalid game id: " + request.getGameId());
        }
        // Do move if it is valid
        Action action = request.getMove();
        if (game.getStateHandler().getActions().contains(action)) {
            StateHandler transformedGame = game.getStateHandler().result(action);
            // update game state
            game.setStateHandler(transformedGame);
        }
        else {
            System.out.println("Invalid move");
            throw new IllegalArgumentException("Invalid move" + action.toString());
        }

        return new GameResponse(game.getGameId(), getBoardStatus(game.getStateHandler(), true), game.getBoard());

    }

    /**
     * Logic to handle bot's move
     * 
     * @param gameId of the game
     * @return updated game state
     * @throws IllegalArgumentException if the game id is invalid
     */
    public GameResponse botMove(String gameId) throws IllegalArgumentException, IllegalStateException {
        
        Game game = null;
        if (games.containsKey(gameId)) {
            game = games.get(gameId);
        }
        else {
            throw new IllegalArgumentException("Invalid game id: " + gameId);
        }

        if (game.getStateHandler().isTerminal()) {
            throw new IllegalStateException("Game is over");
        }
        final Game taskGame = game;
        Callable<GameResponse> botMoveTask = () -> {
            // Bot move logic here
            Agent agent = taskGame.getAgent();
            Action action = agent.getAction(taskGame.getStateHandler());
            StateHandler transformedGame = taskGame.getStateHandler().result(action);
            taskGame.setStateHandler(transformedGame);
            
            return new GameResponse(taskGame.getGameId(), getBoardStatus(taskGame.getStateHandler(), false), taskGame.getBoard());
        };
        
        Future<GameResponse> future = throttlingScheduler.submit(botMoveTask);
        try {
            // Wait for the bot move to complete or timeout
            return future.get(timeToLive, TimeUnit.MINUTES);
        } catch (TimeoutException e) {
            // Handle timeout, e.g., by updating the game state or setting a flag
            System.out.println("Error TimeoutException bot move " + e.getMessage());
            // Attempt to cancel the ongoing task
            future.cancel(true); 
            throw new IllegalStateException("Bot move timed out");
        } catch (InterruptedException | ExecutionException e) {
            // Handle other exceptions
            System.out.println("Error InterruptedException bot move " + e.getMessage());
            throw new IllegalStateException("Error executing bot move: ", e.getCause());
        } finally {
            // stop clock
            throttlingScheduler.shutdownNow();
            throttlingScheduler = Executors.newScheduledThreadPool(1);
        }
    }

    private String getBoardStatus(StateHandler stateHandler, boolean player) {
        if(stateHandler.isTerminal()) {
            if (stateHandler.utility(stateHandler.toMove()) >= 1) {
                
                return (player) ? "You won" : "Bot won";
            }
            else if (stateHandler.utility(stateHandler.toMove()) <= -1) {
                return (player) ? "You won" : "Bot won";
            }
            else if (stateHandler.utility(stateHandler.toMove()) == 0) {
                return "Draw";
            }
        }
        
        return "Game in progress";
    }

    /**
     * Create a unique game id
     */
    private String generateGameId() {
        return UUID.randomUUID().toString();
    }
}
