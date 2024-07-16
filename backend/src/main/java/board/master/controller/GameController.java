package board.master.controller;

import java.util.logging.Logger;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import board.master.model.communication.GameId;
import board.master.model.communication.GameResponse;
import board.master.model.communication.GameStartRequest;
import board.master.model.communication.MoveRequest;
import board.master.service.GameService;

@RestController
@RequestMapping("/api")
public class GameController {
    private final Logger logger = Logger.getLogger(getClass().getName());
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Endpoint to start a new game.
     * 
     * @param request - GameStartRequest for what type of game to start, who is the first player and what bot to fight against
     * @return GameResponse with the game id, status and board
     */
    @PostMapping("/start")
    public ResponseEntity<GameResponse> startGame(@RequestBody GameStartRequest request) {
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

    /**
     * Endpoint to make a move in the game specified with the same gameId.
     * 
     * @param request - PlayerMoveRequest for what game to make a move in and what move to make
     * @return GameResponse with the game id, status and board
     */
    @PostMapping("/move")
    public ResponseEntity<GameResponse> playerMove(@RequestBody MoveRequest request) {
        try {
            GameResponse response = gameService.playerMove(request);
            return ResponseEntity.ok(response);
        }
        catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().build();

        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Endpoint to getting bots move in the game.
     * 
     * @param gameId - GameId with the id of the game to make a move in
     * @return GameResponse with the game id, status and board
     */
    @PostMapping("/bot-move")
    public ResponseEntity<GameResponse> botMove(@RequestBody GameId gameId) {
        try {
            String id = gameId.getGameId();
            GameResponse response = gameService.botMove(id);
            return ResponseEntity.ok(response);
        }
        catch (IllegalArgumentException | IllegalStateException e) {
            logger.warning("Bot move request: id:" + gameId + " error:" + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
        catch (Exception e) {
            logger.severe("Bot move request: id:" + gameId + " error:" + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
