import Spinner from 'react-bootstrap/Spinner';

function Loading() {
  return (
    <Spinner style={{
        position: 'absolute',
        top: '50%',
        left: '50%'
    }} animation="border" role="status">
    </Spinner>
  );
}

export default Loading;