package board.master.model;


public class PlayerMoveRequest {
    private String gameId;
    private Move move;

    public PlayerMoveRequest(String gameId, Move move) {
        this.gameId = gameId;
        this.move = move;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }
}
