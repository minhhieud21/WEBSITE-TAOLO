import React, { useState, useEffect } from 'react'
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

import User from './UserProfile';
import Confirm from 'components/Popup/Confirm';
import { getAllProduct, getAllUsers } from 'services';
import { useDispatch, useSelector } from 'react-redux';
import { productSelector, userSelector } from 'redux/selector';
import { addProduct, addUser,getAllUsersRedux } from 'redux/actions';

export default function UsersPage() {
    const dispatch = useDispatch();

    const [open, setOpen] = useState(false)
    const [del, setDel] = useState(false)
    const [userList, setUserList] = useState([])
    const users = useSelector(userSelector)

    useEffect(() => {
        getAllUsers().then((res) => {
            dispatch(addUser(
                res.data
            ))
            setUserList(res.data)
        })

    }, [])
    console.log(userList)

    return (
        <>
            {del ? <Confirm isOpen={del} /> : ''}
            <Container fluid>
                <Row>
                    <Col md="12">
                        <Card className="card-plain table-plain-bg">
                            <Card.Header>
                                <Card.Title as="h4">Manage user in TAOLO</Card.Title>
                                <Button color='primary' size='sm'>Add</Button>
                            </Card.Header>
                            <Card.Body className="table-full-width table-responsive px-0">
                                <Table className="table-hover">
                                    <thead>
                                        <tr>
                                            <th className="border-0">#</th>
                                            <th className="border-0">User Id</th>
                                            <th className="border-0">Name</th>
                                            <th className="border-0">Phone</th>
                                            <th className="border-0">Age</th>
                                            <th className="border-0">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {userList.map((user,index) => 
                                            <tr key={user.userID}>
                                                <td>{index+1}</td>
                                                <td>{user.userID}</td>
                                                <td>{user.name}</td>
                                                <td>{user.phone}</td>
                                                <td>{user.age}</td>
                                                <td>
                                                    <div className='d-flex' style={{cursor:"pointer"}}>
                                                        <i className="nc-icon nc-simple-remove text-danger pr-3" onClick={() => setDel(!del)}></i>
                                                        <i className="nc-icon nc-settings-gear-64" onClick={() => setOpen(!open)}></i>
                                                    </div>
                                                </td>
                                            </tr>
                                        ) }



                                    </tbody>
                                </Table>
                            </Card.Body>
                        </Card>
                    </Col>
                </Row>
                {
                    open ? <User /> : ''
                }
            </Container>
        </>
    )
}
