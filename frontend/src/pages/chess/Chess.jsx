import React, { useEffect, useState } from 'react';
import { apiRoutes } from '../../routes/routeDefinitions.jsx';
import { getAgents} from '../../services/gameService';
import Board from '../../components/game/Board';
import Error from '../../components/game/Error';
import axios from 'axios';
import './Chess.css';
function Chess() {

  const [gameData, setGameData] = useState(null);
  const [shallLoad, setShallLoad] = useState(false);
  const [botType, setBotType] = useState("");
  const [playerStarts, setPlayerStarts] = useState(true);
  const [availableBots, setAvailableBots] = useState([]);
  const [gameStarted, setGameStarted] = useState(false);
  const [errorMessage, setErrorMessage] = useState(null);
  const gameType = "chess";
  const [startMove, setStartMove] = useState("");
  const [endMove, setEndMove] = useState("");

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

  useEffect(() => {
    console.log("Game data:", gameData);
  }, [gameData]);

  const handleStartGame = async () => {
    // Reset state
    setGameStarted(false);
    setErrorMessage(null);

    let requestData = {
      botType: botType,
      gameType: gameType
    };
    await axios.post(apiRoutes.startGame, requestData)
    .then((response) => {
      setGameData(response.data);
      setGameStarted(true);
    }).catch((error) => {
      setErrorMessage(error.message);
      console.error("Error starting game:", error);
    });
  }

  const makeMove = async (x, y) => {
    console.log("Making Move: " + x + y);
    setErrorMessage(null);
    let requestBoard = {
      gameId: gameData.gameId,
      x: x, //Send column number to backend
      y: y,
    } 

    if (gameData.status === "Game in progress") {
      try {
        console.log("Sending Move")
        console.log(requestBoard)
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

  /**
   * Handles a click on the board from the player. 
   * If the player has clicked on a piece, it will be highlighted and
   * the start move will be set. 
   * If the player clicks on a another places, 
   * the end move will be set and the move will be sent.
   * If the player clicks on the same piece again, the start move will be reset.
   * 
   * @param {String} x 
   * @param {String} y 
   */
  const handleClick = (x, y) => {
    const move = String(x) + String(y);
    console.log("Clicked: " + move);
    if (startMove === move) {
      setStartMove("");
      return;
    }

    if (startMove === "") {
      setStartMove(move);
      return;
    } else if (startMove !== "" && endMove === "") {
      setEndMove(move);
      console.log("Start Move: " + startMove + " End Move: " + endMove);
      makeMove(startMove, move);
    }

    setStartMove("");
    setEndMove("");
  }

  /**
   * A method for handling the bot move.
   */
  const botMove = async () => {
    console.log("Bot Move");
    setErrorMessage(null);
    setShallLoad(true);

    let requestBody = {
        gameId: gameData.gameId,
    }
    console.log("Game Status: " + gameData.gameId);
    if (gameData.status === "Game in progress") {
        console.log("Sending Bot Move")
        const response = await axios.post(apiRoutes.botMove, requestBody)
        //const response = gameService.botMove(requestBody)
        .then(result => {
            // Update state with response data
            setGameData(result.data);
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
          <button onClick={handleStartGame}>Restart Game</button>
          <p>Game ID: {gameData.gameId}</p>
          <p>Status: {gameData.status}</p>
          <div className='board-container'>
            <Board grid={gameData.board.grid} onClickCallback={handleClick} shallLoad={shallLoad} placeSign={(playerStarts) ? "X" : "O"}/> 
          </div>
        </div>
      )}
      <Error error={errorMessage} />
    </div>
  );
}

export default Chess;