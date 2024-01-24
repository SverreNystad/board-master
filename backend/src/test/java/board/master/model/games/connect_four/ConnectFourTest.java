package board.master.model.games.connect_four;

import org.junit.jupiter.api.Test;

import board.master.model.games.Board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

public class ConnectFourTest {

    private ConnectFour connectFour;
    private int columnHeight;

    @BeforeEach
    void setUp() {
        connectFour = new ConnectFour();
        columnHeight = 7;
    }

    @Nested
    class TestGetActions {

        @Test
        void testGetAllActionsAtStart() {
            int expected = 7;
            int actual = connectFour.getActions().size();
            assertEquals(expected, actual);
        }

        @Test
        void testGetAllActionsAfterOneMove() {
            int expected = 7;
            connectFour.result(connectFour.getActions().get(0));
            int actual = connectFour.getActions().size();
            assertEquals(expected, actual);
        }

        @Test
        void testGetAllActionsAfterColumnIsFull() {
            int expected = 6;
            for (int i = 0; i < columnHeight; i++) {
                connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            }
            int actual = connectFour.getActions().size();
            assertEquals(expected, actual);
        }

    }

    @Nested
    class testGetBoard {

        @Test
        void testGetBoardAtStart() {
            for (int x = 0; x < connectFour.getBoard().getRows(); x++) {
                for (int y = 0; y < connectFour.getBoard().getColumns(); y++) {
                    String expected = "";
                    String actual = connectFour.getBoard().getPosition(x, y);
                    assertEquals(expected, actual);
                }
            }
        }

        @Test
        void testGetBoardAfterOneMove() {
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            String laidPiece = connectFour.getBoard().getPosition(0, 0);
            assertEquals("X", laidPiece);
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            laidPiece = connectFour.getBoard().getPosition(0, 1);
            assertEquals("O", laidPiece);
        }

    }

    @Nested
    class testIsTerminal {

        @Test
        void testIsTerminalAtStart() {
            boolean expected = false;
            boolean actual = connectFour.isTerminal();
            assertEquals(expected, actual);
        }

        @Test
        void testIsTerminalAfterLessThenPossibleWinningCombination() {
            boolean expected = false;
            int numberOfMoves = 3;
            for (int i = 0; i < numberOfMoves; i++) {
                connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(0));
                connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            }
            boolean actual = connectFour.isTerminal();
            assertEquals(expected, actual);
        }

        @Test
        void testIsTerminalAfterGameIsWon() {
            boolean expected = true;
            // Player 1 makes tower in column 0 and wins
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            boolean actual = connectFour.isTerminal();
            assertEquals(expected, actual);
            

        }

    }
    
    @Nested
    class TestResult {

        @Test
        void testResultDoesNotMutateOriginal() {
            ConnectFour originalStateHandler = connectFour;
            int originalToMove = connectFour.toMove();
            Board originalBoard = connectFour.getBoard();
            ConnectFour transformedStateHandler = (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            assertEquals(originalBoard, originalStateHandler.getBoard());
            assertEquals(originalToMove, originalStateHandler.toMove());
        }

        @Test
        void testResultsTransformationHasTransformed() {
            ConnectFour originalStateHandler = connectFour;
            ConnectFour transformedStateHandler = (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            assertNotEquals(originalStateHandler, transformedStateHandler);
            assertNotEquals(originalStateHandler.getBoard(), transformedStateHandler.getBoard());
            
        }

    }
    @Nested
    class TestToMove {

        @Test
        void testToMoveAtStart() {
            int expected = 1;
            int actual = connectFour.toMove();
            assertEquals(expected, actual);
        }

        @Test
        void testToMoveAfterOneMove() {
            int expected = -1;
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            int actual = connectFour.toMove();
            assertEquals(expected, actual);
        }

        @Test
        void testToMoveAfterTwoMoves() {
            int expected = 1;
            connectFour.result(connectFour.getActions().get(0));
            connectFour.result(connectFour.getActions().get(0));
            int actual = connectFour.toMove();
            assertEquals(expected, actual);
        }

    }
    
    @Nested
    class testUtility {
        @Test
        void testUtilityAtStart() {
            int expected = 0;
            int actual = connectFour.utility(1);
            assertEquals(expected, actual);
        }

        @Test
        void testUtilityAfterOneMove() {
            int expected = 0;
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            int actual = connectFour.utility(1);
            assertEquals(expected, actual);
        }

        @Test
        void testUtilityAfter3InAColumn() {
            int baseNumber = 2;
            int playerPiecesInARow = 3;
            int botPiecesInARow = 2;

            // The value for player - the value for bot
            int expected = (int) Math.pow(baseNumber, playerPiecesInARow) 
            - (int) Math.pow(baseNumber, botPiecesInARow);

            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            int actual = connectFour.utility(1);
            assertEquals(expected, actual);
        }

        @Test
        void testUtilityAfter3InARow() {
            int baseNumber = 2;
            int playerPiecesInARow = 3;
            int botPiecesInARow = 2;

            // The value for player - the value for bot
            int expected = (int) Math.pow(baseNumber, playerPiecesInARow) 
            - (int) Math.pow(baseNumber, botPiecesInARow);

            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(2));
            
            int actual = connectFour.utility(1);
            assertEquals(expected, actual);
        }

        @Test
        void testUtilityAfter3Diagonally() {
            int baseNumber = 2;
            int playerPiecesInARow = 3;
            int botPiecesInARow = 2;

            // The value for player - the value for bot
            int expected = (int) Math.pow(baseNumber, playerPiecesInARow) 
            - (int) Math.pow(baseNumber, botPiecesInARow);

            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour = (ConnectFour) connectFour.result(connectFour.getActions().get(2));
        }

    }
}
