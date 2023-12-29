import errorStyle from './Error.css';

function Error({ error }) {
    return (
        <div style={errorStyle}>
        {error}
        </div>
    );
    }

export default Error;