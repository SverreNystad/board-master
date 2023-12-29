import React from 'react';
import './Board.css';

function Board({ grid, onClickCallback, shallLoad }) {
  const handleClick = (rowIndex, columnIndex) => () => {
    // When the shallLoad prop is true, do not allow the user to click
    if (shallLoad) return;
    onClickCallback(rowIndex, columnIndex);
  };

  return (
    <div className='boardContainer'>
      {shallLoad && (
        <div className='loadingOverlay'>
          <div className='loadingSymbol'>Waiting on bots action</div>
        </div>
      )}
      <div className='columnGrid'>
        {grid.map((row, rowIndex) => (
          <div key={rowIndex} style={{ display: 'flex' }}>
            {row.map((cell, columnIndex) => (
              <div key={columnIndex} style={cellStyle} onClick={handleClick(rowIndex, columnIndex)}>
                {cell}
              </div>
            ))}
          </div>
        ))}
      </div>
    </div>
  );
}

const boardContainerStyle = {
  position: 'relative'
};

const loadingOverlayStyle = {
  position: 'absolute',
  top: 0,
  left: 0,
  right: 0,
  bottom: 0,
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  backgroundColor: 'rgba(255, 255, 255, 0.7)', // Semi-transparent overlay
  zIndex: 1
};

const loadingSymbolStyle = {
  padding: '10px',
  backgroundColor: '#f0f0f0',
  border: '1px solid #ccc',
  borderRadius: '5px'
};

const cellStyle = {
  width: '50px',
  height: '50px',
  border: '1px solid black',
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  cursor: 'pointer'
};

export default Board;
