import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { apiRoutes } from '../../routes/routeDefinitions';
import Board from './Board';
import Error from './Error';
import './TicTacToe.css';


function TicTacToe() {
  const [gameData, setGameData] = useState(null);
  const [shallLoad, setShallLoad] = useState(false);
  const [errorMessage, setErrorMessage] = useState(null);
  const [botType, setBotType] = useState("");
  const [availableBots, setAvailableBots] = useState([]);

  useEffect(() => {
    getAgents();
    console.log(availableBots);
  }, []);

  useEffect(() => {
    console.log(gameData);
  }, [gameData]);

  useEffect(() => {
    setBotType(availableBots[0]);
  } , [availableBots]);

  useEffect(() => { 
    console.log(botType);
  }, [botType]);



  const startGame = async () => {
    try {
      console.log("Starting Game");
      // Mock request data
      let requestData = {
        playerColor: "white", // or "black"
        botType: botType,
        gameType: "tic-tac-toe" // or "chess", based on your game logic
      };
      const response = await axios.post(apiRoutes.startGame, requestData);

      // Update state with response data
      setGameData(response.data);
      console.log("Game Started:", response.data);
    } catch (error) {
      setErrorMessage(error.message);
      console.error("Error starting game:", error);
    }
  }

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

  const makeMove = async (x, y) => {
    console.log("Making Move" + x + y);

    let requestBoard = {
      gameId: gameData.gameId,
      x: x,
      y: y
    } 

    try {
      const response = await axios.post(apiRoutes.makeMove, requestBoard);
      // Update state with response data
      setGameData(response.data);
      console.log("Move Made:", response.data);
      botMove();
    } catch (error) {
      setErrorMessage(error.message);
      console.error("Error making move:", error);
    }

  }

  const botMove = async () => {
    console.log("Bot Move");
    setShallLoad(true);
    console.log(gameData.gameId);
    let requestBody = {
      gameId: gameData.gameId,
    }
    try {
      const response = await axios.post(apiRoutes.botMove, requestBody);

      // Update state with response data
      setGameData(response.data);
      console.log("Bot Move:", response.data);
    } catch (error) {
      setErrorMessage(error.message);
      console.error("Error making bot move:", error);

    } finally {
      setShallLoad(false);
    }
  }



  return (
    <div className="TicTacToe">
      <h1>BoardMaster TicTacToe</h1>
      <h2>Choose bot-type:</h2>
      <div>
        <select onChange={(e) => setBotType(e.target.value)}>
          {availableBots.map((bot) => {
            return <option value={bot}>{bot}</option>
          })}
        </select>
      </div>
      <button onClick={startGame}>Start Game</button>
      {gameData && (
        <div>
          <p>Game ID: {gameData.gameId}</p>
          <p>Status: {gameData.status}</p>
          <div className='board-container'>
            <Board grid={gameData.board.grid} onClickCallback={makeMove} shallLoad={shallLoad}/> {/* Render the Board */}
          </div>
        </div>
      )}
      <Error error={errorMessage} />
    </div>
  );
}

export default TicTacToe;
