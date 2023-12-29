package board.master.model.agents;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import board.master.model.Action;
import board.master.model.StateHandler;
import board.master.model.games.tictactoe.TicTacToe;

public class MinimaxTest {
    private StateHandler stateHandler;
    private Agent agent;
    @BeforeEach
    void setUp() {
        stateHandler = new TicTacToe();
        agent = new Minimax();
        
    }
    @Test
    void testGetAction() {
        Action action = agent.getAction(stateHandler);
        assert(action != null);
    }

    @Test
    void testNotFillBoard() {
        int expectedSpaces = stateHandler.getActions().size();
        Action action = agent.getAction(stateHandler);
        assertEquals(expectedSpaces, stateHandler.getActions().size());
    }
}
