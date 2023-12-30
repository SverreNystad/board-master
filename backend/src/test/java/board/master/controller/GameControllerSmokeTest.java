package board.master.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    private String badRequestJson;

    private final String startGameEndpoint = "/api/start";
    private final String playerMoveEndpoint = "/api/move";
    private final String botMoveEndpoint = "/api/bot-move";

    @BeforeEach
    public void setup() throws Exception {
        badRequestJson = "{\"playerColor\":\"illegal\", \"botType\":\"illegal\", \"gameType\":\"illegal\"}";
        legalGameStartJson = "{\"playerColor\":\"white\", \"botType\":\"Random\", \"gameType\":\"tic-tac-toe\"}";
        
        // Start a game to be used in tests
        MvcResult result = mockMvc.perform(post(startGameEndpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(legalGameStartJson))
                .andExpect(status().isOk())
                .andReturn();
        
        // Retrieve the gameId from the response
        String responseContent = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseContent);
        gameInService = rootNode.path("gameId").asText(); // Assuming "gameId" is the field in your response
        
        // Create legal move requests for the game in service
        legalPlayerMoveJson = String.format("{\"gameId\":\"%s\", \"x\":1, \"y\":2}", gameInService);
        legalBotMoveJson = String.format("{\"gameId\":\"%s\"}", gameInService);
    }

    @Test
    @DisplayName("Test that startGameEndpoint works for legal request")
    public void startGameEndpointSmokeTest() throws Exception {
        mockMvc.perform(post(startGameEndpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(legalGameStartJson))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test that startGameEndpoint works for bad request")
    public void startGameEndpointBadRequestSmokeTest() throws Exception {
        mockMvc.perform(post(startGameEndpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(badRequestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Test that playerMoveEndpoint works for legal ")
    public void playerMoveEndpointSmokeTest() throws Exception {
        mockMvc.perform(post(playerMoveEndpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(legalPlayerMoveJson))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test that playerMoveEndpoint works for bad request ")
    public void playerMoveEndpointBadRequestSmokeTest() throws Exception {
        mockMvc.perform(post(playerMoveEndpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(badRequestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Test that botMoveEndpoint works for legal ")
    public void botMoveEndpointSmokeTest() throws Exception {
        // Note: This requires a valid gameId which should be obtained from startGame response
        mockMvc.perform(post(botMoveEndpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(legalBotMoveJson))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test that botMoveEndpoint works for bad request ")
    public void botMoveEndpointBadRequestSmokeTest() throws Exception {
        // Note: This requires a valid gameId which should be obtained from startGame response
        mockMvc.perform(post(botMoveEndpoint)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(badRequestJson))
                .andExpect(status().isBadRequest());
    }
}
