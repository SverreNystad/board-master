package board.master.model.games;

import board.master.model.Action;

public class Move extends Action {
    
    private final String x; // e.g., "e2"
    private final String y;   // e.g., "e4"
    private final static String sentinel = "BLANK-INPUT";

    public Move(Integer x) {
        this.x = Integer.toString(x);
        this.y = null;
    }
    
    public Move(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public Move (Integer x, Integer y) {
        this.x = Integer.toString(x);
        this.y = Integer.toString(y);
    }

    // Factory method to create instances of Move
    public static Move createMove(String x, String y) {
        if (y.equals(sentinel)) {
            return new Move(x, null);
        } else if (x.equals(sentinel)) {
            return new Move(null, y);
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
        Move otherMove = (Move) obj;
        return this.x.equals(otherMove.getX()) && this.y.equals(otherMove.getY());
    }
}