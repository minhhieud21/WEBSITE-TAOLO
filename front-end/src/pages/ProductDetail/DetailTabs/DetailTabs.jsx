import React from "react";

const DetailTabs = () => {
  return (
    <>
      <ul className="nav nav-tabs border-0" id="myTab" role="tablist">
        <li className="nav-item">
          <a
            className="nav-link text-uppercase active"
            id="description-tab"
            data-bs-toggle="tab"
            href="#description"
            role="tab"
            aria-controls="description"
            aria-selected="true"
          >
            Description
          </a>
        </li>
        <li className="nav-item">
          <a
            className="nav-link text-uppercase"
            id="reviews-tab"
            data-bs-toggle="tab"
            href="#reviews"
            role="tab"
            aria-controls="reviews"
            aria-selected="false"
          >
            Reviews
          </a>
        </li>
      </ul>

      <div className="tab-content mb-5" id="myTabContent">
        <div
          className="tab-pane fade show active"
          id="description"
          role="tabpanel"
          aria-labelledby="description-tab"
        >
          <div className="p-4 p-lg-5 bg-white">
            <h6 className="text-uppercase">Product description </h6>
            <p className="text-muted text-sm mb-0">
              Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
              eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
              enim ad minim veniam, quis nostrud exercitation ullamco laboris
              nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
              reprehenderit in voluptate velit esse cillum dolore eu fugiat
              nulla pariatur. Excepteur sint occaecat cupidatat non proident,
              sunt in culpa qui officia deserunt mollit anim id est laborum.
            </p>
          </div>
        </div>
        <div
          className="tab-pane fade"
          id="reviews"
          role="tabpanel"
          aria-labelledby="reviews-tab"
        >
          <div className="p-4 p-lg-5 bg-white">
            <div className="row">
              <div className="col-lg-8">
                <div className="d-flex mb-3">
                  <div className="flex-shrink-0">
                    <img
                      className="rounded-circle"
                      src="img/customer-1.png"
                      alt=""
                      width={50}
                    />
                  </div>
                  <div className="ms-3 flex-shrink-1">
                    <h6 className="mb-0 text-uppercase">Jason Doe</h6>
                    <p className="small text-muted mb-0 text-uppercase">
                      20 May 2020
                    </p>
                    <ul className="list-inline mb-1 text-xs">
                      <li className="list-inline-item m-0">
                        <i className="fas fa-star text-warning" />
                      </li>
                      <li className="list-inline-item m-0">
                        <i className="fas fa-star text-warning" />
                      </li>
                      <li className="list-inline-item m-0">
                        <i className="fas fa-star text-warning" />
                      </li>
                      <li className="list-inline-item m-0">
                        <i className="fas fa-star text-warning" />
                      </li>
                      <li className="list-inline-item m-0">
                        <i className="fas fa-star-half-alt text-warning" />
                      </li>
                    </ul>
                    <p className="text-sm mb-0 text-muted">
                      Lorem ipsum dolor sit amet, consectetur adipisicing elit,
                      sed do eiusmod tempor incididunt ut labore et dolore magna
                      aliqua.
                    </p>
                  </div>
                </div>
                <div className="d-flex">
                  <div className="flex-shrink-0">
                    <img
                      className="rounded-circle"
                      src="img/customer-2.png"
                      alt=""
                      width={50}
                    />
                  </div>
                  <div className="ms-3 flex-shrink-1">
                    <h6 className="mb-0 text-uppercase">Jane Doe</h6>
                    <p className="small text-muted mb-0 text-uppercase">
                      20 May 2020
                    </p>
                    <ul className="list-inline mb-1 text-xs">
                      <li className="list-inline-item m-0">
                        <i className="fas fa-star text-warning" />
                      </li>
                      <li className="list-inline-item m-0">
                        <i className="fas fa-star text-warning" />
                      </li>
                      <li className="list-inline-item m-0">
                        <i className="fas fa-star text-warning" />
                      </li>
                      <li className="list-inline-item m-0">
                        <i className="fas fa-star text-warning" />
                      </li>
                      <li className="list-inline-item m-0">
                        <i className="fas fa-star-half-alt text-warning" />
                      </li>
                    </ul>
                    <p className="text-sm mb-0 text-muted">
                      Lorem ipsum dolor sit amet, consectetur adipisicing elit,
                      sed do eiusmod tempor incididunt ut labore et dolore magna
                      aliqua.
                    </p>
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

export default DetailTabs;
