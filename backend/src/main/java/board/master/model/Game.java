package board.master.model;

import board.master.model.agents.Agent;
import board.master.model.Game;


public class Game {
    private String gameId;
    private Agent agent;
    private StateHandler stateHandler;

    public Game(String gameId, Agent agent, StateHandler stateHandler) {
        this.gameId = gameId;
        this.agent = agent;
        this.stateHandler = stateHandler;
    }


    public String getGameId() {
        return gameId;
    }

    public Agent getAgent() {
        return agent;
    }

    public StateHandler getStateHandler() {
        return stateHandler;
    }

    public Board getBoard() {
        return stateHandler.getBoard();
    }

    public void setStateHandler(StateHandler transformedGame) {
        this.stateHandler = transformedGame;
    }
}
