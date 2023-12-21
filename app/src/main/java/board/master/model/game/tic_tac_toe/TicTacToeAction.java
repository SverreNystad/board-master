package board.master.model.game.tic_tac_toe;

import board.master.model.Action;

public class TicTacToeAction extends Action {

    private int x;
    private int y;

    public TicTacToeAction(int x, int y) throws IllegalArgumentException {
        this.x = x;
        this.y = y;
        validateMove(x, y);
    }

    private void validateMove(int x, int y) throws IllegalArgumentException {
        if (x < 0 || x > 2 || y < 0 || y > 2) {
            throw new IllegalArgumentException("Invalid move: (" + x + ", " + y + ")");
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
