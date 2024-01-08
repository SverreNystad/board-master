package board.master.model.games.chess.Pieces;

import java.util.ArrayList;
import java.util.List;

import board.master.model.Action;
import board.master.model.games.Board;
import board.master.model.games.Move;
import board.master.model.games.chess.Color;

public class Bishop extends Piece {

    public Bishop(Color color, int row, int column) {
        super(color, row, column);
        super.symbol = "B";
    }

    public List<Action> getValidMoves(Board board) {
        List<Action> actions = new ArrayList<Action>();

        //test for diagonal moves up and left
        int up = this.row - 1;
        int left = this.column - 1;
        while (up >= 0 && left >= 0) {
            if (board.getPosition(up, left).equals("")) {
                actions.add((Action) new Move(up, left));
            } else if (board.getPosition(up, left).charAt(1) == oppositeColor()) {
                actions.add((Action) new Move(up, left));
                break;
            } else {
                break;
            }
            up--;
            left--;
        }

        //test for diagonal moves up and right
        up = this.row - 1;
        int right = this.column + 1;
        while (up >= 0 && right <= 7) {
            if (board.getPosition(up, right).equals("")) {
                actions.add((Action) new Move(up, right));
            } else if (board.getPosition(up, right).charAt(1) == oppositeColor()) {
                actions.add((Action) new Move(up, right));
                break;
            } else {
                break;
            }
            up--;
            right++;
        }

        //test for diagonal moves down and left
        int down = this.row + 1;
        left = this.column - 1;
        while (down <= 7 && left >= 0) {
            if (board.getPosition(down, left).equals("")) {
                actions.add((Action) new Move(down, left));
            } else if (board.getPosition(down, left).charAt(1) == oppositeColor()) {
                actions.add((Action) new Move(down, left));
                break;
            } else {
                break;
            }
            down++;
            left--;
        }

        //test for diagonal moves down and right
        down = this.row + 1;
        right = this.column + 1;
        while (down <= 7 && right <= 7) {
            if (board.getPosition(down, right).equals("")) {
                actions.add((Action) new Move(down, right));
            } else if (board.getPosition(down, right).charAt(1) == oppositeColor()) {
                actions.add((Action) new Move(down, right));
                break;
            } else {
                break;
            }
            down++;
            right++;
        }

        return actions;
    }

}
