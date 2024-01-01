package board.master.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import board.master.model.communication.GameResponse;
import board.master.model.communication.GameStartRequest;
import board.master.model.communication.MoveRequest;
import board.master.model.games.Board;

public class ServiceIntegrationTest {
    
    private GameService gameService;
    
    private String gameIdOfGameInService;


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

        @Test
        @DisplayName("Test minimax tries to win")
        void testMinimaxTriesToWin2() {
            // X should block
            // x - x |    | x - x 
            // o o - | -> | o o o 
            // - x - |    | - x - 

            String expected = "O";
            gameService.playerMove(new MoveRequest(gameIdOfGameInService, 0, 0)); // X
            gameService.playerMove(new MoveRequest(gameIdOfGameInService, 1, 1)); // O
            gameService.playerMove(new MoveRequest(gameIdOfGameInService, 1, 2)); // X
            gameService.playerMove(new MoveRequest(gameIdOfGameInService, 0, 1)); // O
            gameService.playerMove(new MoveRequest(gameIdOfGameInService, 2, 0)); // X

            // Minimax should try to win
            GameResponse gameResponse = gameService.botMove(gameIdOfGameInService); // O
            Board board = gameResponse.getBoard();
            String actual = board.getPosition(2, 1);
            assertEquals(expected, actual);
        }
    }
}
