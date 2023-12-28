package board.master.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import board.master.model.GameStartRequest;
import board.master.model.Board;
import board.master.model.GameResponse;
import board.master.model.PlayerMoveRequest;

import board.master.service.GameService;


@RestController
@RequestMapping("/api/chess")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public GameResponse startGame(@RequestParam GameStartRequest request) {
        // Logic to start a new game
        return gameService.startGame(request);
    }

    @PostMapping("/move")
    public GameResponse playerMove(@RequestParam PlayerMoveRequest request) {
        // Logic to handle player's move
        return gameService.playerMove(request);
    }

    @GetMapping("/bot-move")
    public GameResponse botMove(@RequestParam String gameId) {
        // Logic to generate bot's move
        return gameService.botMove(gameId);
    }

    @GetMapping("/test")
    public GameResponse test() {
        // Logic to generate bot's move
        return new GameResponse("is", "status", new Board(0, 0));
    }
}
