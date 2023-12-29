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
              <div className='cell' key={columnIndex} style={cellStyle} onClick={handleClick(rowIndex, columnIndex)}>
                <div>
                  <b>X</b>
                {cell}
                </div>
              </div>
            ))}
          </div>
        ))}
      </div>
    </div>
  );
}

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
