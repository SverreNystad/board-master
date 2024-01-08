package board.master.model.games.chess.Pieces;

import java.util.List;

import board.master.model.Action;
import board.master.model.games.Board;
import board.master.model.games.Move;
import board.master.model.games.chess.Color;

public abstract class Piece {
    public Color color;
    public int row;
    public int column;
    protected String symbol;

    public Piece(Color color, int row, int column) {
        this.color = color;
        this.row = row;
        this.column = column;
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

    public abstract List<Action> getValidMoves(Board board);

    public void move(int row, int column, Board board) throws IllegalArgumentException {
        if (!isValidMove(row, column, board)) {
            throw new IllegalArgumentException("Invalid move");
        }
        this.row = row;
        this.column = column;
    }
    protected char oppositeColor() {
        if (this.color == Color.WHITE) {
            return 'B';
        }
        return 'W';
    }

    public String getSymbol() {
        String color = (this.color == Color.WHITE) ? "W" : "B";
        return this.symbol + color;
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
