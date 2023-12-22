package board.master.view;

import board.master.model.StateHandler;
import board.master.model.game.nim.Nim;

public class TicTacToeView implements View {
  
        @Override
        public void updateBoard(StateHandler model) {
            Nim nim = (Nim) model;
            System.out.println("Amount left: " + nim.getAmount());
            System.out.println("Current player: " + nim.getCurrentPlayer());
        }
}
