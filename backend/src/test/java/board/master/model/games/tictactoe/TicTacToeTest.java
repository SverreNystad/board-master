package board.master.model.games.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import board.master.model.Board;
import board.master.model.Move;
import board.master.model.games.tictactoe.TicTacToe;

public class TicTacToeTest {
    private TicTacToe ticTacToe;
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
            String value = "1";
            int x = 1;
            int y = 2;
            ticTacToe.setPosition(x, y, value);
            TicTacToe newTicTacToe = new TicTacToe(ticTacToe);
            assertEquals(value, newTicTacToe.getBoard().getPosition(x, y));
        }
    }
    

    @Test
    void testUtility() {

    }

    @Test
    void testGetActions() {

    }

    @Test
    void testGetBoard() {

    }

    @Test
    void testGetPosition() {

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
            String value = "1";
            int x = 1;
            for (int y = 0; y < ticTacToe.getBoard().getColumns(); y++) {
                ticTacToe.setPosition(x, y, value);
            }
            assertEquals(true, ticTacToe.isTerminal());
        }

        @Test
        @DisplayName("Test if TicTacToe is terminal when diagonal is of the same value")
        void testIsTerminal2() {
            String value = "1";
            for (int index = 0; index < ticTacToe.getBoard().getRows(); index++) {
                ticTacToe.setPosition(index, index, value);
            }
            assertEquals(true, ticTacToe.isTerminal());
        }

        @Test
        @DisplayName("Test if TicTacToe is terminal when full")
        void testIsTerminal3() {
            for (int x = 0; x < ticTacToe.getBoard().getRows(); x++) {
                for (int y = 0; y < ticTacToe.getBoard().getColumns(); y++) {
                    ticTacToe.setPosition(x, y, "1");
                }
            }
            assertEquals(true, ticTacToe.isTerminal());
        }
    }

    @Nested
    @DisplayName("Test of result")
    class TicTacToeResult {
        @Test
        @DisplayName("Test of result when not terminal")
        void testResult() {
            String value = "1";
            int x = 1;
            int y = 2;
            ticTacToe.setPosition(x, y, value);
            TicTacToe newTicTacToe = (TicTacToe) ticTacToe.result(new Move(x, y, value));
            assertEquals(value, newTicTacToe.getBoard().getPosition(x, y));
        }

        @Test
        @DisplayName("Test of result when terminal")
        void testResult2() {
            String value = "1";
            int x = 1;
            for (int y = 0; y < ticTacToe.getBoard().getColumns(); y++) {
                ticTacToe.setPosition(x, y, value);
            }
            TicTacToe newTicTacToe = (TicTacToe) ticTacToe.result(new Move(x, 0));
            assertEquals(value, newTicTacToe.getBoard().getPosition(x, 0));
        }
    }
    @Test
    void testResult() {

    }

    @Test
    void testSetPosition() {

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
                ticTacToe.setPosition(x, y, value);
            }
            assertEquals(Integer.parseInt(value), ticTacToe.toMove());
        }

        @Test
        @DisplayName("Test of toMove when board is full")
        void testToMove3() {
            String value = "1";
            for (int x = 0; x < ticTacToe.getBoard().getRows(); x++) {
                for (int y = 0; y < ticTacToe.getBoard().getColumns(); y++) {
                    ticTacToe.setPosition(x, y, value);
                }
            }
            assertEquals(0, ticTacToe.toMove());
        }
    }
}

