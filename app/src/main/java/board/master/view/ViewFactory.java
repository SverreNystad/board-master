package board.master.view;

/**
 * Factory class for creating game views.
 * 
 * This class provides a method to get a view object based on the
 * specified game type.
 * It uses the Factory design pattern to encapsulate the creation logic of game
 * objects,
 * allowing for easy addition of new games and maintaining the principle of
 * open/closed
 * for modifications and extensions.
 */
public class ViewFactory {

    /**
     * Returns a StateHandler object for the specified game type.
     * 
     * Based on the input string, this method returns an instance of the
     * corresponding
     * game class that implements the StateHandler interface. If the game type is
     * not recognized,
     * it returns null.
     *
     * @param gameType A string representing the type of game.
     * @return An instance of a class that implements StateHandler, corresponding to
     *         the specified game type.
     */
    public static View getView(String gameType) throws IllegalArgumentException {

        switch (gameType.toUpperCase()) {
            case "TIC-TAC-TOE":
                return new CLITicTacToeView();
            case "NIM":
                return new CLINimView();
            default:
                throw new IllegalArgumentException("Invalid game type: " + gameType);
        }
    }

}
