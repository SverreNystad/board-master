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
    private final int rowLength = 7;
    private final int columnHeight = 6;
    private final int base = 2; //the base for the heuristic function

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
        for (int x = 0; x < rowLength; x++) {
            if (board.getPosition(x, columnHeight-1) == "") {
                actions.add(new Move(x));
            }
        }
        return actions;
    }

    @Override
    public StateHandler result(Action action) {
        // Create a StateHandler with the same board and the opposite player to move
        ConnectFour result = new ConnectFour(-playerToMove, board);

        Move move = (Move) action;
        int x = Integer.valueOf(move.getX());
        for (int y = 0; y < columnHeight; y++) {
            if (result.getBoard().getPosition(x, y) == "") {
                String symbol = getPlayerSymbol(this.toMove());
                result.getBoard().setPosition(x, y, symbol);
                break;
            }
        }
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
        String playerSymbol = Integer.toString(player);
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
        String playerSymbol = Integer.toString(player);
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
     * Analyzes the board horizontally and returns a the score.
     * The score is calculated by the number of pieces in a row 
     * is positive if the player has the pieces in a row or 
     * negative if the opponent has the pieces in a row.
     * 
     * @return int with the score for the board
     */
    private int checkHorizontal(int player) {
        int score = 0;

        for (int row = 0; row < rowLength; row++) {
            String preSymbol = "";
            int piecesInARow = 0;
            for (int col = 0; col < columnHeight; col++) {
                processPosition(row, col, player, score, piecesInARow, preSymbol);
            }
        }
        return score;
    }

    private int checkVertical(int player) {
        int score = 0;

        for (int col = 0; col < columnHeight; col++) {
            String preSymbol = "";
            int piecesInARow = 0;
            for (int row = 0; row < rowLength; row++) {
                processPosition(row, col, player, score, piecesInARow, preSymbol);
            }
        }
        return score;
    }

    private int checkDiagonal(int player) {
        int score = 0;

        // Check diagonal (top-left to bottom-right)
        for (int row = 0; row < rowLength - 1; row++) {
            String preSymbol = "";
            int piecesInARow = 0;

            // If it is the first row, go through the column and check the diagonal
            // from every point in the column
            if (row == 0) {
                for (int col = 0; col < columnHeight; col++) {
                    int x = row;
                    int y = col;
                    while(x < rowLength && y < columnHeight) {
                        processPosition(x, y, player, score, piecesInARow, preSymbol);
                        x++;
                        y++;
                    }
                    preSymbol = "";
                }
            } else  {
                int x = row;
                int y = 0;
                while(x < rowLength && y < columnHeight) {
                        processPosition(row, y, player, score, piecesInARow, preSymbol);
                        x++;
                        y++;
                    }
                
            }
            
        }

        // Check diagonal (bottom-left to top-right)
        for (int row = rowLength - 1; row > -1; row--) {
            String preSymbol = "";
            int piecesInARow = 0;

            // If it is the last row, go through the column and check the diagonal
            // from every point in the column
            if (row == rowLength - 1) {
                for (int col = 0; col < columnHeight; col++) {
                    int x = row;
                    int y = col;
                    while(x > -1 && y < columnHeight) {
                        processPosition(x, y, player, score, piecesInARow, preSymbol);
                        x--;
                        y++;
                    }
                    preSymbol = "";
                }
            } else  {
                int x = row;
                int y = 0;
                while(x > -1 && y < columnHeight) {
                        processPosition(row, y, player, score, piecesInARow, preSymbol);
                        x--;
                        y++;
                    }
                
            }
        }
        return score;
    }

    private void processPosition(int row, int column, int player, int score,
    int piecesInARow, String preSymbol) {
        String cuSymbol = board.getPosition(row, column);
        if (preSymbol == cuSymbol && !preSymbol.equals("") ) {
            piecesInARow++;
        } else if (preSymbol != cuSymbol && !preSymbol.equals("")) {
            calculateScore(piecesInARow, preSymbol, score, player);
            piecesInARow = 0;
        }
        preSymbol = cuSymbol;
    }

    private void calculateScore(int piecesInARow, String preSymbol, 
    int score, int player) {
        if (piecesInARow > 0) {
            if (preSymbol.equals(getPlayerSymbol(player))) {
                score += Math.pow(base, piecesInARow);
            }
            else if (preSymbol.equals(getPlayerSymbol(-player))) {
                score -= Math.pow(base, piecesInARow);
            }
        }
    }

    @Override
    public Board getBoard() {
        return board;
    }
    
    private String getPlayerSymbol(int player) {
        return player == 1 ? "X" : "O";
    }

}
