package board.master.model.agents;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import board.master.model.Action;
import board.master.model.StateHandler;
import board.master.model.games.tictactoe.TicTacToe;

public class AlphaBetaPruningMinimaxTest {
    private StateHandler stateHandler;
    private Agent agent;
    
    @BeforeEach
    void setUp() {
        stateHandler = new TicTacToe();
        agent = new AlphaBetaPruningMinimax();
        
    }
    @Test
    @DisplayName("Test agent gets an action")
    void testGetAction() {
        Action action = agent.getAction(stateHandler);
        assert(action != null);
    }

    @Test
    @DisplayName("Test agent gets a legal action")
    void testGetLegalAction() {
        Action action = agent.getAction(stateHandler);
        assert(stateHandler.getActions().contains(action));
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
    
}
