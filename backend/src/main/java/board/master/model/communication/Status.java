package board.master.model.communication;

public enum Status {
    PLAYER_WIN("You won"),
    BOT_WIN("Bot won"),
    DRAW("Draw"),
    IN_PROGRESS("Game in progress");

    private final String message;

    private Status(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
