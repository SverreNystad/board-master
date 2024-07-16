package board.master.model.communication;

import board.master.model.games.Board;

public class GameResponse {
    private final String gameId;
    private final Status status;
    private final Board board;

    public GameResponse(String gameId, Status status, Board board) {
        this.gameId = gameId;
        this.status = status;
        this.board = board;
    }

    public String getGameId() {
        return gameId;
    }

    public String getStatus() {
        return status.getMessage();
    }

    public Board getBoard() {
        return board;
    }

}
