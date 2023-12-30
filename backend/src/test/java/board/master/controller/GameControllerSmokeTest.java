package board.master.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerSmokeTest {

    @Autowired
    private MockMvc mockMvc;

    private String legalGameStartJson;

    private String gameInService;
    @BeforeEach
    public void setup() throws Exception {
        
        // Start a game to be used in tests
        gameInService = (String) mockMvc
            .perform(post("/api/start")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(legalGameStartJson))
                .andReturn().getAsyncResult();
        
        legalGameStartJson = "{\"playerColor\":\"white\", \"botType\":\"Random\", \"gameType\":\"chess\"}";

    }

    @Test
    public void startGameEndpoint_SmokeTest() throws Exception {
        mockMvc.perform(post("/api/start")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(legalGameStartJson))
                .andExpect(status().isOk());
    }

    @Test
    public void playerMoveEndpoint_SmokeTest() throws Exception {
        // Note: This requires a valid gameId which should be obtained from startGame response
        String playerMoveJson = "{\"gameId\":\"12345\", \"move\":{\"x\":1, \"y\":2}}";
        mockMvc.perform(post("/api/move")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(playerMoveJson))
                .andExpect(status().isOk());
    }

    @Test
    public void botMoveEndpoint_SmokeTest() throws Exception {
        // Assuming a JSON payload for GameId
        // Note: This requires a valid gameId which should be obtained from startGame response
        String botMoveJson = "{\"gameId\":\"12345\"}";
        mockMvc.perform(post("/api/bot-move")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(botMoveJson))
                .andExpect(status().isOk());
    }
}
