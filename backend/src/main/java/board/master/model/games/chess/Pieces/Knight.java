package board.master.model.games.chess.Pieces;

import java.util.ArrayList;
import java.util.List;

import board.master.model.Action;
import board.master.model.games.Board;
import board.master.model.games.Move;
import board.master.model.games.chess.Color;

public class Knight extends Piece {
    
    public Knight(Color color, int row, int column) {
        super(color, row, column);
        super.symbol = "P";
    }

    public List<Action> getValidMoves(Board board) {
        List<Action> actions = new ArrayList<Action>();

        


        return actions;
    }


}
