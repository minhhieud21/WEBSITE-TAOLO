import React,{useState} from 'react'
import {
    Badge,
    Button,
    Card,
    Navbar,
    Nav,
    Table,
    Container,
    Row,
    Col,
} from "react-bootstrap";
import { Link } from 'react-router-dom';

import Confirm from 'components/Popup/Confirm';
export default function UsersPage() {
    const [open, setOpen] = useState(false)
    const [del, setDel] = useState(false)
    return (
        <>
            { del ? <Confirm isOpen ={del} /> : ''}
            <Container fluid>
                <Row>
                    <Col md="12">
                        <Card className="strpied-tabled-with-hover">
                            <Card.Header>
                                <Card.Title as="h4">Manage user in TAOLO</Card.Title>
                                <Button color='primary' size='sm'>Add</Button>
                            </Card.Header>
                            <Card.Body className="table-full-width table-responsive px-0">
                                <Table className="table-hover table-striped">
                                    <thead>
                                        <tr>
                                            <th className="border-0">ID</th>
                                            <th className="border-0">Name</th>
                                            <th className="border-0">Salary</th>
                                            <th className="border-0">Country</th>
                                            <th className="border-0">City</th>
                                            <th className="border-0">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>Dakota Rice</td>
                                            <td>$36,738</td>
                                            <td>Niger</td>
                                            <td>Oud-Turnhout</td>
                                            <td>
                                                <div className='d-flex '>
                                                    <i className="nc-icon nc-simple-remove text-danger pr-3"onClick={() => setDel(!del)}></i>
                                                    <Link to={`/admin/user/${1}`}><i className="nc-icon nc-settings-gear-64" ></i></Link>
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </Table>
                            </Card.Body>
                        </Card>
                    </Col>
                </Row>
            </Container>
        </>
    )
}
