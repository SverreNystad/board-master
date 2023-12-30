package board.master.model;

import java.util.List;
import java.util.ArrayList;

public class Board {
    private List<List<String>> grid;
    private int rows;
    private int columns;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = initializeBoard(rows, columns);
    }

    /** 
     * Initialize the board with empty strings or a default value
     * @param rows The number of rows in the board
     * @param columns The number of columns in the board
     */
    private static List<List<String>> initializeBoard(int rows, int columns) {
        List<List<String>> cells = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            List<String> row = new ArrayList<>();
            for (int c = 0; c < columns; c++) {
                row.add("");
            }
            cells.add(row);
        }
        return cells;
    }

    public List<List<String>> getGrid() {
        return this.grid;
    }

    public void setGrid(List<List<String>> grid) {
        this.grid = grid;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public String getPosition(int x, int y) {
        return this.grid.get(x).get(y);
    }

    public void setPosition(int x, int y, String value) {
        List<String> row = this.grid.get(x);
        row.set(y, value);
        this.grid.set(x, row);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Board)) {
            return false;
        }
        Board otherBoard = (Board) other;
        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.columns; c++) {
                if (!this.getPosition(r, c).equals(otherBoard.getPosition(r, c))) {
                    return false;
                }
            }
        }
        return true;
    }
}
