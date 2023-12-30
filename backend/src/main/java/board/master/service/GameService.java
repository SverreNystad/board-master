package board.master.service;

import org.springframework.stereotype.Service;
import java.util.UUID;

import board.master.model.GameStartRequest;
import board.master.model.GameResponse;
import board.master.model.MoveRequest;
import board.master.model.Game;
import board.master.model.games.GameStateHandlerFactory;
import board.master.model.StateHandler;
import board.master.model.agents.Agent;
import board.master.model.agents.AgentFactory;
import board.master.model.Action;

import java.util.HashMap;

@Service
public class GameService {
    /**
     * Map of games and their ids that are currently running
     */
    private HashMap<String, Game> games = new HashMap<>();

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
        
        return new GameResponse(game.getGameId(), game.getStateHandler().isTerminal(), game.getBoard());
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

        return new GameResponse(game.getGameId(), game.getStateHandler().isTerminal(), game.getBoard());

    }

    /**
     * Logic to handle bot's move
     * 
     * @param gameId of the game
     * @return updated game state
     * @throws IllegalArgumentException if the game id is invalid
     */
    public GameResponse botMove(String gameId) throws IllegalArgumentException {
        
        Game game = null;
        if (games.containsKey(gameId)) {
            game = games.get(gameId);
        }
        else {
            throw new IllegalArgumentException("Invalid game id: " + gameId);
        }
        Agent agent = game.getAgent();
        Action action = agent.getAction(game.getStateHandler());
        
        // update game state
        StateHandler transformedGame = game.getStateHandler().result(action);
        game.setStateHandler(transformedGame);

        return new GameResponse(game.getGameId(), game.getStateHandler().isTerminal(), game.getBoard());
    }

    /**
     * Create a unique game id
     */
    private String generateGameId() {
        return UUID.randomUUID().toString();
    }
}
