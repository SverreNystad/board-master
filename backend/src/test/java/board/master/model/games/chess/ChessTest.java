package board.master.model.games.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import board.master.model.games.Board;

public class ChessTest {
    private Chess chess;
    private Board board;

    @BeforeEach
    void ChessSetUp() {
        chess = new Chess();
        board = Chess.CreateInitialBoard();
    }

    @Test
    void testCreateInitialBoard() {
        Board emptyBoard = new Board(8, 8);
        assertEquals(emptyBoard, board);
    }

    @Test
    void testGetActions() {

    }

    @Test
    void testGetBoard() {

    }

    @Test
    void testIsTerminal() {

    }

    @Test
    void testResult() {

    }

    @Test
    void testToMove() {

    }

    @Test
    void testUtility() {

    }
}
