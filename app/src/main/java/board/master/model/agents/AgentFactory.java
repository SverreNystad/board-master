package board.master.model.agents;

import board.master.model.input.InputHandler;

/**
 * Factory class for creating agents instances.
 */
public class AgentFactory {

    public static Agent getAgent(String agentStrategy, InputHandler inputHandler) throws IllegalArgumentException {

        switch (agentStrategy.toUpperCase()) {
            case "MINI-MAX":
                return new Minimax();
            case "RANDOM":
                return new RandomStrategy();
            case "PLAYER":
                return new PlayerStrategy(inputHandler);
            default:
                throw new IllegalArgumentException("Invalid agent type: " + agentStrategy);
        }
    }

}
