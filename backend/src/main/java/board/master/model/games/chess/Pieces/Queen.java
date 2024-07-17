package board.master.model.games.chess.Pieces;

import java.util.ArrayList;
import java.util.List;

import board.master.model.Action;
import board.master.model.games.Board;
import board.master.model.games.chess.Color;

public class Queen extends Piece {

    public Queen(Color color, int row, int column) {
        super(color, row, column);
        super.symbol = "Q";
        super.value = 9;
    }

    public List<Action> getValidMoves(Board board) {
        List<Action> actions = new ArrayList<Action>();

        Rook rook = new Rook(this.color, this.row, this.column);
        actions.addAll(rook.getValidMoves(board));

        Bishop bishop = new Bishop(this.color, this.row, this.column);
        actions.addAll(bishop.getValidMoves(board));

        return actions;
    }

    /**
     * Creates a copy of the Queen
     * @return a copy of the Queen
     */
    public Piece copy() {
        return new Queen(this.color, this.row, this.column);
    }
}
