package board.master.model.games.connect_four;

import java.util.ArrayList;
import java.util.List;

import board.master.model.Action;
import board.master.model.StateHandler;
import board.master.model.games.Board;
import board.master.model.games.Move;

public class ConnectFour implements StateHandler {

    private int playerToMove;
    private Board board;
    private final int rowLength = 6;
    private final int columnHeight = 7;

    public ConnectFour() {
        playerToMove = 1;
        board = new Board(rowLength, columnHeight);
    }

    @Override
    public int toMove() {
        return playerToMove;
    }

    @Override
    public List<Action> getActions() {
        List<Action> actions = new ArrayList<Action>();
        for (int x = 0; x < rowLength; x++) {
            if (board.getPosition(x, columnHeight-1) == "") {
                actions.add(new Move(x));
            }
        }
        return actions;
    }

    @Override
    public StateHandler result(Action action) {
        this.playerToMove *= -1;
        return this;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    @Override
    public int utility(int player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'utility'");
    }

    @Override
    public Board getBoard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBoard'");
    }
    
}
