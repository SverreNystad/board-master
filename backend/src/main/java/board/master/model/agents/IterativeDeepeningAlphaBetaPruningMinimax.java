package board.master.model.agents;

import java.util.Calendar;

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
    private final long maxTime;
    private long startTime;

    public IterativeDeepeningAlphaBetaPruningMinimax(long maxTime) {
        this.maxTime = maxTime;
    }


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
        init(state);

        float currentBestMaximizer = Float.NEGATIVE_INFINITY;
        float currentBestMinimizer = Float.POSITIVE_INFINITY;
        Action currentBestAction = null;

        for (Action action : state.getActions()) {
            float value = evaluateState(state.result(action), currentBestMaximizer, currentBestMinimizer, 1, false);
            if (currentBestMaximizer < value) {
                currentBestMaximizer = value;
                currentBestAction = action;
                
            }
        }

        return currentBestAction;
    }

    private float evaluateState(StateHandler state, float alpha, float beta, int depth, Boolean isMaximizingPlayer) {
        return 0;
    }

    private boolean isTimeUp() {
        return Calendar.getInstance().getTimeInMillis() - startTime >= maxTime;
    }

    private void init(StateHandler state) {
        this.agentPlayerId = state.toMove();
        startTime = Calendar.getInstance().getTimeInMillis();
    }

}
