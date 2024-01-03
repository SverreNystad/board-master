// SomeNotFoundComponent.js
import React from 'react';
import { Link } from 'react-router-dom';

const SomeNotFoundComponent = () => {
  const containerStyle = {
    padding: '20px',
    textAlign: 'center',
    backgroundColor: '#282c34', // dark background
    color: 'white', // light text
    minHeight: '100vh', // full screen height
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    fontSize: 'calc(10px + 2vmin)',
    lineHeight: '1.6',
  };

  return (
    <div style={containerStyle}>
      <h1>404 - Not Found</h1>
      <p>There is no escape from the BoardMaster</p>
      <p>
        <Link to="/">Return to the safety of the Home page</Link>
      </p>
    </div>
  );
};

export default SomeNotFoundComponent;