import React from "react";

const Detail = () => {
  return (
    <>
      <div className="col-lg-6">
        <ul className="list-inline mb-2 text-sm">
          <li className="list-inline-item m-0">
            <i className="fas fa-star small text-warning" />
          </li>
          <li className="list-inline-item m-0 1">
            <i className="fas fa-star small text-warning" />
          </li>
          <li className="list-inline-item m-0 2">
            <i className="fas fa-star small text-warning" />
          </li>
          <li className="list-inline-item m-0 3">
            <i className="fas fa-star small text-warning" />
          </li>
          <li className="list-inline-item m-0 4">
            <i className="fas fa-star small text-warning" />
          </li>
        </ul>
        <h1>Red digital smartwatch</h1>
        <p className="text-muted lead">$250</p>
        <p className="text-sm mb-4">
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ut
          ullamcorper leo, eget euismod orci. Cum sociis natoque penatibus et
          magnis dis parturient montes nascetur ridiculus mus. Vestibulum
          ultricies aliquam convallis.
        </p>
        <div className="row align-items-stretch mb-4">
          <div className="col-sm-5 pr-sm-0">
            <div className="border d-flex align-items-center justify-content-between py-1 px-3 bg-white border-white">
              <span className="small text-uppercase text-gray mr-4 no-select">
                Quantity
              </span>
              <div className="quantity">
                <button className="dec-btn p-0">
                  <i className="fas fa-caret-left" />
                </button>
                <input
                  className="form-control border-0 shadow-0 p-0"
                  type="text"
                  defaultValue={1}
                />
                <button className="inc-btn p-0">
                  <i className="fas fa-caret-right" />
                </button>
              </div>
            </div>
          </div>
          <div className="col-sm-3 pl-sm-0">
            <a
              className="btn btn-dark btn-sm btn-block h-100 d-flex align-items-center justify-content-center px-0"
              href="cart.html"
            >
              Add to cart
            </a>
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
