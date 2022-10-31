import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
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
import { v4 as uuidv4 } from 'uuid';
import { getAllCategory, addProduct } from '../services'
import { categories,colors } from '../constant'


function ProductAdd() {

  const [category, setCategory] = useState([])
  const { register, handleSubmit, formState: { errors } } = useForm();

  useEffect(() => {
    getAllCategory().then(res => {
      console.log(res)
      setCategory(res.data)
    })
  }, [])

  const onSubmit = data => {
    const productAdd = {
      //...data,
      proId: uuidv4(),
      color: "Vàng",
      cateId: "MBP",
      proName: "test",
      price: "123",
      description: "test",
      quantity: "1",
      warrantyMonth: "12",
      image: "123.png"
    }
    //console.log(JSON.stringify(productAdd));
    //const a = JSON.stringify(productAdd);
    addProduct(productAdd).then(res =>{
      console.log(res)
    }).catch(err => console.log(err))
  }

  return (
    <>
      {/* <Button>Back</Button> */}
      <Container className="p-5">
        <Row>
          <Col md="8">
            <Card>
              <Card.Header>
                <Card.Title as="h4">Add Product</Card.Title>
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
                          required
                          {...register("proName", {
                            required: "This input is required"
                          })}
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
                        as='select'
                          {...register("color")}
                        >
                          {colors.map((color,index) => <option key={index} value={color.value}>{color.value}</option>)}
                          
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
                            {category.map(cate => <option key={cate.cateID} value={cate.cateID}>{cate.cateName}</option> )}
                          
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
                          placeholder=""
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>
                  <Row>
                    <Col className="pr-1" md="6">
                      <Form.Group>
                        <label>Quantity</label>
                        <Form.Control
                          placeholder=""
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
                        <label className="">Product Image</label>
                        <Form.Control
                          type="file"
                          multiple
                          style={{"border":"none","padding":"0"}}
                          {...register("image")}
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>
                  <Button
                    className="btn-fill pull-right"
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

export default ProductAdd;
