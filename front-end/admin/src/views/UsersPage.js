import React, { useState, useEffect, useContext, useCallback } from 'react'
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
    InputGroup,
    Form
} from "react-bootstrap";

import { Link } from 'react-router-dom';
import { Input } from "reactstrap";

import Confirm from 'components/Popup/Confirm';
import { getAllProduct, getAllUsers } from 'services';
import { AdminContext } from 'layouts/Admin';
import Loading from 'components/Loading';
import { changeStatusUser } from 'services';


export default function UsersPage() {

    const [userList, setUserList] = useState([])
    const [status, setStatus] = useState(1)

    const { isDelete, setIsDelete } = useContext(AdminContext)
    const [isLoading, setIsLoading] = useState(false)
    const [toggle, setToggle] = useState(false)
    const token = localStorage.getItem("token")

    useEffect(() => {
        setIsLoading(true)
        getAllUsers(token).then((res) => {
            setUserList(res.data.data.content)
        }).catch(e => console.log(e)).finally(setIsLoading(false))

    }, [])
    console.log(status)

    const changeStatus = useCallback(async(accID,status) => {
        await changeStatusUser(accID,status, token)
        window.location.reload()
    }, [])


    return (
        <>
            {isLoading && <Loading />}

            <Container fluid>
                <Row>
                    <Col md="12">
                        <Card className="strpied-tabled-with-hover">
                            <Card.Header>
                                <Card.Title as="h4">Manage user in TAOLO</Card.Title>
                                <div className="header">
                                    <div className="">
                                        <Link to="/admin/product/add"><Button color='primary' size='sm'>Add</Button></Link>
                                    </div>
                                    <div className="header__right">
                                        <Input className="header__right__search" />
                                    </div>
                                </div>

                            </Card.Header>
                            <Card.Body className="table-full-width table-responsive px-0">
                                <Table className="table-hover table-striped">
                                    <thead>
                                        <tr>
                                            <th className="border-0">#</th>
                                            <th className="border-0">User Id</th>
                                            <th className="border-0">User Name</th>
                                            <th className="border-0">Role</th>
                                            <th className="border-0">Google Login</th>
                                            <th className="border-0">Status</th>
                                            <th className="border-0">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {userList && userList.map((user, index) =>
                                            <tr key={user.accID}>
                                                <td>{index + 1}</td>
                                                <td>{user.accID}</td>
                                                <td>{user.username}</td>
                                                <td>{user.accID.includes('A') ? 'Admin' : ' User'}</td>
                                                <td>{user.google_login ? 'True' : 'False'}</td>
                                                {user.status === 1 ? <td className='text-primary'>{'Active'}</td> : <td className='text-danger'>{'Deactive'}</td>}
                                                <td>
                                                    {/* <div className='d-flex' style={{ cursor: "pointer" }}>
                                                        {user.status === 1 ? <i className='text-danger nc-icon nc-stre-down pr-3' onClick={() => changeAccountStatus(user.accID, 0)}></i> : <i className='nc-icon nc-stre-up text-primary pr-3' onClick={() => changeAccountStatus(user.accID, 1)}></i>}
                                                    </div> */}
                                                    <select onChange={(e) => {
                                                        setStatus(Number(e.target.value))
                                                    }} >
                                                        <option value="1">Active</option>
                                                        <option value="0">Deactive</option>
                                                    </select>
                                                </td>
                                                    <Button color='primary' size='sm' onClick={() => changeStatus(user.accID,status)}>Save</Button>
                                            </tr>
                                        )}
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
