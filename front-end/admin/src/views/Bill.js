import React,{useEffect, useState} from "react";

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
import { getAllBill } from "services";


function Bill() {

  const [bill, setBill] = useState([])

  const headers = ['Index','Account ID','Bill ID','Address','Pay by','Total Quantity','Total Cost','Action']

  useEffect(() => {
    getAllBill().then(res => setBill(res.data)).catch(e => console.log(e))
  

  }, [])
  
  return (
    <>
      <Container fluid>
        <Row>
          <Col md="12">
            <Card className="strpied-tabled-with-hover">
              <Card.Header>
                <Card.Title as="h4">Manage Bill in TAOLO</Card.Title>
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
                    {bill && bill.map((bill,index) => <tr>
                      <td>{index + 1}</td>
                      <td>{bill.accID}</td>
                      <td>{bill.billID}</td>
                      <td>{bill.address}</td>
                      <td>{bill.methodPay}</td>
                      <td>{bill.toTalQuantity}</td>
                      <td>{bill.totalCost}</td>
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

export default Bill;
