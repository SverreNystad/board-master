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

public class BishopTest {
    private Bishop bishop;
    private Board board;

    @BeforeEach
    void bishopSetUp() {
        bishop = new Bishop(Color.WHITE, 6, 1);
        board = new Board(8, 8);
    }

    @Test
    @DisplayName("Test Bishop constructor")
    void testConstructor() {
        Color color = Color.WHITE;
        int row = 6;
        int column = 1;
        assertEquals(color, bishop.getColor());
        assertEquals(row, bishop.getRow());
        assertEquals(column, bishop.getColumn());
    }

    @Test
    @DisplayName("Test Bishop Symbol")
    void testGetSymbol() {
        String symbol = "BW";
        assertEquals(symbol, bishop.getSymbol());
    }

    @Test
    @DisplayName("Test Bishop getValidMoves without other pieces")
    void testGetValidMovesNoOtherPieces() {
        int numberOfMoves = 9;
        assertEquals(numberOfMoves, bishop.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test Bishop getValidMoves with an opposite colored pieces")
    void testGetValidMovesWithEnemyPiece() {
        int x = 4;
        int y = 3;
        Rook enemyRook = new Rook(Color.BLACK, x, y);
        board.setPosition(x, y, enemyRook.getSymbol());
        int numberOfMoves = 5;
        assertEquals(numberOfMoves, bishop.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test Bishop getValidMoves a same colored piece")
    void testGetValidMovesWithFriendlyPiece() {
        int x = 4;
        int y = 3;
        Rook friendlyRook = new Rook(Color.WHITE, x, y);
        board.setPosition(x, y, friendlyRook.getSymbol());
        int numberOfMoves = 4;
        assertEquals(numberOfMoves, bishop.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test Bishop isValidMove invalid move")
    void testIsValidMoveFalse() {
        int x = 5;
        int y = 0;
        Rook friendlyRook = new Rook(Color.WHITE, x, y);
        board.setPosition(x, y, friendlyRook.getSymbol());
        assertFalse(bishop.isValidMove(x, y, board));
    }

    @Test
    @DisplayName("Test Bishop isValidMove invalid move")
    void testIsValidMoveTrue() {
        int x = 5;
        int y = 0;
        Rook friendlyRook = new Rook(Color.BLACK, x, y);
        board.setPosition(x, y, friendlyRook.getSymbol());
        assertTrue(bishop.isValidMove(x, y, board));
    }

    @Test
    @DisplayName("Test Bishop move when valid move")
    void testMoveValidMove() {
        int x = 4;
        int y = 3;
        bishop.move(x, y, board);
        assertEquals(x, bishop.getRow());
        assertEquals(y, bishop.getColumn());
    }

    @Test
    @DisplayName("Test Bishop move when invalid move")
    void testMoveInvalidMove() {
        int x = 5;
        int y = 1;
        assertThrows(IllegalArgumentException.class, () -> bishop.move(x, y, board));
    }
}
