package board.master.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import board.master.model.Board;
import board.master.model.GameResponse;
import board.master.model.GameStartRequest;
import board.master.model.PlayerMoveRequest;
import board.master.model.games.chess.Chess;
import board.master.model.games.tictactoe.TicTacToe;

public class GameServiceTest {
    
    private GameService gameService;
    
    private String gameIdOfGameInService;
    private Board boardOfGameInService;

    @BeforeEach
    void GameServiceSetup() {
        gameService = new GameService();

        // Make a game to be used in tests
        String gameType = "tic-tac-toe";
        String botType = "random";
        String playerColor = "white";
        GameStartRequest request = new GameStartRequest(playerColor, botType, gameType);
        GameResponse response = gameService.startGame(request);
        gameIdOfGameInService = response.getGameId();

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
            Board expectedBoard = Chess.CreateInitialBoard();
            GameStartRequest request = new GameStartRequest(playerColor, botType, gameType);
            GameResponse response = gameService.startGame(request);
            assertEquals(expectedBoard, response.getBoard());
        }

        @Test
        @DisplayName("Test of startGame with tic-tac-toe")
        void testStartGameTicTacToe() {
            String gameType = "tic-tac-toe";
            String botType = "random";
            String playerColor = "white";
            Board expectedBoard = (new TicTacToe()).getBoard();

            GameStartRequest request = new GameStartRequest(playerColor, botType, gameType);
            GameResponse response = gameService.startGame(request);
            assertEquals(expectedBoard, response.getBoard());
        }
    }

    @Nested
    @DisplayName("Test of playerMove")
    class playerMove {
        @Test
        @DisplayName("Test of playerMove changes the board in the game")
        void testPlayerMoveChess() {
            int x = 1;
            int y = 1;

            Board originalBoard = boardOfGameInService;

            PlayerMoveRequest request = new PlayerMoveRequest(gameIdOfGameInService, x, y);
            GameResponse response = gameService.playerMove(request);
            assertNotEquals(originalBoard, response.getBoard());
        }

        @Test
        @DisplayName("Test of playerMove with tic-tac-toe")
        void testPlayerMoveTicTacToe() {
            String gameType = "tic-tac-toe";
            String botType = "random";
            String playerColor = "white";
            Board expectedBoard = (new TicTacToe()).getBoard();

            GameStartRequest request = new GameStartRequest(playerColor, botType, gameType);
            GameResponse response = gameService.startGame(request);
            assertEquals(expectedBoard, response.getBoard());
        }
    }


        @Test
        @DisplayName("Test of BotMOve changes the board in the game")
        void testPlayerMoveChess() {
            Board originalBoard = boardOfGameInService;

            String gameId = gameIdOfGameInService;
            GameResponse response = gameService.botMove(gameId);
            assertNotEquals(originalBoard, response.getBoard());
        }
}
