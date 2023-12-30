package board.master.model;

public class GameResponse {
    private final String gameId;
    private final boolean status;
    private final Board board;

    public GameResponse(String gameId, boolean status, Board board) {
        this.gameId = gameId;
        this.status = status;
        this.board = board;
    }

    public String getGameId() {
        return gameId;
    }

    public boolean getStatus() {
        return status;
    }

    public Board getBoard() {
        return board;
    }

}
