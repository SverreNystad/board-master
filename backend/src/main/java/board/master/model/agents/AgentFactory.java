package board.master.model.agents;

import java.util.List;

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

    /**
     * Get the list of valid agent types.
     * 
     * @return list of valid agent types
     */
    public static List<String> getAgentTypes() {
        // TODO Create a way to get the list of valid agent types that will be updated when new agents are added
        // Maybe use a hashmap to store the agent types and their descriptions
        // And return the keys of the hashmap, make then this hashmap used in createAgent method to make them stay in sync
        return null;
    }

}
