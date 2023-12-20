/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package board.master;

import board.master.controller.GameController;

public class App {

    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        GameController gameController = new GameController("Nim", "RandomStrategy");
        gameController.startGame();
    }
}
