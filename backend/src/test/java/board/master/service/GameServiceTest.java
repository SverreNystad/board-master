package board.master.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import board.master.model.GameResponse;
import board.master.model.GameStartRequest;
import board.master.model.games.chess.Chess;

public class GameServiceTest {
    
    private GameService gameService;

    @BeforeEach
    void GameServiceSetup() {
        gameService = new GameService();
    }

    @Nested
    @DisplayName("Test of startGame")
    class CreationOfGames {
        
        @Test
        @DisplayName("Test of startGame with chess")
        void testStartGameChess() {
            String gameType = "chess";
            String botType = "random";
            String playerColor = "white";
            GameStartRequest request = new GameStartRequest(playerColor, botType, gameType);
            GameResponse response = gameService.startGame(request);
            assertEquals(Chess.CreateInitialBoard(), response.getBoard());
        }
    }

    @Test
    void testPlayerMove() {

    }

    @Test
    void testStartGame() {

    }
}
