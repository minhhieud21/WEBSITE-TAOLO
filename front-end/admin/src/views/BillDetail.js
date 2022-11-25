import React from 'react'
import { Modal } from 'react-bootstrap'

export default function BillDetail() {
    return (
        <Modal
            className="modal-mini modal-primary"
            show={true}
        >
            <Modal.Header className="justify-content-center">
                <h3>{title}</h3>
            </Modal.Header>
            <Modal.Body className="text-center">
                <p>Always have an access to your profile</p>
            </Modal.Body>
            <div className="modal-footer">
                <Button
                    className="btn-simple"
                    type="button"
                    variant="link"

                >
                    Back
                </Button>
                <Button
                    className="btn-simple"
                    type="button"
                    variant="link"

                >
                    Close
                </Button>
            </div>
        </Modal>
    )
}
