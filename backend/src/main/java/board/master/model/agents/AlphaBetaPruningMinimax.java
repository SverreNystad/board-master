package board.master.model.agents;

import board.master.model.Action;
import board.master.model.StateHandler;

public class AlphaBetaPruningMinimax implements Agent {

    private int agentPlayerId;


    @Override
    public Action getAction(StateHandler state) {
        this.agentPlayerId = state.toMove();

        float currentBestValue = Float.NEGATIVE_INFINITY;
        Action currentBestAction = null;

        for (Action action : state.getActions()) {
            float value = evaluateState(state.result(action), false);
            if (currentBestValue < value) {
                currentBestValue = value;
                currentBestAction = action;
                
            }
        }
        return currentBestAction;
    }

    private float evaluateState(StateHandler state, Boolean isMaximizingPlayer) {

        if (state.isTerminal()) {
            return state.utility(agentPlayerId);
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
    
}
