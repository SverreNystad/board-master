package board.master.model.games.chess.Pieces;

import java.util.List;
import java.util.logging.Logger;

import board.master.model.Action;
import board.master.model.games.Board;
import board.master.model.games.Move;
import board.master.model.games.chess.Color;

/**
 * Abstract class for a chess piece
 */
public abstract class Piece {
    private final Logger logger = Logger.getLogger(getClass().getName());

    public final Color color;
    public int row;
    public int column;
    protected String symbol;
    private static final int colorIndex = 1;
    protected int value;

    /**
     * Creates a new piece
     * @param color     the color of the piece
     * @param row       the row of the piece
     * @param column    the column of the piece
     */
    public Piece(Color color, int row, int column) {
        this.color = color;
        this.row = row;
        this.column = column;
    }

    /**
     * Checks if the move is valid
     * @param row       the row of the move
     * @param column    the column of the move
     * @param board     the board with the current game state
     * @return          true if the move is valid, false otherwise
     */
    public boolean isValidMove(int row, int column, Board board) {
        for (Action action : getValidMoves(board)) {
            Move move = (Move) action;
            String newPos = move.getY();
            String x = String.valueOf(newPos.charAt(0));
            String y = String.valueOf(newPos.charAt(1));
            if (x.equals(String.valueOf(row)) && y.equals(String.valueOf(column))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the position is valid within the confines of a chess board
     * @param row   the row of the position
     * @param col   the column of the position
     * @return      true if the position is valid, false otherwise
     */
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row <= 7 && col >= 0 && col <= 7;
    }

    /**
     * Returns a list of valid moves for the piece
     * @param board   the board with the current game state
     * @return        a list of valid moves
     */
    public abstract List<Action> getValidMoves(Board board);

    /**
     * Moves the piece to a new position
     * Removes the piece from the old position and adds it to the new position
     * @param row
     * @param column
     * @param board
     * @throws IllegalArgumentException
     */
    public void move(int row, int column, Board board) throws IllegalArgumentException {
        if (!isValidMove(row, column, board)) {
            logger.severe("Invalid move: " + String.valueOf(row) + String.valueOf(column) + " for piece: " + getSymbol()
                    + " at position: " + String.valueOf(this.row) + String.valueOf(this.column));
            throw new IllegalArgumentException("Invalid move");
        }
        // Delete old position
        board.setPosition(this.row, this.column, "");
        // Set new position
        this.row = row;
        this.column = column;
        board.setPosition(row, column, getSymbol());
    }

    /**
     * Checks for valid moves in a given direction
     * @param board     the board with the current game state
     * @param row       the row of the piece
     * @param col       the column of the piece
     * @param actions   the list of valid moves
     */
    protected void checkMove(Board board, int row, int col, List<Action> actions) {
        if (isValidPosition(row, col)) {
            String position = board.getPosition(row, col);
            String currentPos = String.valueOf(this.row) + String.valueOf(this.column);
            String newPos = String.valueOf(row) + String.valueOf(col);

            if (checkEmptySpace(board, row, col)) {
                actions.add((Action) new Move(currentPos, newPos));
            } else if (position.charAt(colorIndex) == oppositeColor()) {
                actions.add((Action) new Move(currentPos, newPos));
            }
        }
    }

    public boolean checkEmptySpace(Board board, int row, int col) {
        if (isValidPosition(row, col)) {
            String position = board.getPosition(row, col);

            if (position.equals("")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the opposite color of the piece
     * @return A char representing the opposite color
     */
    protected char oppositeColor() {
        if (this.color == Color.WHITE) {
            return 'B';
        }
        return 'W';
    }

    /**
     * Returns the symbol of the piece, existing of the symbol and the color
     * @return  A string representing the symbol of the piece
     */
    public String getSymbol() {
        String color = (this.color == Color.WHITE) ? "W" : "B";
        return this.symbol + color;
    }

    /**
     * Returns the color of the piece
     * @return A Color enum representing the color of the piece
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Returns the row of the piece
     * @return An int representing the row of the piece
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Returns the column of the piece
     * @return An int representing the column of the piece
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Returns the value of the piece
     * @return An int representing the value of the piece
     */
    public int getValue() {
        return this.value;
    }

    public abstract Piece copy();
}
