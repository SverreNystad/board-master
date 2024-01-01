package board.master.model.communication;

/**
 * Request for starting a new game in the backend.
 * The request is a immutable object.
 */
public class GameStartRequest {
    private final String botType;
    private final String gameType;

    public GameStartRequest(String botType, String gameType) {
        this.botType = botType;
        this.gameType = gameType;
    }

    public String getBotType() {
        return botType;
    }

    public String getGameType() {
        return gameType;
    }
}
