package board.master.model.games.chess.Pieces;

import java.util.ArrayList;
import java.util.List;

import board.master.model.Action;
import board.master.model.games.Board;
import board.master.model.games.Move;

public class Rook extends Piece {
    
    public Rook(Color color, int row, int column) {
        super(color, row, column);
    }

    public boolean isValidMove(int row, int column, Board board) {
        for (Action action : getValidMoves(board)) {
            Move move = (Move) action;
            if (move.getX().equals(String.valueOf(row))
                && move.getY().equals(String.valueOf(column))) {
                return true;
            }
        }
        return false;
    }

    public List<Action> getValidMoves(Board board) {
        List<Action> actions = new ArrayList<Action>();

        //test for horizontal moves left
        int left = this.column - 1;
        while (left >= 0) {
            if (board.getPosition(this.row, left).equals("")) {
                actions.add((Action) new Move(this.row, left));
            } else if (board.getPosition(this.row, left).charAt(1) == oppositeColor(this.color)) {
                actions.add((Action) new Move(this.row, left));
                break;
            } else {
                break;
            }
            left--;
        }

        //test for horizontal moves right
        int right = this.column + 1;
        while (right <= 7) {
            if (board.getPosition(this.row, right).equals("")) {
                actions.add((Action) new Move(this.row, right));
            } else if (board.getPosition(this.row, right).charAt(1) == oppositeColor(this.color)) {
                actions.add((Action) new Move(this.row, right));
                break;
            } else {
                break;
            }
            right++;
        }

        //test for vertical moves up
        int up = this.row - 1;
        while (up >= 0) {
            if (board.getPosition(up, this.column).equals("")) {
                actions.add((Action) new Move(up, this.column));
            } else if (board.getPosition(up, this.column).charAt(1) == oppositeColor(this.color)) {
                actions.add((Action) new Move(up, this.column));
                break;
            } else {
                break;
            }
            up--;
        }

        //test for vertical moves down
        int down = this.row + 1;
        while (down <= 7) {
            if (board.getPosition(down, this.column).equals("")) {
                actions.add((Action) new Move(down, this.column));
            } else if (board.getPosition(down, this.column).charAt(1) == oppositeColor(this.color)) {
                actions.add((Action) new Move(down, this.column));
                break;
            } else {
                break;
            }
            down++;
        }
        return actions;
    }

    public void move(int row, int column, Board board) throws IllegalArgumentException {
        if (!isValidMove(row, column, board)) {
            throw new IllegalArgumentException("Invalid move");
        }
        this.row = row;
        this.column = column;
    }
    private char oppositeColor(Color color) {
        if (color == Color.WHITE) {
            return 'B';
        }
        return 'W';
    }

    public String getSymbol() {
        String color = (this.color == Color.WHITE) ? "W" : "B";
        return "R" + color;
    }

    public Color getColor() {
        return this.color;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }
    
}
