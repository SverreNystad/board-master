package board.master.model.games.chess.Pieces;

import board.master.model.games.Board;
import board.master.model.games.chess.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueenTest {
    private Queen queen;
    private Board board;

    @BeforeEach
    void rookSetUp() {
        queen = new Queen(Color.WHITE, 6, 1);
        board = new Board(8, 8);
    }

    @Test
    @DisplayName("Test Queen constructor")
    void testConstructor() {
        Color color = Color.WHITE;
        int row = 6;
        int column = 1;
        assertEquals(color, queen.getColor());
        assertEquals(row, queen.getRow());
        assertEquals(column, queen.getColumn());
    }

    @Test
    @DisplayName("Test Queen getValidMoves without other pieces")
    void testGetValidMovesNoOtherPieces() {
        int numberOfMoves = 23;
        assertEquals(numberOfMoves, queen.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test Queen getValidMoves with an opposite colored pieces")
    void testGetValidMovesWithEnemyPiece() {
        int x = 6;
        int y = 3;
        Rook enemyRook = new Rook(Color.BLACK, x, y);
        board.setPosition(x, y, enemyRook.getSymbol());
        int numberOfMoves = 19;
        assertEquals(numberOfMoves, queen.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test Queen getValidMoves a same colored piece")
    void testGetValidMovesWithFriendlyPiece() {
        int x = 6;
        int y = 3;
        Rook friendlyRook = new Rook(Color.WHITE, x, y);
        board.setPosition(x, y, friendlyRook.getSymbol());
        int numberOfMoves = 18;
        assertEquals(numberOfMoves, queen.getValidMoves(board).size());
    }
}
