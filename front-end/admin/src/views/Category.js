import { AdminContext } from "layouts/Admin";
import React, { useContext, useEffect, useState } from "react";

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
import { getAllCategory } from "services";
import CategoryAdd from "./CategoryAdd";


function Category() {

  const [cate, setCate] = useState([])
  const { token } = useContext(AdminContext)

  const headers = ['Index', 'Category ID', 'Category Name','Status']


  useEffect(() => {
    getAllCategory(token).then(res => setCate(res.data.data)).catch(e => console.log(e))


  }, [])

  console.log(cate)

  return (
    <>
    <CategoryAdd>ABC</CategoryAdd>
      <Container fluid>
        <Row>
          <Col md="12">
            <Card className="strpied-tabled-with-hover">
              <Card.Header>
                <Card.Title as="h4">Manage cate in TAOLO</Card.Title>
                <Button color='primary' size='sm'>Add</Button>

              </Card.Header>
              <Card.Body className="table-full-width table-responsive px-0">
                <Table className="table-hover table-striped">
                  <thead>
                    <tr>
                      {headers.map((header, index) => <th key={index} className="border-0">{header}</th>)}
                    </tr>
                  </thead>
                  <tbody>
                    {cate && cate.map((cate, index) => <tr key={index}>
                      <td>{index + 1}</td>
                      <td>{cate.cateID}</td>
                      <td>{cate.cateName}</td>
                      <td>{cate.status}</td>
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

export default Category;
