const routes = {
  mainMenu: "/main-menu",
  chess: "/chess",
  tic_tac_toe: "/tic-tac-toe",
  connect_four: "/connect-four",
};

const baseUrl = 'http://localhost:8080/api';


const apiRoutes = {
  startGame: `${baseUrl}/start`,
  makeMove: `${baseUrl}/move`,
  botMove: `${baseUrl}/bot-move`,
  getAgents: `${baseUrl}/agents`,
};

export { routes, apiRoutes };
  