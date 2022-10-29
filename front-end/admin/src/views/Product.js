import React, { useState, useEffect } from "react";

import { getAllProduct } from "../services"
import Confirm from 'components/Popup/Confirm';
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

function Product() {
  const [product, setProduct] = useState([])
  const [del, setDel] = useState(false)
  const [currentProduct, setCurrentProduct] = useState("")

  const header = ["#", "Name", "Price", "Quantity", "Warranty", "Status","Action"]
  const warrantyMonth = "Month"
  const status = {
    active: 1,
    inactice: 0
  }

  useEffect(() => {
    getAllProduct().then(res => {
      setProduct(res.data.Product)
    }).catch(e => console.log(e))

  }, [])

  console.log('prod',del)

  return (
    <>
      {del ? <Confirm isOpen={del} productName={currentProduct} /> : ''}
      <Container fluid>
        <Row>
          <Col md="12">
            <Card className="strpied-tabled-with-hover">
              <Card.Header>
                <Card.Title as="h4">Manage product in TAOLO</Card.Title>
                <Button color='primary' size='sm'>Add</Button>

              </Card.Header>
              <Card.Body className="table-full-width table-responsive px-0">
                <Table className="table-hover table-striped">
                  <thead>
                    <tr>
                      {header.map(data => <th className="border-0">{data}</th>)}
                    </tr>
                  </thead>
                  <tbody>
                    {product.map((product, index) =>
                      <tr key={product.proId}>
                        <td>{index}</td>
                        <td>{product.proName}</td>
                        <td>{product.price}</td>
                        <td>{product.quantity}</td>
                        <td>{product.warrantyMonth} {warrantyMonth}</td>
                        <td>{product.status}</td>
                        <td>
                          <div className='d-flex' style={{ cursor: "pointer" }}>
                            <i className="nc-icon nc-simple-remove text-danger pr-3" onClick={() => { setDel(!del); setCurrentProduct(product.proName) }}></i>
                            <i className="nc-icon nc-settings-gear-64" onClick={() => setOpen(!open)}></i>
                          </div>
                        </td>
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
  );
}

export default Product;
