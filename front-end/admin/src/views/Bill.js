import { AdminContext } from "layouts/Admin";
import React, { useCallback, useContext, useEffect, useMemo, useState } from "react";

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
  Modal,
} from "react-bootstrap";
import { Link, useLocation, useParams } from "react-router-dom";
import { formatVnd } from "services";
import { getBillDetailByBillId } from "services";
import { changStatusBill } from "services";
import { getAllProduct } from "services";
import { getAllBill } from "services";
import { getCartDetailByCartID } from "services/CartService";
import { getAllCartReadyCheckOut } from "services/CartService";


function Bill() {
  const [showModal, setShowModal] = useState(false)

  const [cart, setCart] = useState([])
  const [cartId, setCartId] = useState("")
  const [bill, setBill] = useState([])
  const [filter, setFilter] = useState("notReady");
  const [product, setProduct] = useState([])
  const [billItem, setBillItem] = useState([])
  const [tmp, setTmp] = useState([])
  const [cartDetail, setCartDetail] = useState([])

  const headers = ['Index', 'Account ID', 'Order ID', 'Address', 'Method Pay', 'Phone Number', 'Total Quantity', 'Total Cost', 'Action']
  const headersPopUp = ['Product name', 'Color', 'Warranty', 'Price', 'Quantity', 'Total Cost']

  const { token } = useContext(AdminContext)
  const search = useLocation().search
  const cartID = new URLSearchParams(search).get("cartID")

  useEffect(() => {
    getAllCartReadyCheckOut(token).then(res => setCart(res.data.data)).catch(e => console.log(e))
    getAllBill(token).then(res => setBill(res.data.data))


    getAllProduct(token).then(res => {
      setProduct(res.data.data.content)
    }).catch(e => console.log(e))

    getCartDetailByCartID(cartId).then((res) => {
      setCartDetail(res.data.data)
    })

    getBillDetailByBillId(cartID).then(res => {
      setBillItem(res.data.data)
    })
    setTmp(itemBill)
  }, [cartID, bill.length, cart.length, billItem.length])


  const handleChangeFilter = (e) => {
    setFilter(e.target.value);
  };

  const itemBill = useMemo(() => {
    let itemCart = []
    billItem.filter((bill) => ({
      ...bill,
      ...product.filter((pro) => {
        if (bill.proID === pro.proId && bill.billID === cartID) {
          const tmp = {
            ...pro,
            quantity: bill.quantity,
            cost: bill.cost,
            cartDID: bill.cartDID,
          }
          itemCart.push(tmp)
        }
      }),
    }))
    return itemCart

  }, [cartID, cart, bill])

  const itemCart = useMemo(() => {
    let itemCart = []
    cartDetail.filter((cart) => ({
      ...cart,
      ...product.filter((pro) => {
        if (cart.proID === pro.proId && cart.cartID === cartID) {
          const tmp = {
            ...pro,
            quantity: cart.quantity,
            cost: cart.cost,
            cartDID: cart.cartDID,
          }
          itemCart.push(tmp)
        }
      }),
    }))
    return itemCart
  }, [cartID, cart, bill])


  const data = useMemo(() => {
    return filter === "ready" ? bill : cart
  }, [cart, bill, filter])

  const handleChangeStatusBill = useCallback(async () => {
    const params = {
      cartID
    }
    await changStatusBill(params, token)
    setShowModal(false)
  }, [])


  return (
    <>
      <Container fluid>
        <Row>
          <Col md="12">
            <Card className="strpied-tabled-with-hover">
              <Card.Header>
                <Card.Title as="h4">Manage Bill in TAOLO</Card.Title>
                <div style={{
                  float: 'right'
                }}>
                  <select value={filter} onChange={handleChangeFilter}>
                    <option value="notReady">Not ready</option>
                    <option value="ready" >Ready</option>
                  </select>

                </div>
              </Card.Header>
              <Card.Body className="table-full-width table-responsive px-0">
                <Table className="table-hover table-striped">
                  <thead>
                    <tr>
                      {headers.map((header, index) => <th key={index} className="border-0">{header}</th>)}
                    </tr>
                  </thead>
                  <tbody>
                    {data && data.map((cart, index) => <tr key={index}>
                      <td>{index + 1}</td>
                      <td>{cart.accID}</td>
                      <td>{cart.cartID || cart.billID}</td>
                      <td>{cart.address}</td>
                      <td>{cart.methodPay}</td>
                      <td>{cart.phone}</td>
                      <td>{cart.totalQuantity || cart.toTalQuantity}</td>
                      <td>{formatVnd(cart.totalCost)}</td>
                      {filter !== "ready" ? <td className="text-danger">{"Not ready"}</td> : <td className="text-success">{"Ready"}</td>}
                      <td className="pl-3" role="button" onClick={() => {
                        setCartId(cart.cartID)
                        setShowModal(true);
                      }}>
                        <Link to={`?cartID=${cart.cartID || cart.billID}`}><i className="nc-icon nc-settings-gear-64"></i></Link>
                      </td>

                    </tr>)}
                  </tbody>
                </Table>
              </Card.Body>
            </Card>
          </Col>
        </Row>

        <Modal
          className="modal-custom"
          show={showModal}
          onHide={() => setShowModal(false)}
        >
          <Modal.Header className="justify-content-center">
            <div className="modal-profile">
              <i className="nc-icon nc-bulb-63"></i>
            </div>
          </Modal.Header>
          <Modal.Body className="text-center">
            <p>Order verification ?</p>
            <Table className="table-hover table-striped">
              <thead>
                <tr>
                  {headersPopUp.map((header, index) => <th key={index} className="border-0">{header}</th>)}
                </tr>
              </thead>
              <tbody>
                {filter === "ready" ? itemBill && itemBill.map((item, index) => <tr key={index}>
                  <td>{item.proName}</td>
                  <td>{item.color}</td>
                  <td>{item.warrantyMonth}</td>
                  <td>{item.price}</td>
                  <td>{item.quantity}</td>
                  <td>{formatVnd(item.cost)}</td>
                </tr>) : itemCart && itemCart.map((item, index) => <tr key={index}>
                  <td>{item.proName}</td>
                  <td>{item.color}</td>
                  <td>{item.warrantyMonth}</td>
                  <td>{item.price}</td>
                  <td>{item.quantity}</td>
                  <td>{formatVnd(item.cost)}</td>
                </tr>)}

              </tbody>
            </Table>
          </Modal.Body>
          <div className="modal-footer justify-content-end">
            {filter !== "ready" &&
              <Button
                className="border btn btn-primary btn-fill"
                type="button"
                variant="info"
                onClick={() => handleChangeStatusBill()}
              >
                Confirm
              </Button>}
            <Button
              className="btn-simple btn-fill ml-3"
              type="button"
              variant="link"
              onClick={() => setShowModal(false)}
            >
              Close
            </Button>
          </div>
        </Modal>
      </Container>
    </>
  );
}

export default Bill;
