package board.master.model.games.connect_four;

import java.util.ArrayList;
import java.util.List;

import board.master.model.Action;
import board.master.model.StateHandler;
import board.master.model.games.Board;
import board.master.model.games.Move;

public class ConnectFour implements StateHandler {

    private int playerToMove;

    public ConnectFour() {
        playerToMove = 1;
    }

    @Override
    public int toMove() {
        return playerToMove;
    }

    @Override
    public List<Action> getActions() {
        List<Action> actions = new ArrayList<Action>();
        for (int i = 0; i < 7; i++) {
            actions.add(new Move(i));
        }
        return actions;
    }

    @Override
    public StateHandler result(Action action) {
        this.playerToMove *= -1;
        return null;
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
