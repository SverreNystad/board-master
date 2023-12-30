package board.master.model;

public class GameStartRequest {
    private final String playerColor; // "white" or "black"
    private final String botType;     // "minimax" or "random"
    /**
     * Chess or tic-tac-toe
     */
    private String gameType;


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
