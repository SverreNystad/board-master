package board.master.model.games.chess.Pieces;

import java.util.ArrayList;
import java.util.List;

import board.master.model.Action;
import board.master.model.games.Board;
import board.master.model.games.Move;
import board.master.model.games.chess.Color;

public class Knight extends Piece {
    
    public Knight(Color color, int row, int column) {
        super(color, row, column);
        super.symbol = "H";
    }

    public List<Action> getValidMoves(Board board) {
        List<Action> actions = new ArrayList<Action>();

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


}
