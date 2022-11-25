import React from 'react'
import {
    Card,
    Form,
    Row,
    Col,
    Modal,
    Button,
} from "react-bootstrap";
export default function CategoryAdd({ children }) {
    return (
        <div>
            <Modal
                className="modal-mini modal-primary"
                show={false}
            >
                <Modal.Header className="justify-content-center">
                    <h3>{children}</h3>
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
        </div>
    )
}
