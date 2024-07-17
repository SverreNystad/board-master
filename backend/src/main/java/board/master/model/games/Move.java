package board.master.model.games;

import board.master.model.Action;

public class Move extends Action {

    private final String x; // e.g., "e2"
    private final String y; // e.g., "e4"
    private static final String sentinel = "BLANK-INPUT";

    public Move(Integer x) {
        this.x = Integer.toString(x);
        this.y = sentinel;
    }

    public Move(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public Move(Integer x, Integer y) {
        this.x = Integer.toString(x);
        this.y = Integer.toString(y);
    }

    // Factory method to create instances of Move
    public static Move createMove(String x, String y) {
        if (y.equals(sentinel)) {
            return new Move(x, sentinel);
        } else if (x.equals(sentinel)) {
            return new Move(sentinel, y);
        } else {
            return new Move(x, y);
        }
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Move)) {
            return false;
        }

        if (this.y.equals(sentinel)) {
            Move otherMove = (Move) obj;
            return this.x.equals(otherMove.getX());
        }
        if (this.x.equals(sentinel)) {
            Move otherMove = (Move) obj;
            return this.y.equals(otherMove.getY());
        }
        Move otherMove = (Move) obj;
        return this.x.equals(otherMove.getX()) && this.y.equals(otherMove.getY());
    }
}
