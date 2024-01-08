package board.master.model.games.chess.Pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import board.master.model.games.Board;
import board.master.model.games.chess.Color;

public class BishopTest {
    private Bishop bishop;
    private Board board;

    @BeforeEach
    void bishopSetUp() {
        bishop = new Bishop(Color.WHITE, 6, 1);
        board = new Board(8, 8);
    }

    @Test
    @DisplayName("Test Pawn constructor")
    void testConstructor() {
        Color color = Color.WHITE;
        int row = 6;
        int column = 1;
        assertEquals(color, bishop.getColor());
        assertEquals(row, bishop.getRow());
        assertEquals(column, bishop.getColumn());
    }

    @Test
    @DisplayName("Test Pawn Symbol")
    void testGetSymbol() {
        String symbol = "BW";
        assertEquals(symbol, bishop.getSymbol());
    }

    @Test
    @DisplayName("Test Pawn getValidMoves without other pieces")
    void testGetValidMovesNoOtherPieces() {
        int numberOfMoves = 9;
        assertEquals(numberOfMoves, bishop.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test Pawn getValidMoves with an opposite colored pieces")
    void testGetValidMovesWithEnemyPiece() {
        int x = 4;
        int y = 3;
        Rook enemyRook = new Rook(Color.BLACK, x, y);
        board.setPosition(x, y, enemyRook.getSymbol());
        int numberOfMoves = 5;
        assertEquals(numberOfMoves, bishop.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test Pawn getValidMoves a same colored piece")
    void testGetValidMovesWithFriendlyPiece() {
        int x = 4;
        int y = 3;
        Rook friendlyRook = new Rook(Color.WHITE, x, y);
        board.setPosition(x, y, friendlyRook.getSymbol());
        int numberOfMoves = 4;
        assertEquals(numberOfMoves, bishop.getValidMoves(board).size());
    }

    @Test
    void testIsValidMove() {

    }

    @Test
    void testMove() {

    }
}
