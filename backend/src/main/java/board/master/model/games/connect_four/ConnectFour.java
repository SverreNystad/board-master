package board.master.model.games.connect_four;

import java.util.ArrayList;
import java.util.List;

import board.master.model.Action;
import board.master.model.StateHandler;
import board.master.model.games.Board;
import board.master.model.games.Move;

public class ConnectFour implements StateHandler {

    private int playerToMove;
    private Board board;
    private final int rowLength = 6;
    private final int columnHeight = 7;
    private final int base = 2; //the base for the heuristic function
    private String startOfRow = "start";
    private String preSymbol = startOfRow;
    
    private int piecesInARow = 0;
    private boolean emptySpace = false; // If there is an empty space before the pieces in a row


    public ConnectFour() {
        playerToMove = 1;
        board = new Board(rowLength, columnHeight);
    }

    private ConnectFour(int playerToMove, Board board) {
        this.playerToMove = playerToMove;
        this.board = new Board(board.getRows(), board.getColumns());
        for (int x = 0; x < board.getRows(); x++) {
            for (int y = 0; y < board.getColumns(); y++) {
                this.board.setPosition(x, y, board.getPosition(x, y));
            }
        }
    }

    @Override
    public int toMove() {
        return playerToMove;
    }

    @Override
    public List<Action> getActions() {
        List<Action> actions = new ArrayList<Action>();
        for (int col = 0; col < columnHeight; col++) { // Goes through the columns on the board
            if (board.getPosition(0, col) == "") { // If the column is not full (the top row is empty)
                actions.add(new Move(col)); // Add the column to the list of possible moves
            }
        }
        return actions;
    }

    @Override
    public StateHandler result(Action action) {
        // Create a StateHandler with the same board and the opposite player to move
        ConnectFour result = new ConnectFour(-playerToMove, board);

        Move move = (Move) action;
        int col = Integer.valueOf(move.getX());
        int row = rowLength - 1;
        
        while (row > -1 && result.getBoard().getPosition(row, col) != "") {
            row--;
        }
        String symbol = getPlayerSymbol(this.toMove());
        result.getBoard().setPosition(row, col, symbol);

        return result;
    }

    @Override
    public boolean isTerminal() {
        // Check for a win
        if (checkForWin(1) || checkForWin(-1)) {
            return true;
        }
        // Check for a draw
        return getActions().isEmpty();
    }

    private boolean checkForWin(int player) {
        // Check horizontal, vertical, and diagonal lines
        return checkHorizontalWin(player) || checkVerticalWin(player) || checkDiagonalWin(player);
    }

    private boolean checkHorizontalWin(int player) {
        String playerSymbol = getPlayerSymbol(player);
        for (int row = 0; row < rowLength; row++) {
            for (int col = 0; col < columnHeight - 3; col++) {
                if (board.getPosition(row, col).equals(playerSymbol)
                        && board.getPosition(row, col + 1).equals(playerSymbol)
                        && board.getPosition(row, col + 2).equals(playerSymbol)
                        && board.getPosition(row, col + 3).equals(playerSymbol)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVerticalWin(int player) {
        String playerSymbol = getPlayerSymbol(player);
        for (int col = 0; col < columnHeight; col++) {
            for (int row = 0; row < rowLength - 3; row++) {
                if (board.getPosition(row, col).equals(playerSymbol)
                        && board.getPosition(row + 1, col).equals(playerSymbol)
                        && board.getPosition(row + 2, col).equals(playerSymbol)
                        && board.getPosition(row + 3, col).equals(playerSymbol)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalWin(int player) {
        String playerSymbol = getPlayerSymbol(player);
        // Check diagonal (top-left to bottom-right)
        for (int row = 0; row < rowLength - 3; row++) {
            for (int col = 0; col < columnHeight - 3; col++) {
                if (board.getPosition(row, col).equals(playerSymbol)
                        && board.getPosition(row + 1, col + 1).equals(playerSymbol)
                        && board.getPosition(row + 2, col + 2).equals(playerSymbol)
                        && board.getPosition(row + 3, col + 3).equals(playerSymbol)) {
                    return true;
                }
            }
        }

        // Check diagonal (bottom-left to top-right)
        for (int row = 3; row < rowLength; row++) {
            for (int col = 0; col < columnHeight - 3; col++) {
                if (board.getPosition(row, col).equals(playerSymbol)
                        && board.getPosition(row - 1, col + 1).equals(playerSymbol)
                        && board.getPosition(row - 2, col + 2).equals(playerSymbol)
                        && board.getPosition(row - 3, col + 3).equals(playerSymbol)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public int utility(int player) {
        if (checkForWin(player)) {
            return Integer.MAX_VALUE;
        } else if (checkForWin(-player)) {
            return Integer.MIN_VALUE;
        }
        // The game is not over yet use a heuristic
        return heuristic(player);
    }

    /**
     * Heuristic function for Connect Four
     * 
     * @param player
     * @return The expected utility of the current state
     */
    private int heuristic(int player) {
        int score = 0;
        // Check horizontal
        score += checkHorizontal(player);
        // Check vertical
        score += checkVertical(player);
        // Check diagonal
        score += checkDiagonal(player);

        return score;
    }

    /**
     * Analyzes the board horizontally and returns a score.
     * The score is calculated by the number of pieces in a row 
     * is positive if the player has the pieces in a row or 
     * negative if the opponent has the pieces in a row.
     * 
     * @return int with the score for the board
     */
    private int checkVertical(int player) {
        int score = 0;

        for (int col = 0; col < columnHeight; col++) {
            for (int row = 0; row < rowLength; row++) {
                score += processPosition(row, col, player);
            }
            score += resetValues(true, player);

        }
        return score;
    }

    /**
     * Analyzes the board horizontally and returns a score.
     * The score is calculated by the number of pieces in a row 
     * is positive if the player has the pieces in a row or 
     * negative if the opponent has the pieces in a row.
     * 
     * @return int with the score for the board
     */
    private int checkHorizontal(int player) {
        int score = 0;

        for (int row = 0; row < rowLength; row++) {
            for (int col = 0; col < columnHeight; col++) {
                score += processPosition(row, col, player);
            }
            score += resetValues(true, player);

        }
        return score;
    }

    /**
     * Analyzes the board diagonally and returns a the score.
     * The score is calculated by the number of pieces in a row 
     * is positive if the player has the pieces in a row or 
     * negative if the opponent has the pieces in a row.
     * 
     * @return int with the score for the board
     */
    private int checkDiagonal(int player) {
        int score = 0;

        // Check diagonal (top-left to bottom-right)
        for (int row = 0; row < rowLength - 3; row++) {
            // No use to give score for last 3 rows as you can't get 4 in a row there

            // If it is the first row, go through the column and check the diagonal
            // from every point in the column
            if (row == 0) {
                for (int col = 0; col < columnHeight - 3; col++) {
                    // No use to give score for last 3 columns as you can't get 4 in a row there
                    int x = row;
                    int y = col;
                    while(x < rowLength && y < columnHeight) {
                        score += processPosition(x, y, player);
                        x++;
                        y++;
                    }
                    score += resetValues(true, player);

                }
            } else  {
                int x = row;
                int y = 0;
                while(x < rowLength && y < columnHeight) {
                    score += processPosition(x, y, player);
                    x++;
                    y++;
                }
                score += resetValues(true, player);
                
            }
            
        }

        // Check diagonal (bottom-left to top-right)
        for (int row = rowLength - 1; row > 2; row--) {
            // If it is the last row, go through the column and check the diagonal
            // from every point in the column
            if (row == rowLength - 1) {
                for (int col = 0; col < columnHeight - 3; col++) {
                    int x = row;
                    int y = col;
                    while(x > -1 && y < columnHeight) {
                        score += processPosition(x, y, player);
                        x--;
                        y++;
                    }
                    score += resetValues(true, player);

                }
            } else  {
                int x = row;
                int y = 0;
                while (x > -1 && y < columnHeight) {
                    score += processPosition(x, y, player);
                    x--;
                    y++;
                }
                score += resetValues(true, player);
                
            }
        }
        return score;
    }

    private int processPosition(int row, int column, int player) {
        String cuSymbol = board.getPosition(row, column);
        int score = 0;
        if (this.preSymbol == cuSymbol && !this.preSymbol.isEmpty() ) {
            this.piecesInARow++;
            
        } else if (this.preSymbol != cuSymbol && !this.preSymbol.equals(startOfRow)) {
            boolean emptyCurrent = cuSymbol.isEmpty();
            if (this.preSymbol.isEmpty()) { 
                // If the symbol before current symbol is empty, emptySpace is true
                this.emptySpace = true;

            } else if (emptySpace || emptyCurrent) { 
                // If an empty space is before or after an piece in a row, the score is calculated
                int empty = (emptySpace && emptyCurrent) ? 2 : 1;
                this.emptySpace = true;
                score += resetValues(false, player) * empty;

            } else {
                this.piecesInARow = 0;
            }
            
        }
        this.preSymbol = cuSymbol;

        return score; 
    }

    private int calculateScore(int player) {
        int score = 0;
        if (this.piecesInARow > 0) {
            if (this.preSymbol.equals(getPlayerSymbol(player))) {
                score += Math.pow(base, this.piecesInARow);
            }
            else if (this.preSymbol.equals(getPlayerSymbol(-player))) {
                score -= Math.pow(base, this.piecesInARow);
            }
        }
        return score;
    }

    private int resetValues(boolean resetPreSymbol, int player) {
        int score = 0;
        if (this.emptySpace) {
            score = calculateScore(player);
        }
        this.piecesInARow = 0;
        this.emptySpace = false;
        
        if (resetPreSymbol) {
            this.preSymbol = startOfRow;
        }
        
        return score;
    }

    @Override
    public Board getBoard() {
        return board;
    }
    
    private String getPlayerSymbol(int player) {
        return player == 1 ? "X" : "O";
    }

}
