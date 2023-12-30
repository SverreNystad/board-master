package board.master.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerSmokeTest {

    @Autowired
    private MockMvc mockMvc;

    private String legalGameStartJson;

    private String gameInService;
    private String legalPlayerMoveJson;
    private String legalBotMoveJson;

    @BeforeEach
    public void setup() throws Exception {
        
        legalGameStartJson = "{\"playerColor\":\"white\", \"botType\":\"Random\", \"gameType\":\"tic-tac-toe\"}";
        // Start a game to be used in tests
        MvcResult result = mockMvc.perform(post("/api/start")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(legalGameStartJson))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseContent);
        gameInService = rootNode.path("gameId").asText(); // Assuming "gameId" is the field in your response
        
        legalPlayerMoveJson = String.format("{\"gameId\":\"%s\", \"x\":1, \"y\":2}", gameInService);
        legalBotMoveJson = String.format("{\"gameId\":\"%s\"}", gameInService);

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
        mockMvc.perform(post("/api/move")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(legalPlayerMoveJson))
                .andExpect(status().isOk());
    }

    @Test
    public void botMoveEndpoint_SmokeTest() throws Exception {
        // Note: This requires a valid gameId which should be obtained from startGame response
        mockMvc.perform(post("/api/bot-move")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(legalBotMoveJson))
                .andExpect(status().isOk());
    }
}
