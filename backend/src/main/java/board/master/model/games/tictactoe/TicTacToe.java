package board.master.model.games.tictactoe;

import java.util.List;
import java.util.ArrayList;

import board.master.model.Board;
import board.master.model.Move;
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
    private Board board;
    
    /**
     * Constructs a new TicTacToe game with an empty board and sets the starting
     * player.
     */
    public TicTacToe() {
        this.board = new Board(3, 3);
        // TODO: Initialize the game board and set the starting player
    }

    public TicTacToe(TicTacToe ticTacToe) {
        this.board = new Board(ticTacToe.getBoard().getRows(), 
        ticTacToe.getBoard().getColumns());
        this.board.setGrid(ticTacToe.getBoard().getGrid());
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
        List<Action> actions = new ArrayList<Action>();
        for (int x = 0; x < this.board.getRows(); x++) {
            for (int y = 0; y < this.board.getColumns(); y++) {
                if (this.board.getPosition(x, y).equals("")) {
                    actions.add((Action) new Move(Integer.toString(x), Integer.toString(y)));
                }
            }
        }
        return actions; // Placeholder return
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StateHandler result(Action action) {
        Move move = (Move) action;
        String value = (this.toMove() == 1) ? "X" : "O";
        TicTacToe newState = new TicTacToe(this);
        newState.setPosition(Integer.parseInt(move.getX()), Integer.parseInt(move.getY()), value);
        
        return newState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTerminal() {
        //if board is full 
        if (getActions().size() == 0) {
            return true;
        }

        //check for win in rows and columns
        if(checkWin() != null) {
            return true;
        }
  
        return false; // Placeholder return
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int Utility() {
        switch (checkWin().toUpperCase()) {
            case "X":
                return 1;
            case "O":
                return -1;
            default:
                return 0;
        }
    }

    public String getPosition(int x, int y) {
        return board.getPosition(x, y);
    }

    public void setPosition(int x, int y, String value) {
        board.setPosition(x, y, value);
        // this.board[x][y] = value;
    }

    public Board getBoard() {
        return this.board;
    }

    /**
     * Checks for a win in the current state of the game. 
     * Returns the symbol of the winning player if there is a win, 
     * or null if there is no win yet or the game is a draw.
     * @return
     */
    private String checkWin() {
        String checkWin = null;
        //check for win in rows and columns
        for (int i = 0; i < 3; i++) {
            Boolean isNotEmpty = !board.getPosition(i, i).equals("");
            if (board.getPosition(i, 0).equals(board.getPosition(i, 1)) 
            && board.getPosition(i, 1).equals(board.getPosition(i, 2)) && isNotEmpty) {
                return board.getPosition(i, 0);
            }

            if (board.getPosition(0, i).equals(board.getPosition(1, i)) 
            && board.getPosition(1, i).equals(board.getPosition(2, i)) 
            && isNotEmpty) {
                return board.getPosition(i, 0);
            }
        }

        Boolean isNotEmpty = !board.getPosition(1, 1).equals("");

        //check for win in diagonals
        if (board.getPosition(0, 0).equals(board.getPosition(1, 1)) 
        && board.getPosition(1, 1).equals(board.getPosition(2, 2)) 
        && isNotEmpty) {
            return board.getPosition(1, 1);
        }

        if(board.getPosition(0, 2).equals(board.getPosition(1, 1))
        && board.getPosition(1, 1).equals(board.getPosition(2, 0))
        && isNotEmpty) {
            return board.getPosition(1, 1);
        }
        return checkWin;
    }



    // Additional helper methods specific to Tic Tac Toe, such as checking for a
    // win, can be added here
    // TODO: Implement helper methods for game-specific logic

}
