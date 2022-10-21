import React, { useCallback, useState } from "react";
import Popup from "../Popup";
import { Link } from "react-router-dom";
import img from "../../assets/img/cat-img-1.jpg";

const ItemProduct = ({proName,price}) => {
  const [popup, setPopup] = useState(false);

  const openPopup = useCallback(() => {
    setPopup(!popup);
  }, []);

  return (
    <>
      <Popup />
      <div className="col-xl-3 col-lg-4 col-sm-6">
        <div className="product text-center">
          <div className="position-relative mb-3">
            {/* <div className="badge text-white bg-danger">Sold</div> */}
            {/* <div className="badge text-white bg-primary">Sale</div> */}
            {/* <div className="badge text-white bg-info">New</div> */}
            <Link className="d-block" to="/detail">
              <img className="img-fluid w-100" src={img} alt="..." />
            </Link>
            <div className="product-overlay">
              <ul className="mb-0 list-inline">
                <li className="list-inline-item m-0 p-0">
                  <a className="btn btn-sm btn-outline-dark" href="#!">
                    <i className="far fa-heart" />
                  </a>
                </li>
                <li className="list-inline-item m-0 p-0">
                  <Link className="btn btn-sm btn-dark" to="/cart">
                    Add to cart
                  </Link>
                </li>
                <li
                  className="list-inline-item me-0"
                  onClick={() => {
                    openPopup();
                  }}
                >
                  <a
                    className="btn btn-sm btn-outline-dark"
                    href="#productView"
                    data-bs-toggle="modal"
                  >
                    <i className="fas fa-expand" />
                  </a>
                </li>
              </ul>
            </div>
          </div>
          <h6>
            {" "}
            <Link className="reset-anchor" to="/detail">
              {proName}
            </Link>
          </h6>
          <p className="small text-muted">{price}</p>
        </div>
      </div>
    </>
  );
};

export default ItemProduct;
