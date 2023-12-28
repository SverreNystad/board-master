package board.master.model.games.chess;

import board.master.model.Board;
import board.master.model.StateHandler;
import board.master.model.Action;

import java.util.List;
import java.util.ArrayList;
public class Chess implements StateHandler {
    
    private Board board;

    public static Board CreateInitialBoard() {
        return new Board(8,8);
    }

    public Chess(Board board) {
        // TODO
        this.board = board;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int toMove() {
        // TODO
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Action> getActions() {
        // TODO
        return new ArrayList<Action>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StateHandler result(Action action) {
        // TODO
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTerminal() {
        // TODO
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int Utility() {
        // TODO
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Board getBoard() {
        return this.board;
    }
}
