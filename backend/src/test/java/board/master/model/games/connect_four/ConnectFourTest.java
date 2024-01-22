package board.master.model.games.connect_four;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

public class ConnectFourTest {

    private ConnectFour connectFour;

    @BeforeEach
    void setUp() {
        connectFour = new ConnectFour();

    }

    @Nested
    class TestGetActions {

        @Test
        void testGetAllActionsAtStart() {
            int expected = 7;
            int actual = connectFour.getActions().size();
            assertEquals(expected, actual);
        }

    }

    @Test
    void testGetBoard() {

    }

    @Nested
    class testIsTerminal {

        @Test
        void testIsTerminalAtStart() {
            boolean expected = false;
            boolean actual = connectFour.isTerminal();
            assertEquals(expected, actual);
        }

    }

    @Test
    void testResult() {

    }

    @Test
    void testToMove() {

    }

    @Test
    void testUtility() {

    }
}
