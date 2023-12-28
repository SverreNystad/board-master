package board.master.model;

public class GameStartRequest {
    private String playerColor; // "white" or "black"
    private String botType;     // "minimax" or "random"
    /**
     * Chess or tic-tac-toe
     */
    private String gameType;


    public GameStartRequest(String playerColor, String botType, String gameType) {
        this.playerColor = playerColor;
        this.botType = botType;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(String playerColor) {
        this.playerColor = playerColor;
    }

    public String getBotType() {
        return botType;
    }

    public void setBotType(String botType) {
        this.botType = botType;
    }

    public String getGameType() {
        return gameType;
    }
}
