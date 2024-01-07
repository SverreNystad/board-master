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

    public boolean isValidMove(int newRow, int newColumn, Board board) {
        for (Action action : getValidMoves(board)) {
            Move move = (Move) action;
            if (move.getX().equals(String.valueOf(newRow))
                && move.getY().equals(String.valueOf(newColumn))) {
                return true;
            }
        }
        return false;
    }

    public List<Action> getValidMoves(Board board) {
        List<Action> actions = new ArrayList<Action>();

        int forwardDirection = (this.color == Color.WHITE) ? -1 : 1;

        if ((this.color == Color.BLACK && this.row == 7) || (this.color == Color.WHITE && this.row == 0)) {
            return actions;
        }

        if (board.getPosition(this.row + forwardDirection, this.column).equals("")) {
            actions.add((Action) new Move(this.row + forwardDirection, this.column));
        }

        int[] possibleColumns = {this.column - 1, this.column + 1};

        for (int column : possibleColumns) {
            if (column >= 0 || column <= 7 ) {
                String position = board.getPosition(this.row + forwardDirection, column);
                
                if (!position.equals("")) {
                    if (position.length() > 1 && position.charAt(1) == oppositeColor()) {
                        actions.add((Action) new Move(this.row + forwardDirection, column));
                    }
                }
            }
        }
        return actions;
    }

    public String getSymbol() {
        String color = (this.color == Color.WHITE) ? "W" : "B";
        return this.symbol + color;
    }
}
