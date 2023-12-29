package board.master.model.agents;

import board.master.model.Action;
import board.master.model.StateHandler;
import java.lang.Math;

/**
 * Class implementing the Minimax algorithm.
 * 
 * Minimax is a recursive algorithm used for choosing optimal moves in
 * game theory settings, particularly in turn-based games. This class provides
 * methods to calculate the best possible move considering future potential
 * moves
 * of an opponent. It is designed to be used with games that can be represented
 * through a StateHandler.
 */
public class Minimax implements Agent {

    /**
     * Calculates the best move to make in a given game state.
     * 
     * This method applies the minimax algorithm to determine the optimal move.
     * It considers all possible moves, evaluates their outcomes, and chooses the
     * move that maximizes the player's chances of winning.
     *
     * @param state The current state of the game.
     * @return The best action to take in the current state.
     */
    @Override
    public Action getAction(StateHandler state) {
        float currentBestValue = Float.NEGATIVE_INFINITY;
        Action currentBestAction = null;

        for (Action action : state.getActions()) {
            float value = evaluateState(state.result(action), true);
            if (currentBestValue < value) {
                currentBestValue = value;
                currentBestAction = action;
                
            }
        }
        return currentBestAction;
    }

    /**
     * Evaluates the given state of the game.
     * 
     * The evaluation function assigns a numerical value to a game state,
     * indicating how favorable it is to the player using the minimax algorithm.
     * A more positive value indicates a more favorable state.
     *
     * @param state The game state to evaluate.
     * @return The numerical evaluation of the state.
     */
    private static float evaluateState(StateHandler state, Boolean isMaximizingPlayer) {

        if (state.isTerminal()) {
            return state.utility();
        }
        float value;
        if (isMaximizingPlayer) {
                value = Float.NEGATIVE_INFINITY;
                for (Action action : state.getActions()) {
                    value = Math.max(value, evaluateState(state.result(action), !isMaximizingPlayer));
                }
                return value;
        }
        else {
                value = Float.POSITIVE_INFINITY;
                for (Action action : state.getActions()) {
                    value = Math.min(value, evaluateState(state.result(action), !isMaximizingPlayer));
                }
                return value;
        }
    }

    // Additional methods for optimizations (like alpha-beta pruning) and utility
    // functions can be added here
    // TODO: Consider implementing alpha-beta pruning for optimization

}
