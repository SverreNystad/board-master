package board.master.model.games.connect_four;

public class Result {
    protected String preSymbol;
    protected int piecesInARow;
    protected int score;

    public Result(String preSymbol, int piecesInARow, int score) {
        this.preSymbol = preSymbol;
        this.piecesInARow = piecesInARow;
        this.score = score;
    }
}
