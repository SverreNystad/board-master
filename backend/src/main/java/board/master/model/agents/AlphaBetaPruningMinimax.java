package board.master.model.agents;

import board.master.model.Action;
import board.master.model.StateHandler;

/**
 * Implementation of the Alpha-Beta Pruning version of the Minimax algorithm.
 * This class is designed to function as an agent in a board game scenario,
 * where it decides the best move to make given the current state of the game.
 */
public class AlphaBetaPruningMinimax implements Agent {

    private int agentPlayerId;

    /**
     * Returns the best action to take in the current state.
     * It evaluates possible actions and chooses the one with the highest value
     * as determined by the alpha-beta pruning minimax algorithm.
     *
     * @param state The current state of the game.
     * @return The best action to take for the current player.
     */
    @Override
    public Action getAction(StateHandler state) {
        this.agentPlayerId = state.toMove();

        float currentBestMaximizer = Float.NEGATIVE_INFINITY;
        float currentBestMinimizer = Float.POSITIVE_INFINITY;
        Action currentBestAction = null;

        for (Action action : state.getActions()) {
            float value = evaluateState(state.result(action), currentBestMaximizer, currentBestMinimizer, false);
            if (currentBestMaximizer < value) {
                currentBestMaximizer = value;
                currentBestAction = action;
            }
        }

        return currentBestAction;
    }

    /**
     * Evaluates the given state of the game using the minimax algorithm with alpha-beta pruning.
     * This method is recursive and alternates between maximizing and minimizing player turns,
     * pruning branches that are not beneficial for the maximizing player.
     *
     * @param state The game state to evaluate.
     * @param alpha The alpha value for pruning (best already explored option along the path for the maximizer).
     * @param beta The beta value for pruning (best already explored option along the path for the minimizer).
     * @param isMaximizingPlayer True if the current move is by the maximizing player, false otherwise.
     * @return The evaluated score of the game state.
     */
    private float evaluateState(StateHandler state, float alpha, float beta, Boolean isMaximizingPlayer) {
        if (state.isTerminal()) {
            return state.utility(agentPlayerId);
        }

        float value;
        if (isMaximizingPlayer) {
            value = alpha;
            for (Action action : state.getActions()) {
                value = Math.max(value, evaluateState(state.result(action), value, beta, !isMaximizingPlayer));
                if (value >= beta) {
                    break;
                }
            }
            return value;
        } else {
            value = beta;
            for (Action action : state.getActions()) {
                value = Math.min(value, evaluateState(state.result(action), alpha, value, !isMaximizingPlayer));
                if (alpha >= value) {
                    break;
                }
            }
            return value;
        }
    }
}
