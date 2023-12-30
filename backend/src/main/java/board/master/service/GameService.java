package board.master.service;

import org.springframework.stereotype.Service;
import java.util.UUID;

import board.master.model.GameStartRequest;
import board.master.model.GameResponse;
import board.master.model.PlayerMoveRequest;
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
        // Find legal agents 
        
        if (!AgentFactory.isValidAgentType(request.getBotType())) {
            throw new IllegalArgumentException("Invalid agent type");
        }
        Agent agent = AgentFactory.createAgent(request.getBotType());

        StateHandler stateHandler = null;
        switch (request.getGameType()) {
            case "chess":
                stateHandler = new Chess();
                break;
            case "tic-tac-toe":
                stateHandler = new TicTacToe();
                break;
            default:
                throw new IllegalArgumentException("Invalid game type" + request.getGameType());
        }

        Game game = new Game(generateGameId(), agent, stateHandler);
        games.put(game.getGameId(), game);
        return new GameResponse(game.getGameId(), "in-progress", game.getBoard());
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
        Game game = null;
        if (games.containsKey(request.getGameId())) {
            game = games.get(request.getGameId());
        }
        else {
            throw new IllegalArgumentException("Invalid game id: " + request.getGameId());
        }

        if (game.getStateHandler().getActions().contains(action)) {
            StateHandler transformedGame = game.getStateHandler().result(action);
            // update game state
            game.setStateHandler(transformedGame);
        }
        else {
            System.out.println("Invalid move");
            throw new IllegalArgumentException("Invalid move" + action.toString());
        }
        if (game.getStateHandler().isTerminal()) {
            return new GameResponse(game.getGameId(), "terminal", game.getBoard());
        }
        else {
            return new GameResponse(game.getGameId(), "in-progress", game.getBoard());
        }
    }

    public GameResponse botMove(String gameId) {
        
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
