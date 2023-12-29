const routes = {
  mainMenu: "/main-menu",
  chess: "/chess",
  tic_tac_toe: "/tic-tac-toe",
};

const baseUrl = 'http://localhost:8080/api';


const apiRoutes = {
  startGame: `${baseUrl}/start-game`,
  makeMove: `${baseUrl}/move`,
  botMove: `${baseUrl}/bot-move`,
  mock: `${baseUrl}/mock`,
};

export { routes, apiRoutes };
  