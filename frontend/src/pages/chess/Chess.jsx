import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { routes } from '../../routes/routeDefinitions.jsx';
import { getAgents } from '../../services/gameService';

function Chess() {

  const [availableBots, setAvailableBots] = useState([]);


  useEffect(() => {
    let agents = getAgents();
    setAvailableBots(agents);
  }, []);

  useEffect(() => {
    console.log(availableBots);
  }, [availableBots]);

  return (
    <div className="Chess">
        <h1>BoardMaster Chess</h1>
        
      
    </div>
  );
}

export default Chess;