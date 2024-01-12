package board.master.model.games.chess.Pieces;

import java.util.ArrayList;
import java.util.List;

import board.master.model.Action;
import board.master.model.games.Board;
import board.master.model.games.chess.Color;

/**
 * A class representing the chess piece Rook.
 * This class extends the abstract class Piece
 */
public class Rook extends Piece {
    
    /**
     * Creates a new Rook
     * @param color     the color of the Rook
     * @param row       the row of the Rook
     * @param column    the column of the Rook
     */
    public Rook(Color color, int row, int column) {
        super(color, row, column);
        super.symbol = "R";
    }

    /**
     * {@inheritDoc}
     */
    public List<Action> getValidMoves(Board board) {
        List<Action> actions = new ArrayList<Action>();

        //test for horizontal moves left
        int left = this.column;
        do {
            left--;
            checkMove(board, this.row, left, actions);
        } while (checkEmptySpace(board, this.row, left));

        //test for horizontal moves right
        int right = this.column;
        do {
            right++;
            checkMove(board, this.row, right, actions);
        } while (checkEmptySpace(board, this.row, right));

        //test for vertical moves up
        int up = this.row;
        do {
            up--;
            checkMove(board, up, this.column, actions);
        } while (checkEmptySpace(board, up, this.column));

        //test for vertical moves down
        int down = this.row;
        do {
            down++;
            checkMove(board, down, this.column, actions);
        } while (checkEmptySpace(board, down, this.column));

        return actions;
    }  

    /**
     * Creates a copy of the Rook
     * @return a copy of the Rook
     */
    public Piece copy() {
        return new Rook(this.color, this.row, this.column);
    }
}
