import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { apiRoutes } from '../../routes/routeDefinitions';
import Board from '../../components/game/Board';
import Error from '../../components/game/Error';
import './ConnectFour.css';
const gameService = require('../../services/gameService');

function ConnectFour() {
    const [gameData, setGameData] = useState(null);
    const [shallLoad, setShallLoad] = useState(false);
    const [botType, setBotType] = useState("");
    const [availableBots, setAvailableBots] = useState([]);
    const [playerStarts, setPlayerStarts] = useState(true);
    const [gameStarted, setGameStarted] = useState(false);
    const [errorMessage, setErrorMessage] = useState(null);
  
    useEffect(() => {
      getAgents();
      console.log(availableBots);
    }, []);
  
    useEffect(() => {
      setBotType(availableBots[0]);
    } , [availableBots]);
  
    useEffect(() => {
      if (gameStarted && gameData && !playerStarts) {
        botMove();
      }
    }, [gameStarted]);
  
  
    const getAgents = async () => {
      try {
        console.log("Getting Agent");
        const response = await axios.get(apiRoutes.getAgents);
  
        // Update state with response data
        setAvailableBots(response.data);
        console.log("Agent:", response.data);
      } catch (error) {
        setErrorMessage(error.message);
        console.error("Error getting agent:", error);
      }
  
    }


}


export default ConnectFour;