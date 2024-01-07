package board.master.model.games.chess.Pieces;

import java.util.List;

import board.master.model.Action;
import board.master.model.games.Board;
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

    public abstract boolean isValidMove(int row, int column, Board board);

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

    public abstract String getSymbol();

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
