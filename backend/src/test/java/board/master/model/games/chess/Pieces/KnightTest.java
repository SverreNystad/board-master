package board.master.model.games.chess.Pieces;

import board.master.model.games.Board;
import board.master.model.games.chess.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightTest {
    private Knight knight;
    private Board board;

    @BeforeEach
    void bishopSetUp() {
        knight = new Knight(Color.WHITE, 6, 1);
        board = new Board(8, 8);
    }

    @Test
    @DisplayName("Test Knight constructor")
    void testConstructor() {
        Color color = Color.WHITE;
        int row = 6;
        int column = 1;
        assertEquals(color, knight.getColor());
        assertEquals(row, knight.getRow());
        assertEquals(column, knight.getColumn());
    }

    @Test
    @DisplayName("Test Knight without other pieces and placed in the corner")
    void testGetValidMovesWithoutOtherPiecesAndPlacedInCorner() {
        int numberOfMoves = 4;
        assertEquals(numberOfMoves, knight.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test Knight without other pieces and placed in the middle")
    void testGetValidMovesWithoutOtherPiecesAndPlacedMiddle() {
        knight = new Knight(Color.WHITE, 3, 3);
        int numberOfMoves = 8;
        assertEquals(numberOfMoves, knight.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test Knight getValidMoves with an opposite colored piece")
    void testGetValidMovesWithEnemyPiece() {
        int x = 4;
        int y = 2;
        Knight oppositeKnight = new Knight(Color.BLACK, x, y);
        board.setPosition(x, y, oppositeKnight.getSymbol());
        int numberOfMoves = 4;
        assertEquals(numberOfMoves, knight.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test Knight getValidMoves with an same colored piece")
    void testGetValidMovesWithFriendlyPiece() {
        int x = 4;
        int y = 2;
        Knight oppositeKnight = new Knight(Color.WHITE, x, y);
        board.setPosition(x, y, oppositeKnight.getSymbol());
        int numberOfMoves = 3;
        assertEquals(numberOfMoves, knight.getValidMoves(board).size());
    }
}
