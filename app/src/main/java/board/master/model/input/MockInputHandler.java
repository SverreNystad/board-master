package board.master.model.input;

public class MockInputHandler implements InputHandler {
    
    private String game;
    private String output;
    public MockInputHandler(String game) {
        this.game = game;
    }

    @Override
    public String getInput() {
        if (this.output != null) {
            return output;
        }

        if (game.equals("TicTacToe")) {
            return "1,1";
        }
        else if (game.equals("Nim")) {
            return "1";
        }
        else {
            return "1";
        }
    }

    public void setOutput(String output) {
        this.output = output;
    }
    

}
