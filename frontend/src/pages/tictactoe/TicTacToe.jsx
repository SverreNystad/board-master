import React, { useEffect, useState } from 'react';
import { getAgents, startGame, makeMove, botMove } from '../../services/gameService'; // Adjust the import path as necessary
import Board from '../../components/game/Board';
import Error from '../../components/game/Error';
import './TicTacToe.css';

function TicTacToe() {
  const [gameData, setGameData] = useState(null);
  const [shallLoad, setShallLoad] = useState(false);
  const [botType, setBotType] = useState("");
  const [availableBots, setAvailableBots] = useState([]);
  const [playerStarts, setPlayerStarts] = useState(true);
  const [gameStarted, setGameStarted] = useState(false);
  const [errorMessage, setErrorMessage] = useState(null);

  
  useEffect(() => {
    const fetchAgents = async () => {
      const agents = await getAgents(setErrorMessage);
      if (agents) setAvailableBots(agents);
    };
    fetchAgents();
  }, []);

  useEffect(() => {
    if (availableBots.length > 0) {
      setBotType(availableBots[0]);
    }
  }, [availableBots]);

  useEffect(() => {
    const executeBotMove = async () => {
      if (gameStarted && gameData && !playerStarts) {
        await botMove(gameData.gameId, setErrorMessage, setShallLoad);
      }
    };
    executeBotMove();
  }, [gameStarted, gameData, playerStarts]);

  const handleStartGame = async () => {
    setGameStarted(false);
    setErrorMessage(null);

    const game = await startGame(botType, "tic-tac-toe", setErrorMessage);
    if (game) {
      setGameData(game);
      setGameStarted(true);
    }
  };

  const handleMakeMove = async (x, y) => {
    setErrorMessage(null);

    const newGameData = await makeMove(gameData.gameId, x, y, setErrorMessage);
    if (newGameData) {
      setGameData(newGameData);
      if (!playerStarts) {
        await botMove(newGameData.gameId, setErrorMessage, setShallLoad);
      }
    }
  };

  return (
    <div className="TicTacToe">
      <h1>BoardMaster TicTacToe</h1>
      <div className='options'>
        <h2>Choose bot-type:</h2>
        <select onChange={(e) => setBotType(e.target.value)} value={botType}>
          {availableBots.map((bot, index) => (
            <option key={index} value={bot}>{bot}</option>
          ))}
        </select>

        <h2>Choose who starts:</h2>
        <form>
          <input 
            type="radio" 
            id="player" 
            name="start-player" 
            value="player" 
            onChange={() => setPlayerStarts(true)}
            checked={playerStarts}
          />
          <label htmlFor="player">Player</label>
          <input 
            type="radio" 
            id="bot" 
            name="start-player" 
            value="bot" 
            onChange={() => setPlayerStarts(false)}
            checked={!playerStarts}
          />
          <label htmlFor="bot">Bot</label>
        </form>
      </div>
      {!gameData && <button onClick={handleStartGame}>Start Game</button>}
      
      {gameData && (
        <div>
          <button onClick={handleStartGame}>Restart Game</button>
          <p>Game ID: {gameData.gameId}</p>
          <p>Status: {gameData.status}</p>
          <div className='board-container'>
            <Board 
              grid={gameData.board.grid} 
              onClickCallback={handleMakeMove} 
              shallLoad={shallLoad} 
              placeSign={playerStarts ? "X" : "O"}
            /> 
          </div>
        </div>
      )}
      <Error error={errorMessage} />
    </div>
  );
}

export default TicTacToe;
