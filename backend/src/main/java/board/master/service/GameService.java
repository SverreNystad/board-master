package board.master.service;

import org.springframework.stereotype.Service;
import java.util.UUID;

import board.master.model.GameStartRequest;
import board.master.model.GameResponse;
import board.master.model.PlayerMoveRequest;
import board.master.model.Board;
import board.master.model.Game;
import board.master.model.games.Chess;
import board.master.model.StateHandler;
import board.master.model.agents.Agent;
import board.master.model.agents.AgentFactory;
import board.master.model.Action;
import board.master.model.Move;

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
                return new GameResponse("id", "tie", new Board(3,3));
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
        // Get move object
        Action action = request.getMove();

        // Get game object
        Game game = games.get(request.getGameId());

        if (game.getStateHandler().getActions().contains(action)) {
            game.getStateHandler().result(action);
        }
        else {
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
        Game game = games.get(gameId);
        Action action = game.getAgent().getAction(game.getStateHandler());
        game.getStateHandler().result(action);
        if (game.getStateHandler().isTerminal()) {
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
