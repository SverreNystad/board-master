package board.master.model.games.chess.Pieces;

import java.util.ArrayList;
import java.util.List;

import board.master.model.Action;
import board.master.model.games.Board;
import board.master.model.games.chess.Color;

/**
 * A class representing the chess piece Bishop.
 * This class extends the abstract class Piece
 */
public class Bishop extends Piece {

    /**
     * Creates a new Bishop
     * @param color     the color of the Bishop
     * @param row       the row of the Bishop
     * @param column    the column of the Bishop
     */
    public Bishop(Color color, int row, int column) {
        super(color, row, column);
        super.symbol = "B";
        super.value = 3;
    }

    /**
     * {@inheritDoc}
     */
    public List<Action> getValidMoves(Board board) {
        List<Action> actions = new ArrayList<Action>();

        // test for diagonal moves up and left
        int up = this.row;
        int left = this.column;
        do {
            up--;
            left--;
            checkMove(board, up, left, actions);
        } while (checkEmptySpace(board, up, left));

        // test for diagonal moves up and right
        up = this.row;
        int right = this.column;
        do {
            up--;
            right++;
            checkMove(board, up, right, actions);
        } while (checkEmptySpace(board, up, right));

        // test for diagonal moves down and left
        int down = this.row;
        left = this.column;
        do {
            down++;
            left--;
            checkMove(board, down, left, actions);
        } while (checkEmptySpace(board, down, left));

        // test for diagonal moves down and right
        down = this.row;
        right = this.column;
        do {
            down++;
            right++;
            checkMove(board, down, right, actions);
        } while (checkEmptySpace(board, down, right));

        return actions;
    }

    /**
     * Creates a copy of the Bishop
     * @return a copy of the Bishop
     */
    public Piece copy() {
        return new Bishop(this.color, this.row, this.column);
    }
}
