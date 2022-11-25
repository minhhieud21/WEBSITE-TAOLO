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
import { AdminContext } from 'layouts/Admin';

function Confirm(props) {
  const { className, productName } = props;

  const { isDelete, setIsDelete, confirmDelete, setConfirmDelete} = useContext(AdminContext)
  
  const toggle = () => {
    setIsDelete(!isDelete)
    setConfirmDelete(false) 
  }

  const confirm = () =>{
    setIsDelete(!isDelete)
    setConfirmDelete(true) 
  } 

  return (
    <div>
      <Modal
        isOpen={isDelete}
        className={className}
        backdrop={true}
      >
        <ModalHeader toggle={toggle}>Xác nhận xóa ?</ModalHeader>
        <ModalBody>
          Bạn có chắc muốn xóa sản phẩm {productName} ?
        </ModalBody>
        <ModalFooter>
          <Button color="danger" onClick={confirm}>
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