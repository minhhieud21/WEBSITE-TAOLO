import React from "react";
import { useEffect,useState } from "react";
import { Link,useParams } from "react-router-dom";
import { getProductById, decreaseQuantity, increaseQuantity,formatVnd } from "../../../services";

const Detail = () => {
  const [quantity, setQuantity] = useState(1)
  const [data, setData] = useState({})
  const {proId} = useParams()

  useEffect(() => {
    getProductById(proId).then(data =>{
      setData(data.data)
    })
  }, [proId])
  
  return (
    <>
      <div className="col-lg-6">
        <h1>{data.proName}</h1>
        <p className="text-muted lead">{data.price}</p>
        <p className="text-sm mb-4">
          {data.description}
        </p>
        <div className="row align-items-stretch mb-4">
          <div className="col-sm-5 pr-sm-0">
            <div className="border d-flex align-items-center justify-content-between py-1 px-3 bg-white border-white">
              <span className="small text-uppercase text-gray mr-4 no-select">
                Quantity
              </span>
              <div className="quantity">
                <button className="dec-btn p-0">
                  <i className="fas fa-caret-left" onClick={() => {return setQuantity(decreaseQuantity(quantity)) }} />
                </button>
                <input
                  className="form-control border-0 shadow-0 p-0"
                  type="text"
                  value={quantity}
                  onChange={() => setQuantity(quantity)}
                />
                <button className="inc-btn p-0">
                  <i className="fas fa-caret-right" onClick={ () => {return setQuantity(increaseQuantity(quantity)) }} />
                </button>
              </div>
            </div>
          </div>
          <div className="col-sm-3 pl-sm-0">
            <Link
              className="btn btn-dark btn-sm btn-block h-100 d-flex align-items-center justify-content-center px-0"
              to="/cart"
            >
              Add to cart
            </Link>
          </div>
        </div>
        <a className="text-dark p-0 mb-4 d-inline-block" href="#!">
          <i className="far fa-heart me-2" />
          Add to wish list
        </a>
        <br />
        <ul className="list-unstyled small d-inline-block">
          <li className="px-3 py-2 mb-1 bg-white">
            <strong className="text-uppercase">SKU:</strong>
            <span className="ms-2 text-muted">039</span>
          </li>
          <li className="px-3 py-2 mb-1 bg-white text-muted">
            <strong className="text-uppercase text-dark">Category:</strong>
            <a className="reset-anchor ms-2" href="#!">
              Demo Products
            </a>
          </li>
          <li className="px-3 py-2 mb-1 bg-white text-muted">
            <strong className="text-uppercase text-dark">Tags:</strong>
            <a className="reset-anchor ms-2" href="#!">
              Innovation
            </a>
          </li>
        </ul>
      </div>
    </>
  );
};

export default Detail;
