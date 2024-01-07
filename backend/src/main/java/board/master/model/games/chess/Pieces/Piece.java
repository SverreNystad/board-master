package board.master.model.games.chess.Pieces;

import java.util.List;

import board.master.model.Action;
import board.master.model.games.Board;

public abstract class Piece {
    
    public enum Color {
        WHITE, BLACK
    }
    public Color color;
    public int row;
    public int column;

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
    private char oppositeColor(Color color) {
        if (color == Color.WHITE) {
            return 'B';
        }
        return 'W';
    }

    public abstract String getSymbol();

    public abstract Color getColor();

    public abstract int getRow();

    public abstract int getColumn();
}
