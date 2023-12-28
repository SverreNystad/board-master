package board.master.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @PostMapping("/move")
    public ResponseEntity<String> makeMove(@RequestBody String move) {
        return ResponseEntity.ok(move);
    }
}