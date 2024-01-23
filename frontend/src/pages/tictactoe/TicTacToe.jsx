import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { apiRoutes } from '../../routes/routeDefinitions';
import Board from '../../components/game/Board';
import Error from '../../components/game/Error';
import './TicTacToe.css';
const gameService = require('../../services/gameService');


function TicTacToe() {
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

  const startGame = async () => {
    try {
      // Reset state
      setGameStarted(false);
      setErrorMessage(null);

      console.log("Starting Game");
      // Mock request data
      let requestData = {
        botType: botType,
        gameType: "tic-tac-toe"
      };
      const response = await axios.post(apiRoutes.startGame, requestData);

      // Update state with response data
      setGameData(response.data);
      console.log("Game Started:", response.data);
      setGameStarted(true);
    } catch (error) {
      setErrorMessage(error.message);
      console.error("Error starting game:", error);
    }
  }

  const makeMove = async (x, y) => {
    console.log("Making Move" + x + y);
    setErrorMessage(null);

    let requestBoard = {
      gameId: gameData.gameId,
      x: x,
      y: y
    } 
    if (gameData.status === "Game in progress") {
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
  }

  const botMove = async () => {
    console.log("Bot Move");
    setErrorMessage(null);
    setShallLoad(true);

    let requestBody = {
      gameId: gameData.gameId,
    }
    if (gameData.status === "Game in progress") {
      const response = gameService.botMove(requestBody)
        .then(result => {
          // Update state with response data
          setGameData(result);
          console.log('Bot move result:', result);
      })
      .catch(error => {
        setErrorMessage(error.message);
        console.error('Error while making bot move:', error);
      });
    }

    setShallLoad(false);
  }



  return (
    <div className="TicTacToe">
      <h1>BoardMaster TicTacToe</h1>
      <div className='options'>
        <h2>Choose bot-type:</h2>
        <select onChange={(e) => setBotType(e.target.value)}>
          {availableBots.map((bot) => {
            return <option value={bot}>{bot}</option>
          })}
        </select>

        <h2>Choose who starts:</h2>
        <form>
          <input type="radio" id="player" name="start-player" value="player" 
            onChange={() => setPlayerStarts(true)}
            checked={playerStarts}
          />
          <label for="player">Player</label>
          <input type="radio" id="bot" name="start-player" value="bot" 
            onChange={() => setPlayerStarts(false)}
            checked={!playerStarts}
          />
          <label for="bot">Bot</label>
        </form>
      </div>
      {!!!gameData && <button onClick={startGame}>Start Game</button>}
      
      {gameData && (
        <div>
          <button onClick={startGame}>Restart Game</button>
          <p>Game ID: {gameData.gameId}</p>
          <p>Status: {gameData.status}</p>
          <div className='board-container'>
            <Board grid={gameData.board.grid} onClickCallback={makeMove} shallLoad={shallLoad} placeSign={(playerStarts) ? "X" : "O"}/> 
          </div>
        </div>
      )}
      {/* <Error error={errorMessage} /> */}
    </div>
  );
}

export default TicTacToe;
