package board.master.model.games.chess.Pieces;

import java.util.List;
import java.util.ArrayList;

import board.master.model.Action;
import board.master.model.games.Board;
import board.master.model.games.chess.Color;

public class Queen extends Piece {

    public Queen(Color color, int row, int column) {
        super(color, row, column);
        super.symbol = "Q";
    }

    public List<Action> getValidMoves(Board board) {
        List<Action> actions = new ArrayList<Action>();

        //not right implementation
        int[] possibleRows = {1, 1, 1, 0, -1, -1, -1, 0};
        int[] possibleColumns = {-1, 0, 1, 1, 1, 0, -1, -1};

        for (int i = 0; i < possibleRows.length; i++) {
            checkMove(board, this.row + possibleRows[i], this.column + possibleColumns[i], actions);
        }

        return actions;
    }
    
}
