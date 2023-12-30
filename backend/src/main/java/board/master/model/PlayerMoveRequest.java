package board.master.model;

public class PlayerMoveRequest {
    private final String gameId;
    private final Move move;

    public PlayerMoveRequest(String gameId, int x, int y) {
        this.gameId = gameId;
        this.move = new Move(x, y);
    }

    public String getGameId() {
        return gameId;
    }

    public Move getMove() {
        return move;
    }
}
