// routes/Routes.js

import React from 'react';
import { Route, Routes } from 'react-router-dom';
import { routes } from './routeDefinitions';

// Import your page components
import Chess from '../components/chess/Chess';
import TicTacToe from '../components/tictactoe/TicTacToe';
import MainMenu from '../components/mainmenu/MainMenu';
import SomeNotFoundComponent from '../components/somenotfoundcomponent/SomeNotFoundComponent';


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