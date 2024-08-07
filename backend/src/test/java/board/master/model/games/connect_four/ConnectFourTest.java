package board.master.model.games.connect_four;

import board.master.model.agents.Agent;
import board.master.model.agents.IterativeDeepeningAlphaBetaPruningMinimax;
import board.master.model.games.Board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ConnectFourTest {

    private ConnectFour connectFour;
    private final int rowLength = 6;
    private final int columnHeight = 7;
    private final int baseNumber = 2;

    @BeforeEach
    void setUp() {
        connectFour = new ConnectFour();
    }

    @Nested
    class TestGetActions {

        @Test
        void testGetAllActionsAtStart() {
            int expected = columnHeight;
            int actual = connectFour.getActions().size();
            assertEquals(expected, actual);
        }

        @Test
        void testGetAllActionsAfterOneMove() {
            int expected = columnHeight;
            connectFour.result(connectFour.getActions().get(0));
            int actual = connectFour.getActions().size();
            assertEquals(expected, actual);
        }

        @Test
        void testGetAllActionsAfterColumnIsFull() {
            int expected = columnHeight - 1;
            for (int i = 0; i < columnHeight; i++) {
                connectFour = (ConnectFour)
                        connectFour.result(connectFour.getActions().get(0));
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
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            String laidPiece = connectFour.getBoard().getPosition(rowLength - 1, 0);
            assertEquals("X", laidPiece);
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            laidPiece = connectFour.getBoard().getPosition(rowLength - 2, 0);
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
                connectFour = (ConnectFour)
                        connectFour.result(connectFour.getActions().get(0));
                connectFour = (ConnectFour)
                        connectFour.result(connectFour.getActions().get(1));
            }
            boolean actual = connectFour.isTerminal();
            assertEquals(expected, actual);
        }

        @Test
        void testIsTerminalAfterGameIsWon() {
            boolean expected = true;
            // Player 1 makes tower in column 0 and wins
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            boolean actual = connectFour.isTerminal();
            assertEquals(expected, actual);
        }

        @Test
        void testIsTerminalAfterGameIsWonHorizontally() {
            boolean expected = true;
            // Player 2 makes tower in column 0 and wins
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(4));
            boolean actual = connectFour.isTerminal();
            assertEquals(expected, actual);
        }

        @Test
        void testIsTerminalAfterGameIsWonDiagonallyLeftToRight() {
            boolean expected = true;
            // Player 2 makes a diagonal and wins
            // Column 0
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1)); // X
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0)); // O
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0)); // X
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0)); // O
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1)); // X
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0)); // O

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2)); // X
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1)); // O
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1)); // X

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2)); // O
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2)); // X

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3)); // O

            boolean actual = connectFour.isTerminal();
            assertEquals(expected, actual);
        }

        @Test
        void testIsTerminalAfterGameIsWonDiagonallyRightToLeft() {
            boolean expected = true;
            // Player 1 makes a diagonal and wins
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));
            boolean actual = connectFour.isTerminal();
            assertEquals(expected, actual);
        }

        @Test
        void testIsTerminalAfterGameIsWonDiagonallyInTopLeftCorner() {
            boolean expected = true;
            // Player 1 makes a diagonal and wins
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(6));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(5));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(4));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(6));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(5));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(4));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(4));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(5));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(6));

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(4));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));

            boolean actual = connectFour.isTerminal();
            assertEquals(expected, actual);
        }

        @Test
        void testIsTerminalAfterGameIsWonDiagonallyInTopRightCorner() {
            boolean expected = true;
            // Player 1 makes a diagonal and wins
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));

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
            ConnectFour transformedStateHandler =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            assertEquals(originalBoard, originalStateHandler.getBoard());
            assertEquals(originalToMove, originalStateHandler.toMove());
        }

        @Test
        void testResultsTransformationHasTransformed() {
            ConnectFour originalStateHandler = connectFour;
            ConnectFour transformedStateHandler =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
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
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
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
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            int actual = connectFour.utility(1);
            assertEquals(expected, actual);
        }

        @Test
        void testUtilityAfter3InAColumn() {
            int playerPiecesInAColumn = 2;
            int botPiecesInARow = 1;

            // The value for player - the value for bot
            int expected =
                    (int) Math.pow(baseNumber, playerPiecesInAColumn) - (int) Math.pow(baseNumber, botPiecesInARow);

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            int actual = connectFour.utility(1);
            assertEquals(expected, actual);
        }

        @Test
        void testUtilityAfter3InARow() {
            int playerPiecesInARow = 2;
            int botPiecesInARow = 1;

            // The value for player - the value for bot
            int expected = (int) Math.pow(baseNumber, playerPiecesInARow) - (int) Math.pow(baseNumber, botPiecesInARow);

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));

            int actual = connectFour.utility(1);
            assertEquals(expected, actual);
        }

        @Test
        void testUtilityAfterLeftToRightDiagonal() {
            int playerPiecesInADiagonal = 2;
            int botPiecesInARow = 1;

            // Player has 2 diagonals with 3 and 2 pieces in a row respectively
            int playerDiagonalValue = (int) Math.pow(baseNumber, playerPiecesInADiagonal);

            // Bot has 2 diagonal with 2 pieces in a row
            int botDiagonalValue = (int) Math.pow(baseNumber, botPiecesInARow) * 3;

            // The value for player - the value for bot
            int expected = playerDiagonalValue - botDiagonalValue;

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));

            int actual = connectFour.utility(1);

            assertEquals(expected, actual);
        }

        @Test
        void testUtilityAfterRightToLeftDiagonally() {
            int playerPiecesInADiagonal = 2;
            int botPiecesInARow = 1;

            // Player has 2 diagonals with 3 and 2 pieces in a row respectively
            int playerDiagonalValue = (int) Math.pow(baseNumber, playerPiecesInADiagonal)
                    + (int) Math.pow(baseNumber, playerPiecesInADiagonal - 1);

            // Bot has 2 diagonal with 2 pieces in a row
            int botDiagonalValue = (int) Math.pow(baseNumber, botPiecesInARow) * 2;

            // The value for player - the value for bot
            int expected = playerDiagonalValue - botDiagonalValue;

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));

            int actual = connectFour.utility(1);

            assertEquals(expected, actual);
        }

        @Test
        void testUtilityAfterDiagonalVerticalAndHorizontally() {
            int playerPiecesInADiagonal = 2;
            int botPiecesInARow = 1;

            // Player has 2 diagonals with 3 pieces, a horizontal with 2 pieces in a row and a vertical with 3 pieces in
            // a row
            int playerDiagonalValue = (int) Math.pow(baseNumber, playerPiecesInADiagonal) * 2;

            // Bot has 2 diagonal with 2 and 3 pieces in a row and a horizontal with 2 pieces in a row
            int botDiagonalValue =
                    (int) Math.pow(baseNumber, botPiecesInARow) * 2 + (int) Math.pow(baseNumber, botPiecesInARow + 1);

            // The value for player - the value for bot
            int expected = playerDiagonalValue - botDiagonalValue;

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(4));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));

            int actual = connectFour.utility(1);

            assertEquals(expected, actual);
        }

        @Test
        void testUtilityWithSymbolInTopLeftCorner() {

            // Player has 2 pieces in a row vertically, but they are locked in
            int playerDiagonalValue = 0;

            // Bot has 0 pieces in a row
            int botDiagonalValue = 0;

            // The value for player - the value for bot
            int expected = playerDiagonalValue - botDiagonalValue;

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(4));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));

            int actual = connectFour.utility(1);

            assertEquals(expected, actual);
        }

        @Test
        void testUtilityTerminalStatePlayerPos1WhenPos1Win() {
            int expected = Integer.MAX_VALUE;

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));

            int actual = connectFour.utility(1);

            assertEquals(expected, actual);
        }

        @Test
        void testUtilityTerminalStatePlayerNeg1WhenPos1Win() {
            int expected = Integer.MIN_VALUE;

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));

            int actual = connectFour.utility(-1);

            assertEquals(expected, actual);
        }

        @Test
        void testUtilityTerminalStatePlayerPos1WhenNeg1Win() {
            int expected = Integer.MIN_VALUE;

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));

            int actual = connectFour.utility(1);

            assertEquals(expected, actual);
        }

        @Test
        void testUtilityTerminalStatePlayerNeg1WhenNeg1Win() {
            int expected = Integer.MAX_VALUE;

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(0));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));

            int actual = connectFour.utility(-1);

            assertEquals(expected, actual);
        }

        @Test
        void testUtilityOfSpecificStateFirst() {
            int expected = -14;

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(1));

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));

            int actual = connectFour.utility(-1);

            assertEquals(expected, actual);
        }

        @Test
        void testUtilityOfSpecificStateSecond() {
            int expected = -4;

            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(3));
            connectFour =
                    (ConnectFour) connectFour.result(connectFour.getActions().get(2));

            Agent iterative = new IterativeDeepeningAlphaBetaPruningMinimax(2);

            // Action action = iterative.getAction(connectFour);
            int actual = connectFour.utility(-1);

            assertEquals(expected, actual);
        }
    }
}
