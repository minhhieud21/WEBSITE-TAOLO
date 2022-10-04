import React, { useState } from "react";
import { Link } from "react-router-dom";
import img from "../../../assets/img/product-1.jpg";
import * as cartService from '../../../services/CartService'
const ItemCart = () => {
  const [quantity, setQuantity] = useState(1)
  return (
    <>
      <tr>
        <th className="ps-0 py-3 border-light" scope="row">
          <div className="d-flex align-items-center">
            <Link
              className="reset-anchor d-block animsition-link"
              to="/detail"
            >
              <img src={img} alt="..." width={70} />
            </Link>
            <div className="ms-3">
              <strong className="h6">
                <Link className="reset-anchor animsition-link" to="/detail">
                  Red digital smartwatch
                </Link>
              </strong>
            </div>
          </div>
        </th>
        <td className="p-3 align-middle border-light">
          <p className="mb-0 small">$250</p>
        </td>
        <td className="p-3 align-middle border-light">
          <div className="border d-flex align-items-center justify-content-between px-3">
            <span className="small text-uppercase text-gray headings-font-family">
              Quantity
            </span>
            <div className="quantity">
              <button className="dec-btn p-0">
                <i className="fas fa-caret-left" onClick={() => { return setQuantity(cartService.decreaseQuantity(quantity)) }} />
              </button>
              <input
                className="form-control form-control-sm border-0 shadow-0 p-0"
                type="text"
                value={quantity}
                onChange={() => setQuantity(quantity)}
              />
              <button className="inc-btn p-0">
                <i className="fas fa-caret-right" onClick={() => { return setQuantity(cartService.increaseQuantity(quantity)) }} />
              </button>
            </div>
          </div>
        </td>
        <td className="p-3 align-middle border-light">
          <p className="mb-0 small">$250</p>
        </td>
        <td className="p-3 align-middle border-light">
          <a className="reset-anchor" href="#!">
            <i className="fas fa-trash-alt small text-muted" />
          </a>
        </td>
      </tr>
    </>
  );
};

export default ItemCart;
