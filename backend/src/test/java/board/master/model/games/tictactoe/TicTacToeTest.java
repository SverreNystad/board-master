package board.master.model.games.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import board.master.model.Board;
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
            int value = 1;
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

    @Test
    void testIsTerminal() {

    }

    @Test
    void testResult() {

    }

    @Test
    void testSetPosition() {

    }

    @Test
    void testToMove() {

    }
}

