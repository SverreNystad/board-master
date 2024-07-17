package board.master.model.games.chess.Pieces;

import java.util.ArrayList;
import java.util.List;

import board.master.model.Action;
import board.master.model.games.Board;
import board.master.model.games.Move;
import board.master.model.games.chess.Color;

/**
 * A class representing the chess piece Pawn.
 * This class extends the abstract class Piece
 */
public class Pawn extends Piece {

    /**
     * Creates a new Pawn
     * @param color     the color of the Pawn
     * @param row       the row of the Pawn
     * @param column    the column of the Pawn
     */
    public Pawn(Color color, int row, int column) {
        super(color, row, column);
        super.symbol = "P";
        super.value = 1;
    }

    /**
     * {@inheritDoc}
     */
    public List<Action> getValidMoves(Board board) {
        List<Action> actions = new ArrayList<Action>();
        // start rows of pawns
        int whitePawnRow = 6;
        int blackPawnRow = 1;

        // end rows of pawns
        int whitePawnMaxRow = 0;
        int blackPawnMaxRow = 7;

        int forwardDirection = (this.color == Color.WHITE) ? -1 : 1;

        // If the pawn is at the end of the board, it cannot move
        if ((this.color == Color.BLACK && this.row == blackPawnMaxRow)
                || (this.color == Color.WHITE && this.row == whitePawnMaxRow)) {
            return actions;
        }

        // If the pawn is not at the end of the board, it can move forward one space
        if (checkEmptySpace(board, this.row + forwardDirection, this.column)) {
            String currentPos = String.valueOf(this.row) + String.valueOf(this.column);
            String newPos = String.valueOf(this.row + forwardDirection) + String.valueOf(this.column);
            actions.add((Action) new Move(currentPos, newPos));

            // If the pawn is at the start of the board, it can move one or two spaces
            if ((this.color == Color.BLACK && this.row == blackPawnRow)
                    || (this.color == Color.WHITE && this.row == whitePawnRow)) {
                if (checkEmptySpace(board, this.row + 2 * forwardDirection, this.column)) {

                    currentPos = String.valueOf(this.row) + String.valueOf(this.column);
                    newPos = String.valueOf(this.row + 2 * forwardDirection) + String.valueOf(this.column);
                    actions.add((Action) new Move(currentPos, newPos));
                }
            }
        }

        int[] possibleColumns = {this.column - 1, this.column + 1};

        for (int newColumn : possibleColumns) {
            if (!checkEmptySpace(board, this.row + forwardDirection, newColumn)) {
                checkMove(board, this.row + forwardDirection, newColumn, actions);
            }
        }

        return actions;
    }

    /**
     * Creates a copy of the Pawn
     * @return a copy of the Pawn
     */
    public Piece copy() {
        return new Pawn(this.color, this.row, this.column);
    }
}
