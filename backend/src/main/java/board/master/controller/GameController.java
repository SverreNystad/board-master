package board.master.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import board.master.model.GameStartRequest;
import board.master.model.Board;
import board.master.model.GameResponse;
import board.master.model.PlayerMoveRequest;

import board.master.service.GameService;

@RestController
@RequestMapping("/api")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Endpoint to start a new game
     */
    @PostMapping("/start")
    public ResponseEntity<GameResponse> startGame(@RequestBody GameStartRequest request) {
        // Logic to start a new game
        try {
            GameResponse response = gameService.startGame(request);
            return ResponseEntity.ok(response);
        } 
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } 
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/move")
    public ResponseEntity<GameResponse> playerMove(@RequestBody PlayerMoveRequest request) {
        // Logic to handle player's move
        try {
            GameResponse response = gameService.playerMove(request);
            return ResponseEntity.ok(response);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();

        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/bot-move")
    public ResponseEntity<GameResponse> botMove(@RequestBody String gameId) {
        // Logic to generate bot's move
        try {
            GameResponse response = gameService.botMove(gameId);
            return ResponseEntity.ok(response);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/test")
    public GameResponse test() {
        // Logic to generate bot's move
        return new GameResponse("is", "status", new Board(0, 0));
    }
}
