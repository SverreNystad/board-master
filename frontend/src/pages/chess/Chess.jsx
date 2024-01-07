import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { routes } from '../../routes/routeDefinitions.jsx';
import { getAgents, startGame, makeMove, botMove } from '../../services/gameService';
import Board from '../../components/game/Board';
import Error from '../../components/game/Error';
function Chess() {

  const [gameData, setGameData] = useState(null);
  const [shallLoad, setShallLoad] = useState(false);
  const [botType, setBotType] = useState("");
  const [playerStarts, setPlayerStarts] = useState(true);
  const [availableBots, setAvailableBots] = useState([]);
  const [gameStarted, setGameStarted] = useState(false);
  const [errorMessage, setErrorMessage] = useState(null);
  const gameType = "chess";

  useEffect(() => {
    // Making the callback function async
    const fetchAgents = async () => {
      try {
        const agents = await getAgents();
        setAvailableBots(agents);
      } catch (error) {
        console.error("Error fetching agents:", error);
        // Handle error, possibly setting it to state for display
      }
    };
  
    fetchAgents();
  }, []);
  

  useEffect(() => {
    setBotType(availableBots[0]);
  } , [availableBots]);
  
  // Make bot move if game has started and player does not start
  useEffect(() => {
    if (gameStarted && gameData && !playerStarts) {
      botMove();
    }
  }, [gameStarted]);

  // Handle game start
  const handleStartGame = () => {
    // Reset state
    setGameStarted(false);
    setErrorMessage(null);

    console.log("Starting Game");
    startGame(gameType, botType).then((response) => {
      setGameData(response.data);
      console.log("Game started:", response.data);
      setGameStarted(true);
    }).catch((error) => {
      setErrorMessage(error.message);
      console.error("Error starting game:", error);
    });
  }

  return (
    <div className="Chess">
        <h1>BoardMaster Chess</h1>
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
        {!!!gameData && <button onClick={handleStartGame}>Start Game</button>}
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
      <Error error={errorMessage} />
    </div>
  );
}

export default Chess;