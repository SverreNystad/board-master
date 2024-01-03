// gameService.js
import axios from "axios";
import { apiRoutes } from "../routes/routeDefinitions"; // Adjust the import path as necessary

const getAgents = async (setError) => {
  try {
    const response = await axios.get(apiRoutes.getAgents);
    return response.data;
  } catch (error) {
    setError(error.message);
    console.error("Error getting agents:", error);
    return null;
  }
};

const startGame = async (botType, gameType, setError) => {
  try {
    let requestData = {
      botType: botType,
      gameType: gameType,
    };
    const response = await axios.post(apiRoutes.startGame, requestData);
    return response.data;
  } catch (error) {
    setError(error.message);
    console.error("Error starting game:", error);
    return null;
  }
};

const makeMove = async (gameId, x, y, setError) => {
  try {
    let requestBoard = {
      gameId: gameId,
      x: x,
      y: y,
    };
    const response = await axios.post(apiRoutes.makeMove, requestBoard);
    return response.data;
  } catch (error) {
    setError(error.message);
    console.error("Error making move:", error);
    return null;
  }
};

const botMove = async (gameId, setError, setLoading) => {
  try {
    setLoading(true);
    let requestBody = {
      gameId: gameId,
    };
    const response = await axios.post(apiRoutes.botMove, requestBody);
    return response.data;
  } catch (error) {
    setError(error.message);
    console.error("Error making bot move:", error);
    return null;
  } finally {
    setLoading(false);
  }
};

export { getAgents, startGame, makeMove, botMove };
