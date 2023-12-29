import errorStyle from './errorStyle.css';

function Error({ error }) {
    return (
        <div style={errorStyle}>
        {error}
        </div>
    );
    }