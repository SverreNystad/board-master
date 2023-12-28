package board.master.model; 

import board.master.model.Action;

public class Move extends Action {
    
    private final String from; // e.g., "e2"
    private final String to;   // e.g., "e4"
    
    public Move(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}