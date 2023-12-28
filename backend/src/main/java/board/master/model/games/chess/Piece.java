public abstract class Piece {
    
    public enum Color {
        WHITE, BLACK
    }
    public Color color;
    public int row;
    public int column;

    public Piece(Color color, int row, int column) {
        this.color = color;
        this.row = row;
        this.column = column;
    }

    public abstract boolean isValidMove(int row, int column);

    public void move(int row, int column) throws IllegalArgumentException {
        if (!isValidMove(row, column)) {
            throw new IllegalArgumentException("Invalid move");
        }
        this.row = row;
        this.column = column;
    }

    public abstract String getSymbol();
}
