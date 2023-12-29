package board.master.service;

import org.springframework.stereotype.Service;
import java.util.UUID;

import board.master.model.GameStartRequest;
import board.master.model.GameResponse;
import board.master.model.PlayerMoveRequest;
import board.master.model.Move;
import board.master.model.Game;
import board.master.model.games.chess.Chess;
import board.master.model.games.tictactoe.TicTacToe;
import board.master.model.StateHandler;
import board.master.model.agents.Agent;
import board.master.model.agents.AgentFactory;
import board.master.model.Action;

import java.util.HashMap;

@Service
public class GameService {
    
    private HashMap<String, Game> games = new HashMap<>();

    public GameResponse startGame(GameStartRequest request) throws IllegalArgumentException{
        // Logic to start a new game
        switch (request.getGameType()) {
            case "chess":
                try {
                    StateHandler chess = new Chess(Chess.CreateInitialBoard());
                    Agent agent = AgentFactory.createAgent(request.getBotType());
                    Game game = new Game(generateGameId(), agent, chess);
                    games.put(game.getGameId(), game);
                    return new GameResponse(game.getGameId(), "in-progress", game.getBoard());
                }
                catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid start request");
                }
            case "tic-tac-toe":
                try {
                    StateHandler ticTacToe = new TicTacToe();
                    Agent agent = AgentFactory.createAgent(request.getBotType());
                    Game game = new Game(generateGameId(), agent, ticTacToe);
                    games.put(game.getGameId(), game);
                    return new GameResponse(game.getGameId(), "in-progress", game.getBoard());
                }
                catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid start request");
                }
            default:
                throw new IllegalArgumentException("Invalid game type");
        }

    }

    /**
     * Logic to handle player's move 
     * 
     * @param request from the player
     * @return updated game state
     */
    public GameResponse playerMove(PlayerMoveRequest request) throws IllegalArgumentException {
        System.out.println("Player move request: id:" + request.getGameId() + " x:" + request.getMove().getX() + " y:" + request.getMove().getY());
        
        // Get move object
        Action action = request.getMove();

        // Get game object
        Game game = games.get(request.getGameId());
        
        System.out.println("Game: " + game + " " + game.getGameId() + " getAgent " + game.getAgent() + " getStateHandler " + game.getStateHandler());
        System.out.println("Games   : " + games.keySet());
        System.out.println("Action x and y: " + ((Move) action).getX() + " " + ((Move) action).getY());
        System.out.println("Actions : " + game.getStateHandler().getActions());
        System.out.println("Does it contain action" + game.getStateHandler().getActions().contains(action));

        for (Action a : game.getStateHandler().getActions()) {
            System.out.println("Action x and y: " + ((Move) a).getX() + " " + ((Move) a).getY());
        }

        if (game.getStateHandler().getActions().contains(action)) {
            game.getStateHandler().result(action);
        }
        else {
            System.out.println("Invalid move");
            throw new IllegalArgumentException("Invalid move");
        }
        if (game.getStateHandler().isTerminal()) {
            return new GameResponse(game.getGameId(), "terminal", game.getBoard());
        }
        else {
            return new GameResponse(game.getGameId(), "in-progress", game.getBoard());
        }
    }

    public GameResponse botMove(String gameId) {
        // Logic to generate bot's move
        System.out.println("gameId: " + gameId);
        Game game = games.get(gameId);
        System.out.println("Bot move request: id:" + gameId);
        System.out.println("Game: " + game + " " + game.getGameId() + " getAgent " + game.getAgent() + " getStateHandler " + game.getStateHandler());
        Action action = game.getAgent().getAction(game.getStateHandler());
        StateHandler transformedGame = game.getStateHandler().result(action);
        // update game state
        game.setStateHandler(transformedGame);
        if (transformedGame.isTerminal()) {
            return new GameResponse(game.getGameId(), "terminal", game.getBoard());
        }
        else {
            return new GameResponse(game.getGameId(), "in-progress", game.getBoard());
        }
    }

    /**
     * Create a unique game id
     */
    private String generateGameId() {
        return UUID.randomUUID().toString();
    }
}
