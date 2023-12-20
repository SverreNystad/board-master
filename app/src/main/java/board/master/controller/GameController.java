package board.master.controller;

import board.master.model.Action;
import board.master.model.GameFactory;
import board.master.model.StateHandler;
import board.master.model.agents.Agent;
import board.master.model.agents.RandomStrategy;

public class GameController {

  private StateHandler game;
  private Agent agent;

  public GameController(String gameName, String agentStrategy) {
    this.game = GameFactory.getGame(gameName);
    this.agent = new RandomStrategy();
  }

  public void startGame() {
    Action action = null;
    while (true) {
      action = agent.getAction(game);
      game.result(action);
      System.out.println(game);
      if (game.isTerminal()) {
        break;
      }
    }
  }

}
