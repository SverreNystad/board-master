package board.master.model.games.chess.Pieces;

import java.util.ArrayList;
import java.util.List;

import board.master.model.Action;
import board.master.model.games.Board;
import board.master.model.games.Move;
import board.master.model.games.chess.Color;

public class Rook extends Piece {
    
    public Rook(Color color, int row, int column) {
        super(color, row, column);
        super.symbol = "R";
    }

    public List<Action> getValidMoves(Board board) {
        List<Action> actions = new ArrayList<Action>();

        //test for horizontal moves left
        int left = this.column - 1;
        while (checkMove(board, this.row, left, actions)) {
            left--;
        }

        //test for horizontal moves right
        int right = this.column + 1;
        while (checkMove(board, this.row, right, actions)) {
            right++;
        }

        //test for vertical moves up
        int up = this.row - 1;
        while (checkMove(board, up, this.column, actions)) {
            up--;
        }

        //test for vertical moves down
        int down = this.row + 1;
        while (checkMove(board, down, this.column, actions)) {
            down++;
        }
        return actions;
    }  
}
