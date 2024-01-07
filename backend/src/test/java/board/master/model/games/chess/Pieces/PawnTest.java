package board.master.model.games.chess.Pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import board.master.model.Action;
import board.master.model.games.Board;
import board.master.model.games.Move;
import board.master.model.games.chess.Color;

public class PawnTest {
    private Pawn pawn;
    private Board board;

    @BeforeEach
    void pawnSetUp() {
        pawn = new Pawn(Color.WHITE, 6, 1);
        board = new Board(8, 8);
    }
    @Test
    @DisplayName("Test Pawn constructor")
    void testGetColor() {
        Color color = Color.WHITE;
        int row = 6;
        int column = 1;
        assertEquals(color, pawn.getColor());
        assertEquals(row, pawn.getRow());
        assertEquals(column, pawn.getColumn());
    }

    @Test
    @DisplayName("Test Pawn Symbol")
    void testGetSymbol() {
        String symbol = "PW";
        assertEquals(symbol, pawn.getSymbol());
    }

    @Test
    @DisplayName("Test Pawn isValidMove without other pieces")
    void testGetValidMoveWithoutOtherPieces() {
        String x = "5";
        String y = "1";
        Move move = (Move) pawn.getValidMoves(board).get(0);
        assertEquals(x, move.getX());
        assertEquals(y, move.getY());
    }

    @Test
    @DisplayName("Test Pawn isValidMove when standing at the far end of board")
    void testGetValidMoveWhenAtTheEndOfBoard() {
        Pawn wronglyPlacedPawn = new Pawn(Color.WHITE, 0, 1);
        int validMoves = 0;
        assertEquals(validMoves, wronglyPlacedPawn.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test Pawn isValidMove when in front of opposite colored piece")
    void testGetValidMoveWhenInFrontOfOppositePiece() {
        int x = 5;
        int y = 1;
        Pawn oppositePawn = new Pawn(Color.BLACK, x, y);
        board.setPosition(x, y, oppositePawn.getSymbol());
        int validMoves = 0;
        assertEquals(validMoves, pawn.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test Pawn isValidMove when in front of friendly piece")
    void testGetValidMoveWhenInFrontOfFriendlyPiece() {
        int x = 5;
        int y = 1;
        Pawn oppositePawn = new Pawn(Color.WHITE, x, y);
        board.setPosition(x, y, oppositePawn.getSymbol());
        int validMoves = 0;
        List<Action> list = pawn.getValidMoves(board);
        assertEquals(validMoves, pawn.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test Pawn isValidMove when opposite colored piece is diagonal to it")
    void testGetValidMoveWhenOppositePieceIsDiagonal() {
        int x = 5;
        int y = 0;
        Pawn oppositePawn = new Pawn(Color.BLACK, x, y);
        board.setPosition(x, y, oppositePawn.getSymbol());
        int validMoves = 2;
        List<Action> list = pawn.getValidMoves(board);
        assertEquals(validMoves, pawn.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test Pawn isValidMove when friendly piece is diagonal to it")
    void testGetValidMoveWhenFriendlyPieceIsDiagonal() {
        int x = 5;
        int y = 0;
        Pawn oppositePawn = new Pawn(Color.WHITE, x, y);
        board.setPosition(x, y, oppositePawn.getSymbol());
        int validMoves = 1;
        List<Action> list = pawn.getValidMoves(board);
        assertEquals(validMoves, pawn.getValidMoves(board).size());
    }


    @Test
    @DisplayName("Test Pawn isValidMove when true")
    void testIsValidMoveWhenTrue() {
        int x = 5;
        int y = 1;
        
        assertTrue(pawn.isValidMove(x, y, board));
    }

    @Test
    @DisplayName("Test Pawn isValidMove when false")
    void testIsValidMoveWhenFalse() {
        int x = 3;
        int y = 1;
        
        assertFalse(pawn.isValidMove(x, y, board));
    }

    @Test
    @DisplayName("Test Pawn move when valid move")
    void testMoveValidMove() {
        int x = 5;
        int y = 1;
        pawn.move(x, y, board);
        assertEquals(x, pawn.getRow());
        assertEquals(y, pawn.getColumn());
    }

    @Test
    @DisplayName("Test Pawn move when invalid move")
    void testMoveInvalidMove() {
        int x = 3;
        int y = 1;
        assertThrows(IllegalArgumentException.class, () -> pawn.move(x, y, board));
    }
}
