package board.master.model.agents;

import board.master.model.Action;
import board.master.model.StateHandler;

import java.util.List;

/**
 * RandomStrategy
 */
public class RandomStrategy implements Agent {

  /**
   * Chooses a random action from the list of available actions.
   * 
   * @param state The current state of the game.
   * @return A random action from the list of available actions.
   */
  @Override
  public Action getAction(StateHandler state) {
    List<Action> actions = state.getActions();

    int random = (int) (Math.random() * actions.size());
    return actions.get(random);
  }

}