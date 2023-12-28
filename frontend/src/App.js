import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import AppRoutes from './routes/Routes';

function App() {
  return (
    <Router>
      <div>
        <AppRoutes  />
      </div>
    </Router>
  );
}

export default App;