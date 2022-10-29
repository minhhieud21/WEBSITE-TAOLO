import React, { useState } from 'react';
import {
  Button,
  Modal,
  ModalHeader,
  ModalBody,
  ModalFooter,
  Input,
  Label,
  Form,
  FormGroup,
} from 'reactstrap';
import PropTypes from 'prop-types';

function Confirm(props) {
  const { className, isOpen, productName } = props;
  const [modal,setModal] = useState(isOpen);
  const toggle = () => setModal(!modal);
  console.log('c',isOpen)

  return (
    <div>
      <Modal
        isOpen={modal}
        className={className}
        backdrop={true}
      >
        <ModalHeader toggle={toggle}>Xác nhận xóa ?</ModalHeader>
        <ModalBody>
          Bạn có chắc muốn xóa sản phẩm {productName} ?
        </ModalBody>
        <ModalFooter>
          <Button color="danger" onClick={toggle}>
            Xóa
          </Button>{' '}
          <Button color="secondary" onClick={toggle}>
            Cancel
          </Button>
        </ModalFooter>
      </Modal>
    </div>
  );
}

Confirm.propTypes = {
  className: PropTypes.string,
};

export default Confirm;