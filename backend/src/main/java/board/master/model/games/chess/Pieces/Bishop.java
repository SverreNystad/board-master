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
    }

    /**
     * {@inheritDoc}
     */
    public List<Action> getValidMoves(Board board) {
        List<Action> actions = new ArrayList<Action>();

        //test for diagonal moves up and left
        int up = this.row - 1;
        int left = this.column - 1;
        while (checkMove(board, up, left, actions)) {
            up--;
            left--;
        }

        //test for diagonal moves up and right
        up = this.row - 1;
        int right = this.column + 1;
        while (checkMove(board, up, right, actions)) {
            up--;
            right++;
        }

        //test for diagonal moves down and left
        int down = this.row + 1;
        left = this.column - 1;
        while (checkMove(board, down, left, actions)) {
            down++;
            left--;
        }

        //test for diagonal moves down and right
        down = this.row + 1;
        right = this.column + 1;
        while (checkMove(board, down, right, actions)) {
            down++;
            right++;
        }

        return actions;
    }
}