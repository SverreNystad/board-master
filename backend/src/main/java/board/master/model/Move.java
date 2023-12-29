package board.master.model; 

import board.master.model.Action;

public class Move extends Action {
    
    private final String x; // e.g., "e2"
    private final String y;   // e.g., "e4"

    
    public Move(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public Move (int x, int y) {
        this.x = Integer.toString(x);
        this.y = Integer.toString(y);
    }

    

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }
}