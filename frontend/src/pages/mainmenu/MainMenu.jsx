import React, { useContext, useEffect } from 'react'; // <-- useContext is imported here
import { Link } from 'react-router-dom';
import './MainMenu.css';
import { routes } from '../../routes/routeDefinitions.jsx';




function MainMenu() {

  return (
    <div className="main-menu">
      <h1>BoardMaster</h1>
      <img src="/images/BoardMaster.png" alt="BoardMaster Logo" width="20%"/>
      <h2>Choose a game</h2>
      <div className='games'>
        <Link to={routes.chess}><button className='Implemented'>Start New Chess game</button></Link>
        <Link to={routes.tic_tac_toe}><button className='Implemented'>Start New Tic Tac Toe game</button></Link>
        <Link to={routes.connect_four}><button className='Implemented'>Start New Connect Four game</button></Link>
      </div>
</div>
  );
}

export default MainMenu;