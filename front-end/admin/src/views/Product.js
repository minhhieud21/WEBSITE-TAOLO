import React, { useState, useEffect, createContext } from "react";

import { getAllProduct, changeStatus } from "../services"
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
import { Link } from "react-router-dom";

const PopUpContext = createContext()

function Product() {
  const [product, setProduct] = useState([])
  const [del, setDel] = useState(false)
  
  const header = ["#", "Name", "Price", "Quantity", "Warranty", "Status", "Action"]
  const warrantyMonth = "Month"
  const status = {
    active: 1,
    inactice: 0
  }

  useEffect(() => {
    getAllProduct().then(res => {
      setProduct(res.data)
    }).catch(e => console.log(e))

  }, [])

  const changeProductStatus = (proId) =>{
    const test = {
      proId : proId,
      status: 1
    }
    changeStatus(test).then(res => console.log(res)).catch(e => console.log(e))
  }

  return (
    <PopUpContext.Provider value={{
      del, setDel
    }}>
         {del ? <Confirm productName={currentProduct} />: ""} 
      <Container fluid>
        <Row>
          <Col md="12">
            <Card className="strpied-tabled-with-hover">
              <Card.Header>
                <Card.Title as="h4">Manage product in TAOLO</Card.Title>
                <Link to="/admin/product/add"><Button color='primary' size='sm'>Add</Button></Link>

              </Card.Header>
              <Card.Body className="table-full-width table-responsive px-0">
                <Table className="table-hover table-striped">
                  <thead>
                    <tr>
                      {header.map((data, index) => <th key={index} className="border-0">{data}</th>)}
                    </tr>
                  </thead>
                  <tbody>
                    {product ? product.map((product, index) =>
                      <tr key={product.proId}>
                        <td>{index}</td>
                        <td>{product.proName}</td>
                        <td>{product.price}</td>
                        <td>{product.quantity}</td>
                        <td>{product.warrantyMonth} {warrantyMonth}</td>
                        <td>{product.status}</td>
                        <td>
                          <div className='d-flex' style={{ cursor: "pointer" }}>
                            <i className="nc-icon nc-simple-remove text-danger pr-3" onClick={() => setDel(!del)}></i>

                            <Link className="reset-anchor" to={`/admin/product/${product.proId}`}>
                              <i className="nc-icon nc-settings-gear-64"></i>
                            </Link>
                          </div>
                        </td>
                      </tr>
                    ) : ""}
                  </tbody>
                </Table>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
    </PopUpContext.Provider>
  );
}

export default Product;
