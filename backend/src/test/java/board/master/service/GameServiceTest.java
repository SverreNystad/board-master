package board.master.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import board.master.model.communication.GameResponse;
import board.master.model.communication.GameStartRequest;
import board.master.model.communication.MoveRequest;
import board.master.model.games.Board;
import board.master.model.games.chess.Chess;
import board.master.model.games.tictactoe.TicTacToe;

public class GameServiceTest {
    
    private GameService gameService;
    
    private String gameIdOfGameInService;
    private Board boardOfGameInService;

    private final String nonUsedGameId = "nonUsedGameId";


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

        @Test
        @DisplayName("Test of startGame with invalid game type")
        void testStartGameInvalidGameType() {
            String gameType = "invalid";
            String botType = "random";
            String playerColor = "white";
            GameStartRequest request = new GameStartRequest(playerColor, botType, gameType);
            
            assertThrows(IllegalArgumentException.class, () -> {
                gameService.startGame(request);
            });
        }

        @Test
        @DisplayName("Test of startGame with invalid bot type")
        void testStartGameInvalidBotType() {
            String gameType = "chess";
            String botType = "invalid";
            String playerColor = "white";
            GameStartRequest request = new GameStartRequest(playerColor, botType, gameType);
            
            assertThrows(IllegalArgumentException.class, () -> {
                gameService.startGame(request);
            });
        }

        @Test
        @DisplayName("Test creating of unique gameIds for games with same start request")
        void testStartGameUniqueGameId() {
            String gameType = "chess";
            String botType = "random";
            String playerColor = "white";
            GameStartRequest request = new GameStartRequest(playerColor, botType, gameType);

            GameResponse response1 = gameService.startGame(request);
            GameResponse response2 = gameService.startGame(request);
            assertNotEquals(response1, response2);
        }
    }

    @Nested
    @DisplayName("Test of playerMove")
    class playerMove {
        @Test
        @DisplayName("Test of legal playerMove changes the board in the game")
        void testPlayerMoveChess() {
            int x = 1;
            int y = 1;

            Board originalBoard = boardOfGameInService;

            MoveRequest request = new MoveRequest(gameIdOfGameInService, x, y);
            GameResponse response = gameService.playerMove(request);
            assertNotEquals(originalBoard, response.getBoard());
        }

        @Test
        @DisplayName("Test of legal playerMove with tic-tac-toe")
        void testPlayerMoveTicTacToe() {
            String gameType = "tic-tac-toe";
            String botType = "random";
            String playerColor = "white";
            Board expectedBoard = (new TicTacToe()).getBoard();

            GameStartRequest request = new GameStartRequest(playerColor, botType, gameType);
            GameResponse response = gameService.startGame(request);
            assertEquals(expectedBoard, response.getBoard());
        }

        @Test
        @DisplayName("Test of valid request with non-existing gameId")
        void testPlayerMoveNonExistingGameId() {
            int x = 1;
            int y = 1;
            MoveRequest request = new MoveRequest(nonUsedGameId, x, y);

            assertThrows(IllegalArgumentException.class, () -> {
                gameService.playerMove(request);
            });
        }

        @Test
        @DisplayName("Test of request with illegal move")
        void testPlayerMoveIllegalMove() {
            int x = -1;
            int y = -1;
            MoveRequest request = new MoveRequest(gameIdOfGameInService, x, y);

            assertThrows(IllegalArgumentException.class, () -> {
                gameService.playerMove(request);
            });
        }
    }

    @Nested
    @DisplayName("Test of botMove")
    class botMove {
        @Test
        @DisplayName("Test of BotMove changes the board in the game")
        void testPlayerMoveChess() {
            Board originalBoard = boardOfGameInService;

            String gameId = gameIdOfGameInService;
            GameResponse response = gameService.botMove(gameId);
            assertNotEquals(originalBoard, response.getBoard());
        }

        @Test
        @DisplayName("Test of valid request with non-existing gameId")
        void testBotMoveNonExistingGameId() {
            String gameId = nonUsedGameId;

            assertThrows(IllegalArgumentException.class, () -> {
                gameService.botMove(gameId);
            });
        }


    }
}
