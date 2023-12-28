package board.master.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import board.master.model.GameStartRequest;
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

    @PostMapping("/start")
    public ResponseEntity<GameResponse> startGame(@RequestParam GameStartRequest request) {
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
    public ResponseEntity<GameResponse> playerMove(@RequestParam PlayerMoveRequest request) {
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
    public ResponseEntity<GameResponse> botMove(@RequestParam String gameId) {
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
}
