package board.master.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameId {
    private String gameId;

    public GameId() { }

    @JsonProperty("gameId")
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameId() {
        return gameId;
    }
}
