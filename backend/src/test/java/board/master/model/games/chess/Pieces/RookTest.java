package board.master.model.games.chess.Pieces;

import board.master.model.games.Board;
import board.master.model.games.chess.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RookTest {
    private Rook rook;
    private Board board;

    @BeforeEach
    void rookSetUp() {
        rook = new Rook(Color.WHITE, 6, 1);
        board = new Board(8, 8);
    }

    @Test
    @DisplayName("Test Rook constructor")
    void testConstructor() {
        Color color = Color.WHITE;
        int row = 6;
        int column = 1;
        assertEquals(color, rook.getColor());
        assertEquals(row, rook.getRow());
        assertEquals(column, rook.getColumn());
    }

    @Test
    @DisplayName("Test Rook Symbol")
    void testGetSymbol() {
        String symbol = "RW";
        assertEquals(symbol, rook.getSymbol());
    }

    @Test
    @DisplayName("Test Rook getValidMoves without other pieces")
    void testGetValidMovesNoOtherPieces() {
        int numberOfMoves = 14;
        assertEquals(numberOfMoves, rook.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test Rook getValidMoves with an opposite colored pieces")
    void testGetValidMovesWithEnemyPiece() {
        int x = 6;
        int y = 3;
        Rook enemyRook = new Rook(Color.BLACK, x, y);
        board.setPosition(x, y, enemyRook.getSymbol());
        int numberOfMoves = 10;
        assertEquals(numberOfMoves, rook.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test Rook getValidMoves a same colored piece")
    void testGetValidMovesWithFriendlyPiece() {
        int x = 6;
        int y = 3;
        Rook friendlyRook = new Rook(Color.WHITE, x, y);
        board.setPosition(x, y, friendlyRook.getSymbol());
        int numberOfMoves = 9;
        assertEquals(numberOfMoves, rook.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test Rook isValidMove invalid move")
    void testIsValidMoveFalse() {
        int x = 6;
        int y = 3;
        Rook friendlyRook = new Rook(Color.WHITE, x, y);
        board.setPosition(x, y, friendlyRook.getSymbol());
        assertFalse(rook.isValidMove(6, 3, board));
    }

    @Test
    @DisplayName("Test Rook isValidMove invalid move")
    void testIsValidMoveTrue() {
        int x = 6;
        int y = 3;
        Rook friendlyRook = new Rook(Color.BLACK, x, y);
        board.setPosition(x, y, friendlyRook.getSymbol());
        assertTrue(rook.isValidMove(6, 3, board));
    }

    @Test
    @DisplayName("Test Rook move when valid move")
    void testMoveValidMove() {
        int x = 4;
        int y = 1;
        rook.move(x, y, board);
        assertEquals(x, rook.getRow());
        assertEquals(y, rook.getColumn());
    }

    @Test
    @DisplayName("Test Rook move when invalid move")
    void testMoveInvalidMove() {
        int x = 5;
        int y = 0;
        assertThrows(IllegalArgumentException.class, () -> rook.move(x, y, board));
    }
}
