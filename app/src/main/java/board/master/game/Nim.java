package board.master.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Nim class implementing the StateHandler interface.
 * This class manages the state of a Nim game, including the game board, player
 * turns, and checking for game over conditions.
 * 
 * For reading on the game of Nim, see: https://en.wikipedia.org/wiki/Nim
 */
public class Nim implements StateHandler {
    private int amount;
    private int maxTake;
    private int currentPlayer;

    public Nim(int startAmount, int maxTake) {
        this.amount = startAmount;
        this.maxTake = maxTake;
        this.currentPlayer = 1;

        validConstruction(startAmount, maxTake);
    }

    private void validConstruction(int startAmount, int maxTake) throws IllegalArgumentException {
        if (startAmount < 1) {
            throw new IllegalArgumentException("Must start with at least one object.");
        }

        if (maxTake < 1) {
            throw new IllegalArgumentException("Must be able to take at least one object.");
        }
    }

    @Override
    public int toMove() {
        return currentPlayer;
    }

    @Override
    public List<Action> getActions() {
        List<Action> actions = new ArrayList<Action>();
        for (int i = 1; i <= maxTake; i++) {
            if (validMove(amount, maxTake, i)) {
                actions.add(new NimAction(i));
            }
        }
        return actions;
    }

    private static boolean validMove(int amount, int maxTake, int take) {
        return take <= maxTake && take > 0 && take <= amount;
    }

    @Override
    public void result(Action action) {
        NimAction nimAction = (NimAction) action;
        amount -= nimAction.getTake();
        // Switch player
        currentPlayer *= -1;
    }

    @Override
    public boolean isTerminal() {
        return amount < 1;
    }

    @Override
    public int Utility() {
        // If the game is over, the player who must take the last object loses
        if (isTerminal()) {
            return currentPlayer == 1 ? -1 : 1;
        }
        // If the game is not over, the utility is 0
        return 0;
    }

    public int getAmount() {
        return amount;
    }

    public int getMaxTake() {
        return maxTake;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public static void main(String[] args) {
        Nim nim = new Nim(10, 3);
        System.out.println(nim);
        System.out.println(nim.getActions());
        Action action = null;
        while (!nim.isTerminal()) {
            if (nim.toMove() == 1) {
                // Get player input
                Scanner scanner = new Scanner(System.in);
                System.out.println("How many objects do you want to take?");
                int take = scanner.nextInt();
                action = nim.getActions().get(take - 1);

            } else {
                action = nim.getActions().get(0);
            }

            nim.result(action);
            System.out.println("Player " + nim.toMove() + " took " + ((NimAction) action).getTake() + " objects.");
            System.out.println(nim.amount + " objects remaining.");

        }
        System.out.println("Player " + nim.toMove() + " loses.");
    }
}
