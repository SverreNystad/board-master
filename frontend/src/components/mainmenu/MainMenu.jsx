import React, { useContext, useEffect } from 'react'; // <-- useContext is imported here
import { Link } from 'react-router-dom';
import './MainMenu.css';
import { routes } from '../../routes/routeDefinitions.jsx';


function MainMenu() {

  return (
    <div className="main-menu">
      <h1>BoardMaster</h1>
      <img src="/images/BoardMaster.png" alt="BoardMaster Logo" width={300} />
      <Link to={routes.chess}><button>Start New Chess game</button></Link>
      <Link to={routes.tic_tac_toe}><button>Start New Tic Tac Toe game</button></Link>
</div>
  );
}

export default MainMenu;