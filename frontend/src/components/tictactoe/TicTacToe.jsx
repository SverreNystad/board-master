import React, { useState } from 'react';
import axios from 'axios';
import { apiRoutes } from '../../routes/routeDefinitions';
import Board from './Board';
import Error from './Error';
import './TicTacToe.css';


function TicTacToe() {
  const [gameData, setGameData] = useState(null);
  const [shallLoad, setShallLoad] = useState(false);
  const [errorMessage, setErrorMessage] = useState(null);

  const startGame = async () => {
    try {
      console.log("Starting Game");
      // Mock request data
      let requestData = {
        playerColor: "white", // or "black"
        botType: "minimax", // or "random"
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
    let requestBody = {
      gameId: gameData.gameId
    }
    try {
      const response = await axios.get(apiRoutes.botMove, requestBody);

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
