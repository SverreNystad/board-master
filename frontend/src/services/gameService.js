import axios from 'axios';
import { apiRoutes } from '../routes/routeDefinitions';


/**
 * Accesses the backend API to get the list of agents
 * @returns {Promise} Resolves to an array of agents
 */
const getAgents = async () => {
  try {
    console.log("Getting Agent");
    const response = await axios.get(apiRoutes.getAgents);

    // Update state with response data
    console.log("Agent:", response.data);
    return response.data;
  } catch (error) {
    console.error("Error getting agent:", error);
    throw error;
  }
}



const startGame = async (gameType, botType) => {
    try {
      // Mock request data
      let requestData = {
          gameType: gameType,
          botType: botType
      };
      const response = await axios.post(apiRoutes.startGame, requestData);
      return response.data;

    } catch (error) {
      throw error;
    }
  }

/**
 * Accesses the backend API to make a move
 * @param {object} gameData The game data containing the game ID
 * @param {number} x The x coordinate of the move
 * @param {number} y The y coordinate of the move
 * @returns {Promise} Resolves to the updated game data
 * @throws {Error} If the move is invalid
 * @throws {Error} If the game is already over
 * @throws {Error} If the game is not found
 */
const makeMove = async (gameData, x, y) => {
    console.log("Making Move" + x + y);
    let requestBoard = {
      gameId: gameData.gameId,
      x: x,
      y: y
    } 

    try {
      const response = await axios.post(apiRoutes.makeMove, requestBoard);
      return response.data;
    } catch (error) {
      throw error;
    }
}

/**
 * Accesses the backend API to get the bot's move
 * @param {object} gameData The game data containing the game ID
 * @returns {Promise} Resolves to the updated game data
 * @throws {Error} If the game is already over
 * @throws {Error} If the game is not found
 */
const botMove = async (gameData) => {
    let requestBody = {
        gameId: gameData.gameId,
    }

    try {
        const response = await axios.post(apiRoutes.botMove, requestBody);
        return response.data;
    } catch (error) {
        throw error;
    } 
}

export  { getAgents, startGame, makeMove, botMove };