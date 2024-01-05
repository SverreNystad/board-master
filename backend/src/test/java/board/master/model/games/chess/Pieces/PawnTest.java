package board.master.model.games.chess.Pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import board.master.model.Action;
import board.master.model.games.Board;
import board.master.model.games.Move;
import board.master.model.games.chess.Pieces.Piece.Color;

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
        assertEquals(color, pawn.getColor());
    }

    @Test
    @DisplayName("Test Pawn Symbol")
    void testGetSymbol() {
        String symbol = "PW";
        assertEquals(symbol, pawn.getSymbol());
    }

    @Test
    @DisplayName("Test Pawn isValidMove without other pieces")
    void testGetValidMoves() {
        String x = "5";
        String y = "1";
        Move move = (Move) pawn.getValidMoves(board).get(0);
        assertEquals(x, move.getX());
        assertEquals(y, move.getY());
    }

    @Test
    @DisplayName("Test Pawn isValidMove when standing at the far end of board")
    void testGetValidMoves1() {
        Pawn wronglyPlacedPawn = new Pawn(Color.WHITE, 0, 1);
        int validMoves = 0;
        assertEquals(validMoves, wronglyPlacedPawn.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test Pawn isValidMove when in front of black piece")
    void testGetValidMoves2() {
        int x = 5;
        int y = 1;
        Pawn oppositePawn = new Pawn(Color.BLACK, x, y);
        board.setPosition(x, y, oppositePawn.getSymbol());
        int validMoves = 0;
        assertEquals(validMoves, pawn.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test Pawn isValidMove when in front of white piece")
    void testGetValidMoves3() {
        int x = 5;
        int y = 1;
        Pawn oppositePawn = new Pawn(Color.WHITE, x, y);
        board.setPosition(x, y, oppositePawn.getSymbol());
        int validMoves = 0;
        List<Action> list = pawn.getValidMoves(board);
        assertEquals(validMoves, pawn.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test Pawn isValidMove when black piece is diagonal to it")
    void testGetValidMoves4() {
        int x = 5;
        int y = 0;
        Pawn oppositePawn = new Pawn(Color.BLACK, x, y);
        board.setPosition(x, y, oppositePawn.getSymbol());
        int validMoves = 2;
        List<Action> list = pawn.getValidMoves(board);
        assertEquals(validMoves, pawn.getValidMoves(board).size());
    }

    @Test
    @DisplayName("Test Pawn isValidMove when white piece is diagonal to it")
    void testGetValidMoves5() {
        int x = 5;
        int y = 0;
        Pawn oppositePawn = new Pawn(Color.WHITE, x, y);
        board.setPosition(x, y, oppositePawn.getSymbol());
        int validMoves = 1;
        List<Action> list = pawn.getValidMoves(board);
        assertEquals(validMoves, pawn.getValidMoves(board).size());
    }


    @Test
    @DisplayName("Test Pawn isValidMove")
    void testIsValidMove() {
        int x = 5;
        int y = 1;
        
        assertTrue(pawn.isValidMove(x, y, board));
    }
}
