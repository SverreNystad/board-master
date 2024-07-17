package board.master.model.agents;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AgentFactoryTest {
    @Test
    @DisplayName("Test of createAgent with legal agent type")
    void testCreateAgent() {
        String agentType = AgentFactory.getAvailableAgentTypesList().get(0);
        Agent agent = AgentFactory.createAgent(agentType);
        assert (agent != null);
        assert (agent instanceof Agent);
    }

    @Test
    @DisplayName("Test of createAgent with illegal agent type")
    void testCreateAgentIllegal() {
        String agentType = "illegal";
        assertThrows(IllegalArgumentException.class, () -> {
            AgentFactory.createAgent(agentType);
        });
    }

    @Test
    @DisplayName("Test of isValidAgentType with all legal agent types")
    void testIsValidAgentType() {
        for (String agentType : AgentFactory.getAvailableAgentTypesList()) {
            assert (AgentFactory.isValidAgentType(agentType));
        }
    }
}
