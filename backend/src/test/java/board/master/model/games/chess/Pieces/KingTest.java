package board.master.model.games.chess.Pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import board.master.model.games.Board;
import board.master.model.games.chess.Color;

public class KingTest {
    private King king;
    private Board board;

    @BeforeEach
    void rookSetUp() {
        king = new King(Color.WHITE, 6, 1);
        board = new Board(8, 8);
    }

    @Test
    @DisplayName("Test King constructor")
    void testConstructor() {
        Color color = Color.WHITE;
        int row = 6;
        int column = 1;
        assertEquals(color, king.getColor());
        assertEquals(row, king.getRow());
        assertEquals(column, king.getColumn());
    }
    @Test
    @DisplayName("Test King getValidMoves without other pieces")
    void testGetValidMovesNoOtherPieces() {
        int numberOfMoves = 8;
        assertEquals(numberOfMoves, king.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test King getValidMoves with an opposite colored pieces")
    void testGetValidMovesWithEnemyPiece() {
        int x = 6;
        int y = 2;
        Rook enemyRook = new Rook(Color.BLACK, x, y);
        board.setPosition(x, y, enemyRook.getSymbol());
        int numberOfMoves = 8;
        assertEquals(numberOfMoves, king.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test King getValidMoves a same colored piece")
    void testGetValidMovesWithFriendlyPiece() {
        int x = 6;
        int y = 2;
        Rook friendlyRook = new Rook(Color.WHITE, x, y);
        board.setPosition(x, y, friendlyRook.getSymbol());
        int numberOfMoves = 7;
        assertEquals(numberOfMoves, king.getValidMoves(board).size());
    }
}
