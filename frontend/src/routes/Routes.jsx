// routes/Routes.js

import React from 'react';
import { Route, Routes } from 'react-router-dom';
import { routes } from './routeDefinitions';

// Import your page components
import Chess from '../pages/chess/Chess';
import TicTacToe from '../pages/tictactoe/TicTacToe';
import MainMenu from '../pages/mainmenu/MainMenu';
import SomeNotFoundComponent from '../pages/somenotfoundcomponent/SomeNotFoundComponent';


const AppRoutes  = () => {
  return (
    <Routes >
      <Route path="/" element={<MainMenu/>} />
      <Route path={routes.mainMenu} element={<MainMenu/>} />
      <Route path={routes.chess} element={<Chess/>} />
      <Route path={routes.tic_tac_toe} element={<TicTacToe/>} />
      <Route path="*" element={<SomeNotFoundComponent />} />
    </Routes >
  );
};

export default AppRoutes ;