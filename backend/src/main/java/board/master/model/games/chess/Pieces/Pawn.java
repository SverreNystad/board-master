package board.master.model.games.chess.Pieces;

import board.master.model.games.Board;
import board.master.model.games.Move;
import board.master.model.games.chess.Color;

import java.util.ArrayList;
import java.util.List;

import board.master.model.Action;

public class Pawn extends Piece {

    public Pawn(Color color, int row, int column) {
        super(color, row, column);
        super.symbol = "P";
    }

    public List<Action> getValidMoves(Board board) {
        List<Action> actions = new ArrayList<Action>();

        int forwardDirection = (this.color == Color.WHITE) ? -1 : 1;

        if ((this.color == Color.BLACK && this.row == 7) || (this.color == Color.WHITE && this.row == 0)) {
            return actions;
        }

        if (checkMove(board, this.row + forwardDirection, this.column, new ArrayList<Action>())) {
            actions.add((Action) new Move(this.row + forwardDirection, this.column));
        }

        int[] possibleColumns = {this.column - 1, this.column + 1};

        for (int newColumn : possibleColumns) {
            if (!checkMove(board, this.row + forwardDirection, newColumn, new ArrayList<Action>())) {
                checkMove(board, this.row + forwardDirection, newColumn, actions);
            }
        }
        return actions;
    }
}
