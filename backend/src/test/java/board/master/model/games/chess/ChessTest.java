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
    //private Board board;

    @BeforeEach
    void ChessSetUp() {
        chess = new Chess();
    }

    @Test
    @DisplayName("Test Chess constructor")
    void testCreateInitialBoard() {
        Board chessBoard = chess.getBoard();
        for (int i = 0; i < 8; i++) {
            if (i == 0 || i == 7) {
                assertEquals(chessBoard.getPosition(0, i), "♜");
                assertEquals(chessBoard.getPosition(7, i), "♖");
            } else if (i == 1 || i == 6) {
                assertEquals(chessBoard.getPosition(0, i), "♞");
                assertEquals(chessBoard.getPosition(7, i), "♘");
            } else if (i == 2 || i == 5) {
                assertEquals(chessBoard.getPosition(0, i), "♝");
                assertEquals(chessBoard.getPosition(7, i), "♗");
            } else if (i == 3) {
                assertEquals(chessBoard.getPosition(0, i), "♛");
                assertEquals(chessBoard.getPosition(7, i), "♕");
            } else if (i == 4) {
                assertEquals(chessBoard.getPosition(0, i), "♚");
                assertEquals(chessBoard.getPosition(7, i), "♔");
            } else {
                assertEquals(chessBoard.getPosition(0, i), "♟");
                assertEquals(chessBoard.getPosition(7, i), "♙");
                
            }
            assertEquals(chessBoard.getPosition(1, i), "♟");
            assertEquals(chessBoard.getPosition(6, i), "♙");
        }
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
