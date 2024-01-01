package board.master.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import board.master.model.Action;
import board.master.model.communication.GameResponse;
import board.master.model.communication.GameStartRequest;
import board.master.model.communication.MoveRequest;
import board.master.model.games.Board;
import board.master.model.games.Move;

public class ServiceIntegrationTest {
    
    private GameService gameService;
    
    private String gameIdOfGameInService;
    private Board boardOfGameInService;

    private final String nonUsedGameId = "nonUsedGameId";


    @BeforeEach
    void GameServiceSetup() {
        gameService = new GameService();

        // Make a game to be used in tests
        String gameType = "tic-tac-toe";
        String botType = "minimax";
        String playerColor = "white";
        GameStartRequest request = new GameStartRequest(playerColor, botType, gameType);
        GameResponse response = gameService.startGame(request);
        gameIdOfGameInService = response.getGameId();

    }

    @Nested
    @DisplayName("Test of tic-tac-toe scenarios")
    class PlayGameScenarios {

        @Test
        @DisplayName("Test minimax tries to win")
        void testMinimaxTriesToWin() {
            String expected = "X";
            gameService.playerMove(new MoveRequest(gameIdOfGameInService, 0, 0)); // X
            gameService.playerMove(new MoveRequest(gameIdOfGameInService, 1, 0)); // O
            gameService.playerMove(new MoveRequest(gameIdOfGameInService, 0, 1)); // X
            gameService.playerMove(new MoveRequest(gameIdOfGameInService, 1, 1)); // O

            // Minimax should try to win
            GameResponse gameResponse = gameService.botMove(gameIdOfGameInService); // X
            Board board = gameResponse.getBoard();
            String actual = board.getPosition(0, 2);
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test minimax tries to block")
        void testMinimaxTriesToBlock() {
            // X should block
            // x o x |    | x o x 
            // x o - | -> | x o - 
            // o - - |    | o x - 

            String expected = "X";
            gameService.playerMove(new MoveRequest(gameIdOfGameInService, 0, 0)); // X
            gameService.playerMove(new MoveRequest(gameIdOfGameInService, 0, 2)); // O
            gameService.playerMove(new MoveRequest(gameIdOfGameInService, 2, 0)); // X
            gameService.playerMove(new MoveRequest(gameIdOfGameInService, 1, 0)); // O
            gameService.playerMove(new MoveRequest(gameIdOfGameInService, 0, 1)); // X
            gameService.playerMove(new MoveRequest(gameIdOfGameInService, 1, 1)); // O

            // Minimax should try to block
            GameResponse gameResponse = gameService.botMove(gameIdOfGameInService); // X
            Board board = gameResponse.getBoard();
            String actual = board.getPosition(1, 2);
            assertEquals(expected, actual);
        }


    }
}
