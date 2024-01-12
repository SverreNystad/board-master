package board.master.model.games.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import board.master.model.Action;
import board.master.model.StateHandler;
import board.master.model.games.Board;

public class ChessTest {
    private Chess chess;
    private Board board;

    @BeforeEach
    void ChessSetUp() {
        chess = new Chess();
        board = Chess.CreateInitialBoard();
    }

    @Test
    @DisplayName("Test Chess constructor")
    void testCreateInitialBoard() {
        Board emptyBoard = new Board(8, 8);
        assertEquals(emptyBoard, chess.getBoard());
    }

    @Test
    void testGetActions() {

    }

    @Test
    void testGetBoard() {

    }

    @Test
    void testIsTerminal() {

    }

    @Test
    void testResult() {

    }


    @Nested
    class ToMoveTest  {
        @Test
        void testToMoveAtStart() {
            assertEquals(chess.toMove(), 1);
        }

        @Test
        @DisplayName("Test toMove after one move does not change original toMove but changes new boards toMove")
        void testToMoveAfterOneMove() {
            int firstMove = chess.toMove();
            Action action = chess.getActions().get(0);
            StateHandler transformed_chess = chess.result(action);
            assertNotEquals(transformed_chess.toMove(), firstMove, "toMove should change after one move");
            assertEquals(chess.toMove(), firstMove, "toMove should not change after one move");
        }
    
        
    }

    @Test
    void testUtility() {

    }
}
