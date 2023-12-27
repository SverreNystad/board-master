package board.master.model.input;

public class GUIInputHandler implements InputHandler {
    private String lastAction;

    public synchronized void waitForInput() {
        try {
            wait();  // Waits until a move is made in the GUI
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void setInput(String action) {
        this.lastAction = action;
        notifyAll();  // Notifies the waiting thread that a move has been made
    }

    @Override
    public String getInput() {
        waitForInput();
        return lastAction.toString();
    }
}
