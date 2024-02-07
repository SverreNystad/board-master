package board.master.model.agents;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

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
        this.maxTime = maxTime*1000;
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
        this.agentPlayerId = state.toMove();
        startTime = Calendar.getInstance().getTimeInMillis();

        float currentBestMaximizer = Float.NEGATIVE_INFINITY;
        float currentBestMinimizer = Float.POSITIVE_INFINITY;
        Action currentBestAction = null;

        int depth = 1;

        List<Action> priorityActions = state.getActions().stream()
                .sorted(Comparator.comparingInt(action -> state.result(action).utility(agentPlayerId)))
                .collect(Collectors.toList());
        
        Collections.reverse(priorityActions);

        while (!isTimeUp()) {
            //Find the best move ordering
            for (Action action : priorityActions) {
                float value = evaluateState(state.result(action), currentBestMaximizer, currentBestMinimizer, depth, false);
                if (currentBestMaximizer < value) {
                    currentBestMaximizer = value;
                    currentBestAction = action;
                }
            }
            depth++;
        }

        return currentBestAction;
    }

    private float evaluateState(StateHandler state, float alpha, float beta, int depth, Boolean isMaximizingPlayer) {
        if (state.isTerminal() || depth <= 0 || isTimeUp()) {
            return state.utility(agentPlayerId);
        }

        List<Action> priorityActions = state.getActions().stream()
                .sorted(Comparator.comparingInt(action -> state.result(action).utility(agentPlayerId)))
                .collect(Collectors.toList());

        float value;
        int minusDepth = depth - 1;

        if (isMaximizingPlayer) {
            Collections.reverse(priorityActions);
            value = alpha;
            for (Action action : priorityActions) {
                value = Math.max(value, evaluateState(state.result(action), value, beta, minusDepth, !isMaximizingPlayer));
                if (value >= beta) {
                    break;
                }
            }
            
            return value;
        }
        else {
            value = beta;
            for (Action action : priorityActions) {
                value = Math.min(value, evaluateState(state.result(action), alpha, value, minusDepth, !isMaximizingPlayer));
                if (alpha >= value) {
                    break;
                }
            }
            return value;
        }
    }

    private boolean isTimeUp() {
        return Calendar.getInstance().getTimeInMillis() - startTime >= maxTime;
    }

}
