import React, { useState } from 'react';
import axios from 'axios';

const baseUrl = 'http://localhost:8080/api';
const startGameUrl = `${baseUrl}/start`;
const makeMoveUrl = `${baseUrl}/move`;
const botMoveUrl = `${baseUrl}/bot`;
function TicTacToe() {
  const [gameData, setGameData] = useState(null);

  const startGame = async () => {
    try {
      console.log("Starting Game");
      let requestData = {
        playerColor: "white", // or "black"
        botType: "minimax", // or "random"
        gameType: "tic-tac-toe" // or "chess", based on your game logic
      };
      // Replace with your backend URL
      const response = await axios.post(startGameUrl, requestData);

      // Update state with response data
      setGameData(response.data);
      console.log("Game Started:", response.data);
    } catch (error) {
      console.error("Error starting game:", error);
    }
  }

  
  const makeMove = async () => {
    
  }

  const botMove = async () => {

  }



  return (
    <div className="TicTacToe">
      <h1>BoardMaster TicTacToe</h1>
      <button onClick={startGame}>Start Game</button>
      {gameData && (
        <div>
          <p>Game ID: {gameData.gameId}</p>
          <p>Status: {gameData.status}</p>
          {/* Render more game data as needed */}
        </div>
      )}
    </div>
  );
}

export default TicTacToe;
