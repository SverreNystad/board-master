package board.master.model.games.chess;

import board.master.model.StateHandler;
import board.master.model.games.Board;
import board.master.model.Action;

import java.util.List;
import java.util.ArrayList;
public class Chess implements StateHandler {
    
    private Board board;
    private int toMove;

    public static Board CreateInitialBoard() {
        return new Board(8,8);
    }

    public Chess() {
        this.board = CreateInitialBoard();
        this.toMove = 1;
    }


    public Chess(Board board) {
        Board newBoard = new Board(board.getRows(), board.getColumns());
        for (int x = 0; x < board.getRows(); x++) {
            for (int y = 0; y < board.getColumns(); y++) {
                newBoard.setPosition(x, y, board.getPosition(x, y));
            }
        }
        this.board = board;
        this.toMove = 1;
    }
    
    /**
     * {@inheritDoc}
     */
    public int toMove() {
        // Should be -1 for black, 1 for white
        return this.toMove * -1;
    }

    /**
     * {@inheritDoc}
     */
    public List<Action> getActions() {
        // TODO
        return new ArrayList<Action>();
    }

    /**
     * {@inheritDoc}
     */
    public StateHandler result(Action action) {
        // TODO
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isTerminal() {
        // TODO
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public int utility(int player) {
        // TODO
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    public Board getBoard() {
        return this.board;
    }
}
