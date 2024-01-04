package board.master.model.games.chess.Pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        String symbol = "P";
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
        List<Action> list = wronglyPlacedPawn.getValidMoves(board);
        assertEquals(validMoves, wronglyPlacedPawn.getValidMoves(board).size());
    }

    @Test
    void testIsValidMove() {

    }
}
