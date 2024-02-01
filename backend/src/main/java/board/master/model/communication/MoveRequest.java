package board.master.model.communication;

import board.master.model.games.Move;

/**
 * Request for making a move in a game.
 * The request is a immutable object.
 */
public class MoveRequest {
    private final String gameId;
    private final Move move;

    // public MoveRequest(String gameId, Integer x, Integer y) {
    //     this.gameId = gameId;
    //     this.move = new Move(x, y);
    // }

    public MoveRequest(String gameId, String x, String y) {
        this.gameId = gameId;
        this.move = Move.createMove(x, y);
    }

    public String getGameId() {
        return gameId;
    }

    public Move getMove() {
        return move;
    }
}
