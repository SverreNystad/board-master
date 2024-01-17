package board.master.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class HealthCheckController {

    /**
     * Endpoint for a heartbeat check.
     *
     * @return A simple message indicating the service is up and running.
     */
    @GetMapping("/heartbeat")
    public ResponseEntity<String> heartbeat() {
        String response = "BoardMaster's backend is alive!";
        return ResponseEntity.ok(response);
    }
}
