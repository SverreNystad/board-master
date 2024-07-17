package board.master.model.games.chess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import board.master.model.Action;
import board.master.model.StateHandler;
import board.master.model.games.Board;
import board.master.model.games.chess.Pieces.King;
import board.master.model.games.chess.Pieces.Pawn;
import board.master.model.games.chess.Pieces.Piece;
import board.master.model.games.chess.Pieces.Rook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ChessTest {
    private Chess chess;
    // private Board board;

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
        Board board = new Board(8, 8);
        Map<String, Piece> pieces = new HashMap<String, Piece>();

        Piece whiteKing = new King(Color.WHITE, 1, 1);
        Piece blackRook = new Rook(Color.BLACK, 6, 1);
        Piece blackKing = new King(Color.BLACK, 7, 7);

        // Place pawns in a ring around the king
        Piece whitePawn1 = new Pawn(Color.WHITE, 0, 0);
        Piece whitePawn2 = new Pawn(Color.WHITE, 0, 1);
        Piece whitePawn3 = new Pawn(Color.WHITE, 0, 2);
        Piece whitePawn4 = new Pawn(Color.WHITE, 1, 2);
        Piece whitePawn5 = new Pawn(Color.WHITE, 1, 0);
        Piece whitePawn6 = new Pawn(Color.WHITE, 2, 0);
        Piece whitePawn7 = new Pawn(Color.WHITE, 2, 2);

        board.setPosition(whiteKing.row, whiteKing.column, whiteKing.getSymbol());
        board.setPosition(blackRook.row, blackRook.column, blackRook.getSymbol());
        board.setPosition(blackKing.row, blackKing.column, blackKing.getSymbol());

        board.setPosition(whitePawn1.row, whitePawn1.column, whitePawn1.getSymbol());
        board.setPosition(whitePawn2.row, whitePawn2.column, whitePawn2.getSymbol());
        board.setPosition(whitePawn3.row, whitePawn3.column, whitePawn3.getSymbol());
        board.setPosition(whitePawn4.row, whitePawn4.column, whitePawn4.getSymbol());
        board.setPosition(whitePawn5.row, whitePawn5.column, whitePawn5.getSymbol());
        board.setPosition(whitePawn6.row, whitePawn6.column, whitePawn6.getSymbol());
        board.setPosition(whitePawn7.row, whitePawn7.column, whitePawn7.getSymbol());

        pieces.put(whiteKing.getSymbol(), whiteKing);
        pieces.put(blackRook.getSymbol(), blackRook);
        pieces.put(blackKing.getSymbol(), blackKing);
        pieces.put(whitePawn1.getSymbol() + 1, whitePawn1);
        pieces.put(whitePawn2.getSymbol() + 2, whitePawn2);
        pieces.put(whitePawn3.getSymbol() + 3, whitePawn3);
        pieces.put(whitePawn4.getSymbol() + 4, whitePawn4);
        pieces.put(whitePawn5.getSymbol() + 5, whitePawn5);
        pieces.put(whitePawn6.getSymbol() + 6, whitePawn6);
        pieces.put(whitePawn7.getSymbol() + 7, whitePawn7);

        StateHandler game = new Chess(board, 1, pieces);
        assertEquals(0, game.getActions().size(), "There should be no possible moves at the end of the game");
    }

    @Test
    void testGetBoard() {}

    @Test
    void testIsTerminal() {}

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
            Board board = new Board(8, 8);
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

        @Test
        void testIsNotTerminalWithOneMoveOutOfCheck() {
            Board board = new Board(8, 8);
            Map<String, Piece> pieces = new HashMap<String, Piece>();

            Piece whiteKing = new King(Color.WHITE, 1, 1);
            Piece blackRook = new Rook(Color.BLACK, 6, 1);
            Piece blackKing = new King(Color.BLACK, 7, 7);

            // Place pawns in a ring around the king
            Piece whitePawn1 = new Pawn(Color.WHITE, 0, 0);
            Piece whitePawn2 = new Pawn(Color.WHITE, 0, 1);
            Piece whitePawn3 = new Pawn(Color.WHITE, 0, 2);
            Piece whitePawn4 = new Pawn(Color.WHITE, 1, 2);
            Piece whitePawn5 = new Pawn(Color.WHITE, 2, 0);
            Piece whitePawn6 = new Pawn(Color.WHITE, 2, 2);

            board.setPosition(whiteKing.row, whiteKing.column, whiteKing.getSymbol());
            board.setPosition(blackRook.row, blackRook.column, blackRook.getSymbol());
            board.setPosition(blackKing.row, blackKing.column, blackKing.getSymbol());

            board.setPosition(whitePawn1.row, whitePawn1.column, whitePawn1.getSymbol());
            board.setPosition(whitePawn2.row, whitePawn2.column, whitePawn2.getSymbol());
            board.setPosition(whitePawn3.row, whitePawn3.column, whitePawn3.getSymbol());
            board.setPosition(whitePawn4.row, whitePawn4.column, whitePawn4.getSymbol());
            board.setPosition(whitePawn5.row, whitePawn5.column, whitePawn5.getSymbol());
            board.setPosition(whitePawn6.row, whitePawn6.column, whitePawn6.getSymbol());

            pieces.put(whiteKing.getSymbol(), whiteKing);
            pieces.put(blackRook.getSymbol(), blackRook);
            pieces.put(blackKing.getSymbol(), blackKing);
            pieces.put(whitePawn1.getSymbol(), whitePawn1);
            pieces.put(whitePawn2.getSymbol(), whitePawn2);
            pieces.put(whitePawn3.getSymbol(), whitePawn3);
            pieces.put(whitePawn4.getSymbol(), whitePawn4);
            pieces.put(whitePawn5.getSymbol(), whitePawn5);
            pieces.put(whitePawn6.getSymbol(), whitePawn6);

            chess = new Chess(board, 1, pieces);

            assertEquals(false, chess.isTerminal(), "Game should not be terminal with check");
        }

        @Test
        void testIsTerminalWithCheckMate() {
            Board board = new Board(8, 8);
            Map<String, Piece> pieces = new HashMap<String, Piece>();

            Piece whiteKing = new King(Color.WHITE, 1, 1);
            Piece blackRook = new Rook(Color.BLACK, 6, 1);
            Piece blackKing = new King(Color.BLACK, 7, 7);

            // Place pawns in a ring around the king
            Piece whitePawn1 = new Pawn(Color.WHITE, 0, 0);
            Piece whitePawn2 = new Pawn(Color.WHITE, 0, 1);
            Piece whitePawn3 = new Pawn(Color.WHITE, 0, 2);
            Piece whitePawn4 = new Pawn(Color.WHITE, 1, 2);
            Piece whitePawn5 = new Pawn(Color.WHITE, 1, 0);
            Piece whitePawn6 = new Pawn(Color.WHITE, 2, 0);
            Piece whitePawn7 = new Pawn(Color.WHITE, 2, 2);

            board.setPosition(whiteKing.row, whiteKing.column, whiteKing.getSymbol());
            board.setPosition(blackRook.row, blackRook.column, blackRook.getSymbol());
            board.setPosition(blackKing.row, blackKing.column, blackKing.getSymbol());

            board.setPosition(whitePawn1.row, whitePawn1.column, whitePawn1.getSymbol());
            board.setPosition(whitePawn2.row, whitePawn2.column, whitePawn2.getSymbol());
            board.setPosition(whitePawn3.row, whitePawn3.column, whitePawn3.getSymbol());
            board.setPosition(whitePawn4.row, whitePawn4.column, whitePawn4.getSymbol());
            board.setPosition(whitePawn5.row, whitePawn5.column, whitePawn5.getSymbol());
            board.setPosition(whitePawn6.row, whitePawn6.column, whitePawn6.getSymbol());
            board.setPosition(whitePawn7.row, whitePawn7.column, whitePawn7.getSymbol());

            pieces.put(whiteKing.getSymbol(), whiteKing);
            pieces.put(blackRook.getSymbol(), blackRook);
            pieces.put(blackKing.getSymbol(), blackKing);
            pieces.put(whitePawn1.getSymbol() + 1, whitePawn1);
            pieces.put(whitePawn2.getSymbol() + 2, whitePawn2);
            pieces.put(whitePawn3.getSymbol() + 3, whitePawn3);
            pieces.put(whitePawn4.getSymbol() + 4, whitePawn4);
            pieces.put(whitePawn5.getSymbol() + 5, whitePawn5);
            pieces.put(whitePawn6.getSymbol() + 6, whitePawn6);
            pieces.put(whitePawn7.getSymbol() + 7, whitePawn7);

            chess = new Chess(board, 1, pieces);
            boolean isTerminal = chess.isTerminal();

            assertEquals(true, isTerminal, "Game should be terminal with checkmate");
        }
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
        public void testAllGetActionsAreLegal() {
            List<Action> actions = chess.getActions();
            for (Action action : actions) {
                StateHandler transformed_chess = chess.result(action);
                assertNotEquals(transformed_chess.getBoard(), chess.getBoard());
            }
        }

        @Test
        public void testPlayGame() {
            StateHandler transformed_chess = chess;
            List<Action> actions = transformed_chess.getActions();
            while (!transformed_chess.isTerminal()) {
                Action action = actions.get(0);
                transformed_chess = transformed_chess.result(action);
                actions = transformed_chess.getActions();
            }
        }

        @Test
        void testResultChangesPiecePosition() {}
    }

    @Nested
    class ToMoveTest {
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
    void testUtility() {}
}
