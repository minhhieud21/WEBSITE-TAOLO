import { AdminContext } from "layouts/Admin";
import React,{useContext, useEffect, useState} from "react";

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
import { getAllReceipt } from "services/Receipt";


function Receipt() {

  const [receipt, setReceipt] = useState([])

  const headers = ['Index','Receipt ID','Date','Quantity','Cost','Status','Action']

  const {token} = useContext(AdminContext)

  useEffect(() => {
    getAllReceipt(token).then(res => setReceipt(res.data.data)).catch(e => console.log(e))
  

  }, [])
  
  return (
    <>
      <Container fluid>
        <Row>
          <Col md="12">
            <Card className="strpied-tabled-with-hover">
              <Card.Header>
                <Card.Title as="h4">Manage receipt in TAOLO</Card.Title>
                <Button color='primary' size='sm'>Add</Button>

              </Card.Header>
              <Card.Body className="table-full-width table-responsive px-0">
                <Table className="table-hover table-striped">
                  <thead>
                    <tr>
                      {headers.map((header,index) => <th key={index} className="border-0">{header}</th>)}
                    </tr>
                  </thead>
                  <tbody>
                    {receipt && receipt.map((receipt,index) => <tr key={index}>
                      <td>{index + 1}</td>
                      <td>{receipt.recID}</td>
                      <td>{receipt.recDate}</td>
                      <td>{receipt.recQuantity}</td>
                      <td>{receipt.recCost}</td>
                      <td>{receipt.status}</td>
                      <td className="pl-3">
                        <i className="nc-icon nc-settings-gear-64"></i>
                      </td>
                    </tr>)}
                  </tbody>
                </Table>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
    </>
  );
}

export default Receipt;
