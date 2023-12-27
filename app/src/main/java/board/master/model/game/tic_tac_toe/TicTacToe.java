package board.master.model.game.tic_tac_toe;

import java.util.List;

import board.master.model.Action;
import board.master.model.StateHandler;

/**
 * TicTacToe class implementing the StateHandler interface.
 * This class manages the state of a Tic Tac Toe game, including the game board,
 * player turns, and checking for game over conditions.
 */
public class TicTacToe implements StateHandler {

    // Game board representation, player turn, and other necessary state variables
    // TODO: Define the game board and other state variables
    private int[][] board;
    /**
     * Constructs a new TicTacToe game with an empty board and sets the starting
     * player.
     */
    public TicTacToe() {
        this.board = new int[3][3];
        // TODO: Initialize the game board and set the starting player
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int toMove() {
        // TODO: Implement method to return the current player's turn
        return 0; // Placeholder return
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Action> getActions() {
        // TODO: Implement method to return a list of legal actions (moves) for the
        // current state
        return null; // Placeholder return
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void result(Action action) {
        // TODO: Implement method to apply an action and update the game state
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTerminal() {
        // TODO: Implement method to check if the current state is a terminal state
        // (game over)
        return false; // Placeholder return
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int Utility() {
        // TODO: Implement method to calculate the utility of the current state
        return 0; // Placeholder return
    }

    private int getPosition(int x, int y) {
        return this.board[x][y];
    }

    private void setPosition(int x, int y, int value) {
        this.board[x][y] = value;
    }

    public String getBoard() {
        String boardRep = "";
        for (int i = 0; i < this.board.length; i++) {
            String row = "";
            for (int j = 0; j < this.board[i].length; j++) {
                row += this.board[i][j];
            }
            boardRep += row + "\n";
        }
        return boardRep;
    }
    // Additional helper methods specific to Tic Tac Toe, such as checking for a
    // win, can be added here
    // TODO: Implement helper methods for game-specific logic

}