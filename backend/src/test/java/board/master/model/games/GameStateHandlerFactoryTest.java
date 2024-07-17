package board.master.model.games;

import board.master.model.StateHandler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameStateHandlerFactoryTest {
    @Test
    @DisplayName("Test of createGameHandler with legal stateHandler type")
    void testCreateGameHandler() {
        String stateHandlerType =
                GameStateHandlerFactory.getSupportedGameHandlersList().get(0);
        StateHandler stateHandler = GameStateHandlerFactory.createGameHandler(stateHandlerType);
        assert (stateHandler != null);
        assert (stateHandler instanceof StateHandler);
    }

    @Test
    @DisplayName("Test of createGameHandler with illegal stateHandler type")
    void testCreateGameHandlerIllegal() {
        String stateHandlerType = "illegal";
        assertThrows(IllegalArgumentException.class, () -> {
            GameStateHandlerFactory.createGameHandler(stateHandlerType);
        });
    }

    @Test
    @DisplayName("Test of isValidstateHandlerType with all legal stateHandler types")
    void testIsValidstateHandlerType() {
        for (String stateHandlerType : GameStateHandlerFactory.getSupportedGameHandlersList()) {
            assert (GameStateHandlerFactory.isValidGameHandler(stateHandlerType));
        }
    }
}
