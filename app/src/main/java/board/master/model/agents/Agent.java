package board.master.model.agents;

import board.master.model.game.Action;
import board.master.model.game.StateHandler;

/**
 * The Agent interface represents an agent that can play a game.
 * It shall choose the best move to make in a given game state.
 * 
 * The Agent follows the strategy pattern:
 * https://en.wikipedia.org/wiki/Strategy_pattern
 */
public interface Agent {

    /**
     * Calculates the best move to make in a given game state.
     * 
     * @param state The current state of the game.
     * @return The best action to take in the current state.
     */
    public Action getAction(StateHandler state);

}
