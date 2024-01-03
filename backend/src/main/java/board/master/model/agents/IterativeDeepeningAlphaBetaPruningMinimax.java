package board.master.model.agents;

import board.master.model.Action;
import board.master.model.StateHandler;

/**
 * An implementation of the Iterative Deepening Alpha-Beta Pruning Minimax algorithm.
 * This class functions as an agent in turn-based board games. It employs the 
 * Iterative Deepening technique to incrementally deepen the search depth, 
 * combined with Alpha-Beta pruning to optimize the Minimax search process.
 * The agent selects the best move based on the current state of the game.
 */
public class IterativeDeepeningAlphaBetaPruningMinimax implements Agent {
    
    private int agentPlayerId;
    private final int maxDepth = 6;
    /**
     * Determines the best action to take in the given game state.
     * This method iteratively deepens the search depth and uses alpha-beta pruning
     * to evaluate the minimax value of each possible action. It returns the action
     * with the highest evaluation.
     *
     * @param state The current state of the game.
     * @return The best action to take based on the evaluation.
     */
    @Override
    public Action getAction(StateHandler state) {
        this.agentPlayerId = state.toMove();

        float currentBestValue = Float.NEGATIVE_INFINITY;
        Action currentBestAction = null;
        
        for (int depth = 1; depth <= maxDepth; depth++) {
            currentBestValue = Float.NEGATIVE_INFINITY;
            float alpha = Float.NEGATIVE_INFINITY;
            float beta = Float.POSITIVE_INFINITY;

            for (Action action : state.getActions()) {
                float value = evaluateState(state.result(action), alpha, beta, depth, true);
                if (currentBestValue < value) {
                    currentBestValue = value;
                    currentBestAction = action;
                }
                alpha = Math.max(alpha, currentBestValue);
            }
        }
        return currentBestAction;
    }

    private float evaluateState(StateHandler state, float alpha, float beta, int depth, Boolean isMaximizingPlayer) {

        if (state.isTerminal() || depth == 0) {
            return state.utility(agentPlayerId);
        }
        float value;
        if (isMaximizingPlayer) {
                value = Float.NEGATIVE_INFINITY;
                for (Action action : state.getActions()) {
                    value = Math.max(value, evaluateState(state.result(action), alpha, beta, depth-1, !isMaximizingPlayer));
                    // Update alpha
                    alpha = Math.max(alpha, value);
                    if (alpha >= beta){
                        break;
                    }
                }
                return value;
        }
        else {
                value = Float.POSITIVE_INFINITY;
                for (Action action : state.getActions()) {
                    value = Math.min(value, evaluateState(state.result(action), alpha, beta, depth-1, !isMaximizingPlayer));
                    beta = Math.min(beta, value);
                    if (alpha >= beta) {
                        break;
                    }
                }
                return value;
        }
    }
}
