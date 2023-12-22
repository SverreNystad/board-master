package board.master.view;

import board.master.model.StateHandler;
import board.master.model.game.nim.Nim;
public class CLINimView implements View {

    public void updateBoard(StateHandler state) {
        Nim nim = (Nim) state;
        System.out.println("Amount left: " + nim.getAmount());
        System.out.println("Current player: " + nim.getCurrentPlayer());
        if (nim.isTerminal()) {
            System.out.println("Game over!");
            System.out.println("Winner: " + nim.Utility());
        }
    }


}
