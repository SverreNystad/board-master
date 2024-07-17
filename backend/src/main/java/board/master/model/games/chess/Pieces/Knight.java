package board.master.model.games.chess.Pieces;

import java.util.ArrayList;
import java.util.List;

import board.master.model.Action;
import board.master.model.games.Board;
import board.master.model.games.chess.Color;

/**
 * A class representing the chess piece Knight.
 * This class extends the abstract class Piece
 */
public class Knight extends Piece {

    /**
     * Creates a new Knight
     * @param color     the color of the Knight
     * @param row       the row of the Knight
     * @param column    the column of the Knight
     */
    public Knight(Color color, int row, int column) {
        super(color, row, column);
        super.symbol = "H";
        super.value = 3;
    }

    /**
     * {@inheritDoc}
     */
    public List<Action> getValidMoves(Board board) {
        List<Action> actions = new ArrayList<Action>();

        // testing the possible moves row by row
        checkMove(board, this.row - 2, this.column - 1, actions);
        checkMove(board, this.row - 2, this.column + 1, actions);

        checkMove(board, this.row - 1, this.column - 2, actions);
        checkMove(board, this.row - 1, this.column + 2, actions);

        checkMove(board, this.row + 1, this.column - 2, actions);
        checkMove(board, this.row + 1, this.column + 2, actions);

        checkMove(board, this.row + 2, this.column - 1, actions);
        checkMove(board, this.row + 2, this.column + 1, actions);

        return actions;
    }

    /**
     * Creates a copy of the Knight
     * @return a copy of the Knight
     */
    public Piece copy() {
        return new Knight(this.color, this.row, this.column);
    }
}
