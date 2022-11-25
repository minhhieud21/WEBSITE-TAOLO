import React, { useState, useEffect, createContext, useContext } from "react";
import { useForm } from "react-hook-form";

import { getAllProduct, changeStatus, getAllUsers, searchProductByName, formatVnd } from "../services"
import Confirm from 'components/Popup/Confirm';
import {
  Button,
  Card,
  Table,
  Container,
  Row,
  Col,
  Form,
  Modal,
} from "react-bootstrap";
import { Link, useLocation } from "react-router-dom";
import { AdminContext } from "layouts/Admin";
import Loading from "components/Loading";
import { Input } from "reactstrap";
import ProductAdd from "./ProductAdd";

export const PopUpContext = createContext()

function Product() {
  const [product, setProduct] = useState([])
  const { token, isDelete, setIsDelete, confirmDelete } = useContext(AdminContext)
  const [isLoading, setIsLoading] = useState(false)
  const { register, watch, handleSubmit, formState: { errors } } = useForm({});
  const [isShow, setIsShow] = useState(false)

  const [totalPages, setTotalPages] = useState(0)
  const search = useLocation().search
  let page = search ? Number(new URLSearchParams(search).get('page')) - 1 : 0;
  const header = ["Name", "Price", "Quantity", "Warranty", "Status", "Action"]
  const warrantyMonth = "Month"

  useEffect(() => {
    setIsLoading(true)

    if (page === -1) page = 0
    getAllProduct(token, page).then(res => {
      setProduct(res.data.data.content)
      setTotalPages(res.data.data.totalPages)
    }).catch(e => console.log(e)).finally(() => {
      setIsLoading(false)
    })
  }, [page])

  const changeProductStatus = async (proId) => {
    setIsDelete(true)
    console.log(confirmDelete)
    if (confirmDelete) {
    }
    const params = {
      proId: proId,
      status: 1
    }
    await changeStatus(params, token)

  }


  const onSubmit = data => {
    searchProductByName(data.text, token).then((res) => {
      setProduct(res.data.data)
    })
  }

  return (
    <PopUpContext.Provider value={{
      isShow,
      setIsShow
    }}>
      {isLoading && <Loading />}
      {isShow && <ProductAdd />}
      <Container fluid>
        <Row>
          <Col md="12">
            <Card className="strpied-tabled-with-hover">
              <Card.Header>
                <Card.Title as="h4">Manage product in TAOLO</Card.Title>
                <div className="header">
                  <div className="">
                    <Button color='primary' size='sm' onClick={() => setIsShow(true)} >Add</Button>
                  </div>
                  <div className="header__right">
                    <Form onSubmit={handleSubmit(onSubmit)} >
                      <Form.Control {...register('text')} className="header__right__search" ></Form.Control>
                    </Form>
                  </div>
                </div>

              </Card.Header>
              <Card.Body className="table-full-width table-responsive px-0">
                <Table className="table-hover table-striped">
                  <thead>
                    <tr>
                      {header.map((data, index) => <th key={index} className="border-0">{data}</th>)}
                    </tr>
                  </thead>
                  <tbody>
                    {product && product.map((product) =>
                      <tr key={product.proId}>
                        <td>{product.proName}</td>
                        <td>{formatVnd(product.price)}</td>
                        <td>{product.quantity}</td>
                        <td>{product.warrantyMonth} {warrantyMonth}</td>
                        {product.status === "1" ? <td className='text-primary'>{'Active'}</td> : <td className='text-danger'>{'Deactive'}</td>}
                        <td>
                          <div className='d-flex' style={{ cursor: "pointer" }}>
                            <Link className="reset-anchor" to={`/admin/product/${product.proId}`}>
                              <i className="nc-icon nc-settings-gear-64"></i>
                            </Link>
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
        {
          product && <Row className="justify-content-end pr-3">
            <nav aria-label="...">
              <ul className="pagination">
                {totalPages &&
                  <li className={page + 1 <= 1 ? "page-item disabled" : "page-item"}>
                    <Link className="page-link" to={`?page=${page === 0 ? 0 : page - 1}`} >Previous</Link>
                  </li>
                }
                {[...Array(totalPages)].map((e, index) =>
                  <li key={index} className={page === index ? "page-item active" : "page-item"}><Link className="page-link" to={`?page=${index + 1}`} >{index + 1}</Link></li>
                )}
                {totalPages &&
                  <li className={page + 1 === totalPages ? "page-item disabled" : "page-item"}>
                    <Link className="page-link" to={`?page=${page === 0 ? page + 2 : page + 1}`}>Next</Link>
                  </li>
                }
              </ul>
            </nav>
          </Row>
        }

      </Container>
    </PopUpContext.Provider>
  );
}

export default Product;
