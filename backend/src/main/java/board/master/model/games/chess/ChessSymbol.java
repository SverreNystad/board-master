package board.master.model.games.chess;

public enum ChessSymbol {
    PAWN_WHITE('\u2659'),
    PAWN_BLACK('\u265F'),
    ROOK_WHITE('\u2656'),
    ROOK_BLACK('\u265C'),
    KNIGHT_WHITE('\u2658'),
    KNIGHT_BLACK('\u265E'),
    BISHOP_WHITE('\u2657'),
    BISHOP_BLACK('\u265D'),
    QUEEN_WHITE('\u2655'),
    QUEEN_BLACK('\u265B'),
    KING_WHITE('\u2654'),
    KING_BLACK('\u265A');

    private char symbol;

    ChessSymbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

    public static void main(String[] args) {
        System.out.println("White Pawn symbol: " + ChessSymbol.PAWN_WHITE.toString());
        System.out.println("Black Pawn symbol: " + ChessSymbol.PAWN_BLACK.getSymbol());
        System.out.println("White Rook symbol: " + ChessSymbol.ROOK_WHITE.getSymbol());
        System.out.println("Black Rook symbol: " + ChessSymbol.ROOK_BLACK.getSymbol());
        System.out.println("White Knight symbol: " + ChessSymbol.KNIGHT_WHITE.getSymbol());
        System.out.println("Black Knight symbol: " + ChessSymbol.KNIGHT_BLACK.getSymbol());
        System.out.println("White Bishop symbol: " + ChessSymbol.BISHOP_WHITE.getSymbol());
        System.out.println("Black Bishop symbol: " + ChessSymbol.BISHOP_BLACK.getSymbol());
        System.out.println("White Queen symbol: " + ChessSymbol.QUEEN_WHITE.getSymbol());
        System.out.println("Black Queen symbol: " + ChessSymbol.QUEEN_BLACK.getSymbol());
        System.out.println("White King symbol: " + ChessSymbol.KING_WHITE.getSymbol());
        System.out.println("Black King symbol: " + ChessSymbol.KING_BLACK.getSymbol());
    }

}
