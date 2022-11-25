import React, { useEffect, useState, useMemo, useContext } from "react";
import { useForm } from "react-hook-form";
import { Link, useHistory } from "react-router-dom"

import { ErrorMessage } from "@hookform/error-message";
// react-bootstrap components
import {
  Badge,
  Button,
  Card,
  Form,
  Navbar,
  Nav,
  Container,
  Row,
  Col,
  InputGroup
} from "react-bootstrap";

import { changeStatus, getAllCategory, getProductById, updateProduct } from '../services'
import { useParams } from "react-router";
import { categories } from '../constant'
import { AdminContext } from "layouts/Admin";
import { Label } from "reactstrap";

function ProductEdit() {

  const [product, setProduct] = useState({})
  const { register, handleSubmit, formState: { errors }, reset,getValues } = useForm();
  const { productId } = useParams();
  const [active, setActive] = useState(false)
  const history = useHistory();

  const token = localStorage.getItem('token')

  useEffect(() => {
    getProductById(productId).then(res => {
      setProduct(res.data)
      reset({ ...res.data }) // set default value for useForm

    }).catch(e => console.log(e))
  }, [productId])

  const changeProductStatus = async (proId) => {
    setIsDelete(true)
    const params = {
      proId: proId,
      status: 1
    }
    await changeStatus(params, token)

  }

  const onSubmit = async data => {
    const tmp = {
      ...data,
      proId: productId,
      image: product.Image ? product.Image : ""
    }
    const params = {
      proId: productId,
      status: getValues("status")
    }

    await changeStatus(params, token)
    updateProduct(tmp, token).then(data => {
      history.push("/admin/product")
    }).catch(e => console.log(e))

  }

  return (
    <>
      <Container className="p-5">
        <Link to="/admin/product">Back</Link>
        <Row>
          <Col md="12">
            <Card>
              <Card.Header>
                <Card.Title as="h4">Edit Product</Card.Title>
              </Card.Header>
              <Card.Body>
                <Form onSubmit={handleSubmit(onSubmit)}>
                  <Row>
                    <Col className="pr-1" md="6">
                      <Form.Group>
                        <label htmlFor="proName">Product Name</label>
                        <Form.Control
                          type="text"
                          id="proName"
                          defaultValue={product.proName}
                          required
                          {...register("proName")}
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                    <Col className="pl-1" md="6">
                      <Form.Group>
                        <label htmlFor="price">
                          Price
                        </label>
                        <Form.Control
                          placeholder="Price"
                          defaultValue={product.price}
                          type="text"
                          id="price"
                          required
                          {...register("price")}
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>
                  <Row>
                    <Col className="pr-1" md="6">
                      <Form.Group>
                        <label>Color</label>
                        <Form.Control
                          placeholder="Color"
                          defaultValue={product.color}
                          type="text"
                          required
                          {...register("color")}
                        >
                        </Form.Control>
                      </Form.Group>
                    </Col>
                    <Col className="pl-1" md="6">
                      <Form.Group>
                        <label>Category</label>
                        <Form.Control
                          as="select"
                          {...register("cateId")}
                        >
                          {categories.map(cate => {
                            if (product.cateId === cate.id) {
                              return <option key={cate.id} value={cate.id} selected>{cate.value}</option>;
                            } else {
                              return <option key={cate.id} value={cate.id} >{cate.value}</option>;
                            }
                          }
                          )}
                        </Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>
                  <Row>
                    <Col md="12">
                      <Form.Group>
                        <label>Description</label>
                        <Form.Control
                          as="textarea"
                          cols="80"
                          rows="4"
                          {...register("description")}
                          defaultValue={product.description}
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>
                  <Row>
                    <Col className="pr-1" md="6">
                      <Form.Group>
                        <label>Quantity</label>
                        <Form.Control
                          defaultValue={product.quantity}
                          type="text"
                          required
                          {...register("quantity")}
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                    <Col className="px-1" md="6">
                      <Form.Group>
                        <label>Warranty (Month)</label>
                        <Form.Control
                          placeholder="Country"
                          defaultValue={product.warrantyMonth}
                          type="text"
                          required
                          {...register("warrantyMonth")}
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>
                  <Row>
                    <Col md="6">
                      <Form.Group>
                        <label className="mr-2">Product Image</label>
                        <input
                          type="file"
                        ></input>
                      </Form.Group>
                    </Col>
                    <Col className="pl-1" md="6">
                      <Form.Group>
                        <label>Category</label>
                        <Form.Control
                          as="select"
                          {...register("status")}
                        >
                          <option value="1">Active</option>
                          <option value="0">De-active</option>
                        </Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>

                  <Button
                    className="btn-fill pull-right mt-2"
                    type="submit"
                    variant="info"
                  >
                    Update Product
                  </Button>
                  <div className="clearfix"></div>
                </Form>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
    </>
  );
}

export default ProductEdit;
