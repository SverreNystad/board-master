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
import board.master.model.games.Move;

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
        int row = 1;
        for (int i = 0; i < 8; i++) {
            assertEquals('B', chessBoard.getPosition(row, i).charAt(1));
        }
    }

    
    @Test
    void testGetActionsAtStartOfGame() {
        assertEquals(20, chess.getActions().size(), "There should be 20 possible moves at the start of the game");
    }

    @Test
    void testNoMovesForTerminalState() {
        StateHandler game = new Chess();
        while (game.isTerminal() == false) {
            Action action = game.getActions().get(0);
            game = game.result(action);
        }
        assertEquals(0, game.getActions().size(), "There should be no possible moves at the end of the game");
    }



    @Test
    void testGetBoard() {

    }

    @Test
    void testIsTerminal() {

    }

    @Nested
    class TestMoves {
        @Test
        void testResultOfLegalMove() {
            Action action = chess.getActions().get(0);
            StateHandler transformed_chess = chess.result(action);
            assertNotEquals(transformed_chess, chess);
        }

        @Test
        void testResultChangesPiecePosition() {

        }

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
