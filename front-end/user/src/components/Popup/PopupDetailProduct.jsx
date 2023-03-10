import React from "react";
import { Link } from "react-router-dom";

const PopupDetailProduct = () => {
  return (
    <>
      <div className="modal fade show" id="productView" tabIndex={-1}>
        <div className="modal-dialog modal-lg modal-dialog-centered">
          <div className="modal-content overflow-hidden border-0">
            <button
              className="btn-close p-4 position-absolute top-0 end-0 z-index-20 shadow-0"
              type="button"
              data-bs-dismiss="modal"
              aria-label="Close"
            />
            <div className="modal-body p-0">
              <div className="row align-items-stretch">
                <div className="col-lg-6 p-lg-0">
                  <Link
                    className="glightbox product-view d-block h-100 bg-cover bg-center"
                    style={{
                      background: "url(../../assets/img/product-5.jpg)",
                    }}
                    to="img/product-5.jpg"
                    data-gallery="gallery1"
                    data-glightbox="Red digital smartwatch"
                  />
                  <Link
                    className="glightbox d-none"
                    to="img/product-5-alt-1.jpg"
                    data-gallery="gallery1"
                    data-glightbox="Red digital smartwatch"
                  />
                  <Link
                    className="glightbox d-none"
                    to="img/product-5-alt-2.jpg"
                    data-gallery="gallery1"
                    data-glightbox="Red digital smartwatch"
                  />
                </div>
                <div className="col-lg-6">
                  <div className="p-4 my-md-4">
                    <ul className="list-inline mb-2">
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
                    <h2 className="h4">Red digital smartwatch</h2>
                    <p className="text-muted">$250</p>
                    <p className="text-sm mb-4">
                      Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                      In ut ullamcorper leo, eget euismod orci. Cum sociis
                      natoque penatibus et magnis dis parturient montes nascetur
                      ridiculus mus. Vestibulum ultricies aliquam convallis.
                    </p>
                    <div className="row align-items-stretch mb-4 gx-0">
                      <div className="col-sm-7">
                        <div className="border d-flex align-items-center justify-content-between py-1 px-3">
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
                      <div className="col-sm-5">
                        <Link
                          className="btn btn-dark btn-sm w-100 h-100 d-flex align-items-center justify-content-center px-0"
                          to="/cart"
                        >
                          Add to cart
                        </Link>
                      </div>
                    </div>
                    <Link
                      className="btn btn-link text-dark text-decoration-none p-0"
                      to="#!"
                    >
                      <i className="far fa-heart me-2" />
                      Add to wish list
                    </Link>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default PopupDetailProduct;
