package board.master.model.games.chess.Pieces;

import board.master.model.games.Board;
import board.master.model.games.Move;
import board.master.model.games.chess.Color;

import java.util.ArrayList;
import java.util.List;

import board.master.model.Action;

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
    }

    /**
     * {@inheritDoc}
     */
    public List<Action> getValidMoves(Board board) {
        List<Action> actions = new ArrayList<Action>();

        int forwardDirection = (this.color == Color.WHITE) ? -1 : 1;

        if ((this.color == Color.BLACK && this.row == 7) || (this.color == Color.WHITE && this.row == 0)) {
            return actions;
        }

        if (checkEmptySpace(board, this.row + forwardDirection, this.column)) {
            actions.add((Action) new Move(this.row + forwardDirection, this.column));

            if ((this.color == Color.BLACK && this.row == 1) 
                || (this.color == Color.WHITE && this.row == 6)) {
                if (checkEmptySpace(board, this.row + 2*forwardDirection, this.column)) {
                    actions.add((Action) new Move(this.row + 2*forwardDirection, this.column));
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
}
