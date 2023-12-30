package board.master.model.communication;

/**
 * Request for starting a new game in the backend.
 * The request is a immutable object.
 */
public class GameStartRequest {
    private final String playerColor; // "white" or "black"
    private final String botType;     // "minimax" or "random"
    /**
     * Chess or tic-tac-toe
     */
    private final String gameType;


    public GameStartRequest(String playerColor, String botType, String gameType) {
        this.playerColor = playerColor;
        this.botType = botType;
        this.gameType = gameType;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public String getBotType() {
        return botType;
    }

    public String getGameType() {
        return gameType;
    }
}
