package board.master.model.games.chess.Pieces;

import java.util.List;
import java.util.ArrayList;

import board.master.model.Action;
import board.master.model.games.Board;
import board.master.model.games.chess.Color;

public class King extends Piece {


    public King(Color color, int row, int column) {
        super(color, row, column);
        super.symbol = "K";
        super.value = 100;
    }

    public List<Action> getValidMoves(Board board) {
        List<Action> actions = new ArrayList<Action>();

        //test the row in front of the king
        checkMove(board, this.row - 1, this.column - 1, actions);
        checkMove(board, this.row - 1, this.column, actions);
        checkMove(board, this.row - 1, this.column + 1, actions);

        //test the row the king is on
        checkMove(board, this.row, this.column - 1, actions);
        checkMove(board, this.row, this.column + 1, actions);

        //test the row behind the king
        checkMove(board, this.row + 1, this.column - 1, actions);
        checkMove(board, this.row + 1, this.column, actions);
        checkMove(board, this.row + 1, this.column + 1, actions);

        return actions;
    }

    /**
     * Creates a copy of the King
     * @return a copy of the King
     */
    public Piece copy() {
        return new King(this.color, this.row, this.column);
    }
}
