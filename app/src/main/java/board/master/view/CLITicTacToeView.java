package board.master.view;

import board.master.model.StateHandler;
import board.master.model.game.tic_tac_toe.TicTacToe;

/**
 * A Command Line Interface view for the Nim game
 */
public class CLITicTacToeView implements View {
    @Override
    public void updateBoard(StateHandler model) {
        TicTacToe ticTacToe = (TicTacToe) model;
        System.out.println("Current player: " + ticTacToe.toMove());
        System.out.println("Board:");
        System.out.println(ticTacToe.getBoard());
    }
}
