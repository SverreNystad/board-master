package board.master.model.agents;

import java.util.List;
import board.master.model.Action;
import board.master.model.StateHandler;
import board.master.model.game.nim.NimAction;
import board.master.model.game.tic_tac_toe.TicTacToeAction;
import board.master.model.input.InputHandler;

public class PlayerStrategy implements Agent {

    private InputHandler inputHandler;

    public PlayerStrategy(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action getAction(StateHandler state) {
        String userInput = inputHandler.getInput();
        Action action = this.createAction(userInput, state.getClass().getSimpleName());
        return action;

    }

    /**
     * Creates an action from the user input.
     * 
     * @param userInput The user input.
     * @return The action.
     */
    private Action createAction(String userInput, String gameName) {
        Action action = null;
        switch (gameName) {
            case "Nim":
                // Parse the user input. That will be a string with number.
                // remove all non-numeric characters
                userInput = userInput.replaceAll("[^\\d]", "");
                int number = Integer.parseInt(userInput);
                action = new NimAction(number);
                break;

            case "TicTacToe":
                // Parse the user input. That will be a string of two numbers separated by a comma.
                // Remove all non-numeric characters and split the string by the comma.
                userInput = userInput.replaceAll("[^\\d,]", "");
                List<String> numbers = List.of(userInput.split(","));
                int x = Integer.parseInt(numbers.get(0));
                int y = Integer.parseInt(numbers.get(1));
                action = new TicTacToeAction(x, y);
                break;

            default:
                break;
        }
        return action;
    }

}
