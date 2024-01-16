package board.master.model.games.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import board.master.model.Action;
import board.master.model.StateHandler;
import board.master.model.games.Board;
import board.master.model.games.Move;
import board.master.model.games.chess.Pieces.King;
import board.master.model.games.chess.Pieces.Piece;
import board.master.model.games.chess.Pieces.Rook;

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
    class testIsTerminal {
        @Test
        void testIsTerminalAtStart() {
            assertEquals(false, chess.isTerminal(), "Game should not be terminal at start");
        }

        @Test
        void testIsTerminalAfterOneMove() {
            Action action = chess.getActions().get(0);
            StateHandler transformed_chess = chess.result(action);
            assertEquals(false, transformed_chess.isTerminal(), "Game should not be terminal after one move");
        }

        @Test
        void testIsNotTerminalWithCheck() {
            Board board = new Board(8,8);
            Map<String, Piece> pieces = new HashMap<String, Piece>();

            Piece whiteKing = new King(Color.WHITE, 1, 1);
            Piece blackRook = new Rook(Color.BLACK, 6, 1);
            Piece blackKing = new King(Color.BLACK, 7, 7);

            board.setPosition(whiteKing.row, whiteKing.column, whiteKing.getSymbol());
            board.setPosition(blackRook.row, blackRook.column, blackRook.getSymbol());
            board.setPosition(blackKing.row, blackKing.column, blackKing.getSymbol());

            pieces.put(whiteKing.getSymbol(), whiteKing);
            pieces.put(blackRook.getSymbol(), blackRook);
            pieces.put(blackKing.getSymbol(), blackKing);

            chess = new Chess(board, 1, pieces);

            assertEquals(false, chess.isTerminal(), "Game should not be terminal with check");
        }

        //TODO: Add test for checkmate
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
