package board.master.model.agents;

import java.util.HashMap;
import java.util.List;

/**
 * Factory class for creating instances of different types of game-playing agents.
 */
public class AgentFactory {

    private final static HashMap<String, Agent> agents = initializeAvailableAgents();


    /**
     * Creates an instance of a game-playing agent based on the specified strategy.
     * 
     * Supported agent types are listed in the {@link #getAvailableAgentTypesList()} method.
     * 
     * @param strategyType - the strategy type for which to create an agent
     * @return Agent instance implementing the specified strategy
     * @throws IllegalArgumentException if the specified strategy type is unsupported
     */
    public static Agent createAgent(String agentStrategy) throws IllegalArgumentException {
        Agent agent = agents.get(agentStrategy.toUpperCase());
        if (agent == null) {
            throw new IllegalArgumentException("Invalid agent type");
        }
        return agent;
    }

    /**
     * Checks if a given strategy type is supported for agent creation.
     * 
     * @param strategyType - the strategy type to check
     * @return true if the strategy type is supported, false otherwise
     */
    public static boolean isValidAgentType(String strategyType) {
        return getAvailableAgentTypesList().contains(strategyType.toUpperCase());
    }

    /**
     * Retrieves a list of supported strategy types for game-playing agents.
     * 
     * @return List of supported strategy types
     */
    public static List<String> getAvailableAgentTypesList() {
        return List.copyOf(initializeAvailableAgents().keySet());
    }

    /**
     * Initializes and returns a map of strategy types to their corresponding Agent instances.
     * 
     * @return HashMap mapping strategy types to Agent instances
     */
    private static HashMap<String, Agent> initializeAvailableAgents() {
        HashMap<String, Agent> agents = new HashMap<>();
        agents.put("MINIMAX", new Minimax());
        agents.put("RANDOM", new RandomStrategy());
        agents.put("ALPHABETA-MINIMAX", new AlphaBetaPruningMinimax());
        agents.put("ITERATIVE-DEEPENING-ALPHABETA-MINIMAX", new IterativeDeepeningAlphaBetaPruningMinimax());
        agents.put("MONTE-CARLO-TREE-SEARCH", new MonteCarloTreeSearch());
        return agents;
    }
}
