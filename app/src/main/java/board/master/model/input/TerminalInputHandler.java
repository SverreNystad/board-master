package board.master.model.input;

import java.util.Scanner;

public class TerminalInputHandler implements InputHandler {
    private Scanner scanner;

    public TerminalInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getInput() {
        return scanner.nextLine();
    }
}
