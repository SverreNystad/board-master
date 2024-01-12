package board.master.model.agents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import board.master.model.Action;
import board.master.model.StateHandler;
import board.master.model.games.Move;
import board.master.model.games.tictactoe.TicTacToe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;

public class IterativeDeepeningAlphaBetaPruningMinimaxTest {
    private StateHandler stateHandler;
    private IterativeDeepeningAlphaBetaPruningMinimax agent;
    long maxTime;
    long margin;

    @BeforeEach
    void setUp() {
        maxTime = 100;
        margin = 100;
        stateHandler = new TicTacToe();
        agent = new IterativeDeepeningAlphaBetaPruningMinimax(maxTime);
    }

    @Test
    @DisplayName("Test agent gets an action")
    void testGetAction() {
        Action action = agent.getAction(stateHandler);
        assert(action != null);

    }

    @Test
    @DisplayName("Test agent gets an action within time limit")
    void testGetActionWithinTimeLimit() {
        long startTime = Calendar.getInstance().getTimeInMillis();
        Action action = agent.getAction(stateHandler);
        long endTime = Calendar.getInstance().getTimeInMillis();
        assert(endTime - startTime <= maxTime + margin);
    }

    @Test
    @DisplayName("Test agent makes the best move")
    void testGetActionBestMove() {
        stateHandler.result(new Move(0, 0));
        stateHandler.result(new Move(0, 1));
        stateHandler.result(new Move(1, 0));
        stateHandler.result(new Move(1, 1));
        
        Action action = agent.getAction(stateHandler);
        assertEquals(1, action.getMove().getX());
        assertEquals(1, action.getMove().getY());
    }


}
