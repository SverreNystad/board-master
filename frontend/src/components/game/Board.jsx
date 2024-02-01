import React from 'react';
import './Board.css';

function Board({ grid, onClickCallback, shallLoad, placeSign, reverse }) {

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
        {
          !!reverse && grid.map((row, rowIndex) => (
            <div key={rowIndex} style={{ display: 'flex' }}>
              {row.map((cell, columnIndex) => (
                <div className='cell' key={columnIndex} style={cellStyle} onClick={handleClick(rowIndex, columnIndex)}>
                  <div>
                    {!!!cell && <b className='toBePlaced'>{placeSign}</b>}
                  <b className='toBeKept'>{cell}</b>
                  </div>
                </div>
              ))}
            </div>
          ))
        }
        {
        !!!reverse && grid[0].map((row, colIndex) => (
          <div key={colIndex} style={{ display: 'flex' }}>
            {grid.map((cell, rowIndex) => (
              <div className='cell' key={rowIndex} style={cellStyle} onClick={handleClick(rowIndex, colIndex)}>
                <div>
                  {!!!cell[colIndex] && <b className='toBePlaced'>{placeSign}</b>}
                  <b className='toBeKept'>{row[colIndex]}</b>
                </div>
              </div>
            ))}
          </div>
        ))}
      </div>
    </div>
  );
}

// Magic code
const cellStyle = {
  width: '50px',
  height: '50px',
  border: '2px solid #C7D9E3',
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  cursor: 'pointer'
};



export default Board;
