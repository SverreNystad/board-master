package board.master.model.games.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import board.master.model.Board;
import board.master.model.Move;

public class TicTacToeTest {
    private TicTacToe ticTacToe;

    private final int playerX = 1;
    private final int playerO = -1;

    @BeforeEach
    void TicTacToeSetup() {
        ticTacToe = new TicTacToe();
    }
    @Nested
    @DisplayName("TicTacToeConstructor")
    class TicTacToeConstructor {
        @Test
        @DisplayName("Test of constructor")
        void testConstructor() {
            Board newBoard = new Board(3, 3);
            assertEquals(newBoard.getGrid(), ticTacToe.getBoard().getGrid());
        }

        @Test
        @DisplayName("Test of deep copy constructor")
        void testDeepCopyConstructor() {
            TicTacToe newTicTacToe = new TicTacToe(ticTacToe);
            assertNotEquals(newTicTacToe, ticTacToe);
        }

        @Test
        @DisplayName("Test of deep copy constructor with mutated")
        void testDeepCopyConstructorMutated() {
            String value = "X";
            int x = 1;
            int y = 2;
            TicTacToe newTicTacToe = (TicTacToe) ticTacToe.result(new Move(x, y));
            assertEquals(value, newTicTacToe.getBoard().getPosition(x, y));
        }
    }
    

    @Nested
    @DisplayName("Test of utility")
    class TicTacToeUtility {
        @Test
        @DisplayName("Test of utility when board is empty")
        void testUtility() {
            int expected = 0;
            assertEquals(expected, ticTacToe.utility(playerX));
            assertEquals(expected, ticTacToe.utility(playerO));
        }

        @Test
        @DisplayName("Test of utility when board is full")
        void testUtility2() {
            int expected = 0;

            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(0, 0));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(0, 1));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(1, 1));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(0, 2));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(1, 2));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(1, 0));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(2, 0));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(2, 2));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(2, 1));

            assertEquals(expected, ticTacToe.utility(playerX));
            assertEquals(expected, ticTacToe.utility(playerO));
        }

        @Test
        @DisplayName("Test of utility when X wins")
        void testUtility3() {

            int expected = -1;
            int x = 1;
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(x, 0));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(x-1, 0));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(x, 1));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(x-1, 1));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(x, 2));
            assertEquals(expected, ticTacToe.utility(playerO));
        }

        @Test
        @DisplayName("Test of utility when O wins")
        void testUtility4() {
            int expected = 1;
            int x = 1;
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(x-1, 0));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(x, 0));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(x+1, 0));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(x, 1));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(x-1, 1));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(x,2));
            assertEquals(expected, ticTacToe.utility(playerO));
        }
    }

    @Nested
    @DisplayName("Test of getActions")
    class TicTacToeGetActions {
        @Test
        @DisplayName("Test of getActions when board is empty")
        void testGetActions() {
            int value = 9;
            assertEquals(value, ticTacToe.getActions().size());
        }

        @Test
        @DisplayName("Test of getActions when board is full")
        void testGetActions2() {
            int expected = 0;
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(0, 0));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(0, 1));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(1, 1));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(0, 2));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(1, 2));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(1, 0));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(2, 0));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(2, 2));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(2, 1));
            assertEquals(expected, ticTacToe.getActions().size());
        }
    }

    @Test
    void testGetBoard() {
        Board newBoard = new Board(3, 3);
        assertEquals(newBoard.getGrid(), ticTacToe.getBoard().getGrid());

    }

    @Test
    void testGetPosition() {
        ticTacToe  = (TicTacToe) ticTacToe.result(new Move(0, 0));
        assertEquals("X", ticTacToe.getPosition(0, 0));
    }

    @Nested
    @DisplayName("Test if TicTacToe is terminal")
    class TicTacToeIsTerminal {
        @Test
        @DisplayName("Test of TicTacToe not terminal")
        void testIsNotTerminal() {

            assertEquals(false, ticTacToe.isTerminal());
        }

        @Test
        @DisplayName("Test if TicTacToe is terminal when row is of the same value")
        void testIsTerminal() {
            int x = 1;
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(x, 0));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(x-1, 0));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(x, 1));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(x-1, 1));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(x, 2));

            assertEquals(true, ticTacToe.isTerminal());
        }

        @Test
        @DisplayName("Test if TicTacToe is terminal when diagonal is of the same value")
        void testIsTerminal2() {
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(0, 0));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(0, 1));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(1, 1));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(0, 2));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(2, 2));
            
            assertEquals(true, ticTacToe.isTerminal());
        }

        @Test
        @DisplayName("Test if TicTacToe is terminal when full")
        void testIsTerminal3() {
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(0, 0));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(0, 1));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(1, 1));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(0, 2));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(1, 2));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(1, 0));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(2, 0));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(2, 2));
            ticTacToe  = (TicTacToe) ticTacToe.result(new Move(2, 1));
            assertEquals(true, ticTacToe.isTerminal());
        }
    }

    @Nested
    @DisplayName("Test of result")
    class TicTacToeResult {
        @Test
        @DisplayName("Test of result when move is the first move")
        void testResult() {
            String value = "X";
            int x = 1;
            int y = 2;
            ticTacToe = (TicTacToe) ticTacToe.result(new Move(x, y));
            ticTacToe = (TicTacToe) ticTacToe.result(new Move(y, x));
            assertEquals(value, ticTacToe.getBoard().getPosition(x, y));
        }

        @Test
        @DisplayName("Test of result when move is the second move")
        void testResult2() {
            String value = "O";
            int x = 1;
            ticTacToe = (TicTacToe) ticTacToe.result(new Move(0, 0));
            TicTacToe newTicTacToe = (TicTacToe) ticTacToe.result(new Move(x, x));
            assertEquals(value, newTicTacToe.getBoard().getPosition(x, x));
        }
    }

    @Nested
    @DisplayName("Test of toMove")
    class TicTacToeToMove {
        @Test
        @DisplayName("Test of toMove when board is empty")
        void testToMove() {
            int value = 1;
            assertEquals(value, ticTacToe.toMove());
        }

        @Test
        @DisplayName("Test of toMove when 3 pieces are laid")
        void testToMove2() {
            String value = "-1";
            int x = 1;
            for (int y = 0; y < ticTacToe.getBoard().getColumns(); y++) {
                ticTacToe = (TicTacToe) ticTacToe.result(new Move(x, y));
            }
            assertEquals(Integer.parseInt(value), ticTacToe.toMove());
        }

        @Test
        @DisplayName("Test of toMove when board is full")
        void testToMove3() {
            String value = "1";
            for (int x = 0; x < ticTacToe.getBoard().getRows(); x++) {
                for (int y = 0; y < ticTacToe.getBoard().getColumns(); y++) {
                    ticTacToe = (TicTacToe) ticTacToe.result(new Move(x, y));
                }
            }
            assertEquals(0, ticTacToe.toMove());
        }
    }
}


