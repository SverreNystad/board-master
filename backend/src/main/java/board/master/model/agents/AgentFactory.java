package board.master.model.agents;

import java.util.HashMap;
import java.util.List;

/**
 * Factory class for creating agents instances.
 */
public class AgentFactory {

    public static Agent createAgent(String agentStrategy) throws IllegalArgumentException {
        for (String agent : getAgentTypesList()) {
            if (agentStrategy.toUpperCase().equals(agent)) {
                return getAgentTypes().get(agent);
            }
        }
        throw new IllegalArgumentException("Invalid agent type");
    }

    /**
     * Get the list of valid agent types.
     * 
     * @return list of valid agent types
     */
    private static HashMap<String, Agent> getAgentTypes() {
        HashMap<String, Agent> agents = new HashMap<>();
        agents.put("MINIMAX", new Minimax());
        agents.put("RANDOM", new RandomStrategy());
        return agents;
    }

    public static List<String> getAgentTypesList() {
        return List.copyOf(getAgentTypes().keySet());
    }

    public static boolean isValidAgentType(String agentType) {
        return getAgentTypesList().contains(agentType.toUpperCase());
    }

}
