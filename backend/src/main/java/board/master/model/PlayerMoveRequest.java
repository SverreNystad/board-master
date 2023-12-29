package board.master.model;


public class PlayerMoveRequest {
    private String gameId;
    private Move move;

    public PlayerMoveRequest(String gameId, int x, int y) {
        this.gameId = gameId;
        this.move = new Move(x, y);
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
