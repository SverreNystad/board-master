package board.master.model.agents;

import board.master.model.Action;
import board.master.model.StateHandler;
import board.master.model.games.Move;
import board.master.model.games.tictactoe.TicTacToe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimaxTest {
    private StateHandler stateHandler;
    private Agent agent;

    @BeforeEach
    void setUp() {
        stateHandler = new TicTacToe();
        agent = new Minimax();
    }

    @Test
    @DisplayName("Test agent gets an action")
    void testGetAction() {
        Action action = agent.getAction(stateHandler);
        assert (action != null);
    }

    @Test
    @DisplayName("Test agent gets a legal action")
    void testGetLegalAction() {
        Action action = agent.getAction(stateHandler);
        assert (stateHandler.getActions().contains(action));
    }

    @Test
    @DisplayName("Test agent does not mutate the board when getting action")
    void testNotFillBoard() {
        int expectedSpaces = stateHandler.getActions().size();
        // Let the agent get an action
        Action action = agent.getAction(stateHandler);
        // Check that the board is not mutated
        assertEquals(expectedSpaces, stateHandler.getActions().size());
    }

    @Test
    @DisplayName("Test agent makes the best move")
    void testGetActionBestMove() {
        String x = "0";
        String y = "2";

        stateHandler = stateHandler.result(new Move(2, 0)); // X
        stateHandler = stateHandler.result(new Move(1, 0)); // O

        stateHandler = stateHandler.result(new Move(1, 1)); // X
        stateHandler = stateHandler.result(new Move(0, 1)); // O

        stateHandler = stateHandler.result(new Move(1, 2)); // X
        stateHandler = stateHandler.result(new Move(2, 2)); // O

        Move action = (Move) agent.getAction(stateHandler);
        assertEquals(x, action.getX());
        assertEquals(y, action.getY());
    }
}
