import React, { useContext, useEffect, useState } from "react";
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
  InputGroup,
  Modal
} from "react-bootstrap";
import { v4 as uuidv4 } from 'uuid';
import { getAllCategory, addProduct } from '../services'
import { categories, colors } from '../constant'
import { AdminContext } from "layouts/Admin";
import { useHistory } from "react-router";
import { PopUpContext } from "./Product";


function ProductAdd() {

  const [category, setCategory] = useState([])
  const { register, handleSubmit, getValues, formState: { errors } } = useForm();
  const token = localStorage.getItem('token')
  const history = useHistory()
  const {isShow, setIsShow} = useContext(PopUpContext)

  useEffect(() => {
    getAllCategory(token).then(res => {
      setCategory(res.data.data)
    })
  }, [])

  const onSubmit = data => {
    const img = getValues('image')[0]
    const productAdd = {
      ...data,
      proId: uuidv4(),
      image: img
    }

    addProduct(productAdd, token).then(res => {
      history.push('/admin/product')
    }).catch(err => console.log(err))
  }

  return (
    <>
   
      <Modal show={isShow}  onHide={() => setIsShow(false)}>
        <Col md="12" className="position-absolute">
          <Card>
            <Modal.Header as="h4"><h2>Add Product</h2></Modal.Header>
            <Card.Body>
              <Form onSubmit={handleSubmit(onSubmit)}>
                <Row>
                  <Col className="pr-1" md="6">
                    <Form.Group>
                      <label htmlFor="proName">Product Name</label>
                      <Form.Control
                        type="text"
                        id="proName"

                        {...register("proName", {
                          required: true
                        })}
                      ></Form.Control>
                    </Form.Group>
                    {errors.proName && (
                      <p className="text-danger">Product name is required</p>
                    )}
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
                        {...register("price", {
                          required: true, min: 100, pattern: {
                            value: /^[0-9]*$/
                          },
                        })}
                      ></Form.Control>
                    </Form.Group>
                    {errors.price?.type === "required" && (
                      <p className="text-danger">Please enter Price</p>
                    )}
                    {errors.price?.type === "min" && (
                      <p className="text-danger">Price at least 100 </p>
                    )}
                    {errors.price?.type === 'pattern' && (
                      <p className="text-danger">Price is number</p>
                    )}
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
                        {colors.map((color, index) => <option key={index} value={color.value}>{color.value}</option>)}
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
                        {category.map(cate => <option key={cate.cateID} value={cate.cateID}>{cate.cateName}</option>)}
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
                        {...register("description", {
                          required: true
                        })}
                        placeholder=""
                      ></Form.Control>
                    </Form.Group>
                    {errors.description && (
                      <p className="text-danger">Please enter Description</p>
                    )}
                  </Col>
                </Row>
                <Row>
                  <Col className="pr-1" md="6">
                    <Form.Group>
                      <label>Quantity</label>
                      <Form.Control
                        placeholder=""
                        type="text"
                        {...register("quantity", {
                          required: true, min: 5, pattern: {
                            value: /^[0-9]*$/
                          },
                        })}
                      ></Form.Control>
                    </Form.Group>
                    {errors.quantity?.type === "required" && (
                      <p className="text-danger">Please enter Quantity</p>
                    )}
                    {errors.quantity?.type === "min" && (
                      <p className="text-danger">Quantity at least 5 </p>
                    )}
                    {errors.quantity?.type === 'pattern' && (
                      <p className="text-danger">Quantity is number</p>
                    )}
                  </Col>
                  <Col className="px-1" md="6">
                    <Form.Group>
                      <label>Warranty (Month)</label>
                      <Form.Control
                        placeholder="Country"
                        type="text"
                        {...register("warrantyMonth", {
                          required: true, min: 6, pattern: {
                            value: /^[0-9]*$/
                          },
                        })}
                      ></Form.Control>
                    </Form.Group>
                    {errors.warrantyMonth?.type === "required" && (
                      <p className="text-danger">Please enter Warranty</p>
                    )}
                    {errors.warrantyMonth?.type === "min" && (
                      <p className="text-danger">Warranty at least 6 Month</p>
                    )}
                    {errors.warrantyMonth?.type === 'pattern' && (
                      <p className="text-danger">Warranty is number</p>
                    )}
                  </Col>
                </Row>
                <Row>
                  <Col md="12">
                    <Form.Group>
                      <label className="">Product Image</label>
                      <Form.Control
                        type="file"
                        multiple
                        style={{ "border": "none", "padding": "0" }}
                        {...register("image", { required: true })}
                      ></Form.Control>
                    </Form.Group>
                    {errors.image && (
                      <p className="text-danger">Please import Image</p>
                    )}
                  </Col>
                </Row>
                <Row className="d-flex justify-content-around">
                  
                <Button
                  className="btn-fill pull-right"
                  type="submit"
                  variant="info"
                >
                  Add Product
                </Button>
                <Button
                  className="btn-fill w-25"
                  type="submit"
                  variant="secondary"
                  onClick={() => setIsShow(false)}
                >
                  Hủy
                </Button>
                </Row>
                <div className="clearfix"></div>
              </Form>
            </Card.Body>
          </Card>
        </Col>
      </Modal>
    </>
  );
}

export default ProductAdd;
