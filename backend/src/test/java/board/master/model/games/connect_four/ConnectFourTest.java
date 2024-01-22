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
            int expected = 6;
            int actual = connectFour.getActions().size();
            assertEquals(expected, actual);
        }

        @Test
        void testGetAllActionsAfterOneMove() {
            int expected = 6;
            connectFour.result(connectFour.getActions().get(0));
            int actual = connectFour.getActions().size();
            assertEquals(expected, actual);
        }

        @Test
        void testGetAllActionsAfterColumnIsFull() {
            int expected = 6;
            int coloumnHeight = 7;
            for (int i = 0; i < coloumnHeight; i++) {
                connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            }
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

        @Test
        void testIsTerminalAfterLessThenPossibleWinningCombination() {
            boolean expected = false;
            int numberOfMoves = 3;
            for (int i = 0; i < numberOfMoves; i++) {
                connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(0));
                connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            }
            boolean actual = connectFour.isTerminal();
            assertEquals(expected, actual);
        }

        @Test
        void testIsTerminalAfterGameIsWon() {
            boolean expected = true;
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(0));

        }

    }

    @Test
    void testResult() {

    }

    @Nested
    class TestToMove {

        @Test
        void testToMoveAtStart() {
            int expected = 1;
            int actual = connectFour.toMove();
            assertEquals(expected, actual);
        }

        @Test
        void testToMoveAfterOneMove() {
            int expected = -1;
            connectFour.result(connectFour.getActions().get(0));
            int actual = connectFour.toMove();
            assertEquals(expected, actual);
        }

        @Test
        void testToMoveAfterTwoMoves() {
            int expected = 1;
            connectFour.result(connectFour.getActions().get(0));
            connectFour.result(connectFour.getActions().get(0));
            int actual = connectFour.toMove();
            assertEquals(expected, actual);
        }

    }


    @Test
    void testUtility() {

    }
}
