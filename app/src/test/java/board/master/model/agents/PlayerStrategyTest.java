package board.master.model.agents;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import board.master.model.Action;
import board.master.model.StateHandler;
import board.master.model.game.tic_tac_toe.TicTacToe;
import board.master.model.game.tic_tac_toe.TicTacToeAction;
import board.master.model.input.InputHandler;
import board.master.model.input.MockInputHandler;

public class PlayerStrategyTest {
    
    private PlayerStrategy playerStrategy;
    private InputHandler inputHandler;
    private StateHandler state;


    @Test
    void testTicTacToeGetAction() {
        inputHandler = new MockInputHandler("TicTacToe");
        playerStrategy = new PlayerStrategy(inputHandler);
        state = new TicTacToe();
        Action action = playerStrategy.getAction(state);
        assertTrue(action instanceof TicTacToeAction);
    }

    @Test
    void testGetAction() {
        inputHandler = new MockInputHandler("TicTacToe");
        playerStrategy = new PlayerStrategy(inputHandler);
        state = new TicTacToe();
        Action action = playerStrategy.getAction(state);
        assertTrue(action instanceof TicTacToeAction);
    }
}
