package board.master.model.communication;

import board.master.model.games.Board;

public class GameResponse {
    private final String gameId;
    private final String status;
    private final Board board;

    public GameResponse(String gameId, String status, Board board) {
        this.gameId = gameId;
        this.status = status;
        this.board = board;
    }

    public String getGameId() {
        return gameId;
    }

    public String getStatus() {
        return status;
    }

    public Board getBoard() {
        return board;
    }

}
