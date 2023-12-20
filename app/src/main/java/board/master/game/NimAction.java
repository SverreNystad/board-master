package board.master.game;

public class NimAction extends Action {
    private int take;

    public NimAction(int take) {
        this.take = take;
    }

    public int getTake() {
        return take;
    }

    @Override
    public String toString() {
        return "Take " + take;
    }
}
