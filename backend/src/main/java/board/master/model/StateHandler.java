package board.master.model;

import java.util.List;

/**
 * Interface representing the state handler for a game.
 * This interface provides methods to handle different states of the game,
 * such as determining the current player's turn, available actions,
 * transitioning to a new state, and assessing the game's terminal state and
 * utility.
 */
public interface StateHandler {

    /**
     * Returns the identifier of the player whose turn it is to move in the current
     * state.
     * This method is used to determine which player should make the next move.
     *
     * @return the player identifier (int) whose turn it is to move in the current
     *         state.
     */
    public int toMove();

    /**
     * Provides a list of all legal actions available in the current state of the
     * game.
     * This method is essential for understanding the possible moves a player can
     * make.
     *
     * @return a list of legal actions ({@link Action}) available in the current
     *         state.
     */
    public List<Action> getActions();

    /**
     * Transitions the game to a new state based on the given action.
     * This method applies the specified action to the current state, resulting in a
     * new state.
     *
     * @param action the action ({@link Action}) to be taken in the current state.
     */
    public StateHandler result(Action action);

    /**
     * Determines if the current state is a terminal state, indicating the end of
     * the game.
     * Terminal states are those where the game has concluded, either in a win,
     * loss, or draw.
     *
     * @return {@code true} if the current state is terminal (game over);
     *         {@code false} otherwise.
     */
    public boolean isTerminal();

    /**
     * Calculates and returns the utility of the current state for the player.
     * Utility is a numerical value representing the desirability or "goodness" of a
     * state for a player,
     * often used in game algorithms to determine optimal moves.
     *
     * @return the utility (int) of the current state for the player.
     */
    public int Utility();

    /**
     * Returns the board object associated with the current state.
     * 
     * @return the board object associated with the current state.
     */
    public Board getBoard();
}
