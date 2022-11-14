import React, { useState,useContext } from 'react';
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
import Product from 'views/Product';

function Confirm(props) {
  const { className, isOpen, productName } = props;
  const {del, setDel} = useContext();
  const toggle = () => setDel(!del)

  return (
    <div>
      <Modal
        isOpen={del}
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