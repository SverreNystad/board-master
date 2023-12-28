package board.master.model;

public class GameResponse {
    private String gameId;
    private String status;
    private Board board;

    public GameResponse(String gameId, String status, Board board) {
        this.gameId = gameId;
        this.status = status;
        this.board = board;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
