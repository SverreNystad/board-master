package board.master.view;

import board.master.model.StateHandler;

public interface View {

    /**
     * Updates the view with the current state of the game
     * @param state
     */
    public abstract void updateBoard(StateHandler state);
}
