package board.master.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;

import java.util.List;

import board.master.model.agents.AgentFactory;

@RestController
@RequestMapping("/api")
public class AgentController {
    
    @GetMapping("/agents")
    public ResponseEntity<List<String>> getAgents() {
        List<String> agents = AgentFactory.getAvailableAgentTypesList();
        return ResponseEntity.ok(agents);
    }
}
