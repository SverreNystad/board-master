package board.master.model.communication;

import board.master.model.games.Move;

/**
 * Request for making a move in a game.
 * The request is a immutable object.
 */
public class MoveRequest {
    private final String gameId;
    private final Move move;

    public MoveRequest(String gameId, int x, int y) {
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
