import React, { useEffect, useState, useMemo } from "react";
import { useForm } from "react-hook-form";
import { useHistory } from "react-router-dom"

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

import { getAllCategory, getProductById, updateProduct } from '../services'
import { useParams } from "react-router";
import { categories } from '../constant'

function ProductEdit() {

  const [product, setProduct] = useState({})
  const [cateName, setCateName] = useState("")
  const [productEdit, setProductEdit] = useState({})
  const { register, watch, handleSubmit, formState: { errors } } = useForm({
    defaultValues: useMemo(() => productEdit, [productEdit])
  });
  const { productId } = useParams();
  const watchAllFields = watch("proName", false);
  const history = useHistory();

  useEffect(() => {
    getProductById(productId).then(res => {
      setProductEdit(res.data)
      setProduct(res.data)
    }).catch(e => console.log(e))
  }, [productId])


  const onSubmit = data => {
    const check = JSON.stringify(data) === JSON.stringify(productEdit);
    const tmp = check ? {
      ...data,
      proId: productId,
      image: product.Image ? product.Image : ""
    } : {
      ...productEdit,
      proId: productId,
      image: product.Image ? product.Image : ""
    }
    console.log(tmp,check)
    updateProduct(tmp).then(data => {
      //history.push("/admin/product")
    }).catch(e => console.log(e))

  }

  return (
    <>
      {/* <Button>Back</Button> */}
      <Container className="p-5">
        <Row>
          <Col md="8">
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
                    <Col md="12">
                      <Form.Group>
                        <label className="mr-2">Product Image</label>
                        <input
                          type="file"
                        ></input>
                      </Form.Group>
                    </Col>
                  </Row>
                  <Button
                    className="btn-fill pull-right mt-2"
                    type="submit"
                    variant="info"
                  >
                    Add Product
                  </Button>
                  <div className="clearfix"></div>
                </Form>
              </Card.Body>
            </Card>
          </Col>
          <Col md="4">
            <Card className="card-user">
              <div className="card-image">
                <img
                  alt="..."
                  src={require("assets/img/photo-1431578500526-4d9613015464.jpeg")}
                ></img>
              </div>
              <Card.Body>
                <div className="author">
                  <a href="#pablo" onClick={(e) => e.preventDefault()}>
                    <img
                      alt="..."
                      className="avatar border-gray"
                      src={require("assets/img/faces/face-3.jpg")}
                    ></img>
                    <h5 className="title">Mike Andrew</h5>
                  </a>
                  <p className="description">michael24</p>
                </div>
                <p className="description text-center">
                  "Lamborghini Mercy <br></br>
                  Your chick she so thirsty <br></br>
                  I'm in that two seat Lambo"
                </p>
              </Card.Body>
              <hr></hr>
              <div className="button-container mr-auto ml-auto">
                <Button
                  className="btn-simple btn-icon"
                  href="#pablo"
                  onClick={(e) => e.preventDefault()}
                  variant="link"
                >
                  <i className="fab fa-facebook-square"></i>
                </Button>
                <Button
                  className="btn-simple btn-icon"
                  href="#pablo"
                  onClick={(e) => e.preventDefault()}
                  variant="link"
                >
                  <i className="fab fa-twitter"></i>
                </Button>
                <Button
                  className="btn-simple btn-icon"
                  href="#pablo"
                  onClick={(e) => e.preventDefault()}
                  variant="link"
                >
                  <i className="fab fa-google-plus-square"></i>
                </Button>
              </div>
            </Card>
          </Col>
        </Row>
      </Container>
    </>
  );
}

export default ProductEdit;
