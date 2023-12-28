package board.master.model.agents;

/**
 * Factory class for creating agents instances.
 */
public class AgentFactory {

    public static Agent createAgent(String agentStrategy) throws IllegalArgumentException {

        switch (agentStrategy.toUpperCase()) {
            case "MINI-MAX":
                return new Minimax();
            case "RANDOM":
                return new RandomStrategy();
            default:
                throw new IllegalArgumentException("Invalid agent type: " + agentStrategy);
        }
    }

}
