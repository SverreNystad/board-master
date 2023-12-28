import React from 'react';

function Board({ grid, onClickCallback }) {
  const handleClick = (rowIndex, columnIndex) => () => {
    onClickCallback(rowIndex, columnIndex);
  };

  return (
    <div style={{ display: 'flex', flexDirection: 'column' }}>
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
