package board.master.model.games;

import java.util.HashMap;
import java.util.List;

import board.master.model.StateHandler;
import board.master.model.games.chess.Chess;
import board.master.model.games.connect_four.ConnectFour;
import board.master.model.games.tictactoe.TicTacToe;

/**
 * Factory class for creating instances of StateHandler for different board games.
 */
public class GameStateHandlerFactory {
    private static final HashMap<String, StateHandler> gameHandlers = initializeGameHandlers();

    /**
     * Creates an instance of StateHandler for the specified board game.
     *
     * Supported game types are available in the {@link #getSupportedGameHandlersList()} method.
     *
     * @param gameType - the type of board game for which to create a StateHandler
     * @return StateHandler instance for the specified game
     * @throws IllegalArgumentException if the specified game type is unsupported
     */
    public static StateHandler createGameHandler(String gameType) throws IllegalArgumentException {
        StateHandler handler = gameHandlers.get(gameType.toUpperCase());
        if (handler == null) {
            throw new IllegalArgumentException("Unsupported game type: " + gameType);
        }
        return handler;
    }

    /**
     * Checks if the provided game type is a valid handler.
     *
     * @param gameType - the type of game to check
     * @return true if the game type is valid, false otherwise
     */
    public static boolean isValidGameHandler(String gameType) {
        return getSupportedGameHandlersList().contains(gameType.toUpperCase());
    }

    /**
     * Returns a list of supported game types for StateHandlers.
     *
     * @return list of valid game types for StateHandlers
     */
    public static List<String> getSupportedGameHandlersList() {
        return List.copyOf(gameHandlers.keySet());
    }

    /**
     * Initializes and returns a map of game types to their corresponding StateHandler instances.
     *
     * @return HashMap mapping game types to StateHandler instances
     */
    private static HashMap<String, StateHandler> initializeGameHandlers() {
        HashMap<String, StateHandler> handlers = new HashMap<>();
        handlers.put("CHESS", new Chess());
        handlers.put("TIC-TAC-TOE", new TicTacToe());
        handlers.put("CONNECT-FOUR", new ConnectFour());
        return handlers;
    }
}
