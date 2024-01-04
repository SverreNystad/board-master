package board.master.model.games.chess.Pieces;

import board.master.model.games.Board;
import board.master.model.games.Move;

import java.util.ArrayList;
import java.util.List;

import board.master.model.Action;

public class Pawn extends Piece {

    public Pawn(Color color, int row, int column) {
        super(color, row, column);
    }

    public boolean isValidMove(int newRow, int newColumn, Board board) {
        Move newMove = new Move(newRow, newColumn);
        if (getValidMoves(board).contains(newMove)) {
            return true;
        }
        return false;
    }

    public List<Action> getValidMoves(Board board) {
        List<Action> actions = new ArrayList<Action>();
        if(this.color == Color.BLACK) {
                if (board.getPosition(this.row + 1, this.column).equals("")) {
                    actions.add((Action) new Move(this.row + 1, this.column));
                }
                for (int i = 0; i < 3; i+= 2) {
                    if (board.getPosition(this.row + 1, this.column - 1 + i).equals(Piece.Color.WHITE)) {
                        actions.add((Action) new Move(this.row + 1, this.column - 1 + i));
                    }
                }
        } else {
            if (board.getPosition(this.row - 1, this.column).equals("")) {
                    actions.add((Action) new Move(this.row - 1, this.column));
            }
            for (int i = 0; i < 3; i+= 2) {
                if (board.getPosition(this.row - 1, this.column - 1 + i).equals(Piece.Color.BLACK)) {
                    actions.add((Action) new Move(this.row - 1, this.column - 1 + i));
                }
            }
        }
        return actions;
    }


    public String getSymbol() {
        return "R";
    }

    public Color getColor() {
        return this.color;
    }
}
