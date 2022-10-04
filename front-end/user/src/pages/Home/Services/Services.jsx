import React from "react";

const Services = () => {
  return (
    <>
      <section className="py-5 bg-light">
        <div className="container">
          <div className="row text-center gy-3">
            <div className="col-lg-4">
              <div className="d-inline-block">
                <div className="d-flex align-items-end">
                  <svg className="svg-icon svg-icon-big svg-icon-light">
                    <use xlinkHref="#delivery-time-1"> </use>
                  </svg>
                  <div className="text-start ms-3">
                    <h6 className="text-uppercase mb-1">Free shipping</h6>
                    <p className="text-sm mb-0 text-muted">
                      Free shipping worldwide
                    </p>
                  </div>
                </div>
              </div>
            </div>
            <div className="col-lg-4">
              <div className="d-inline-block">
                <div className="d-flex align-items-end">
                  <svg className="svg-icon svg-icon-big svg-icon-light">
                    <use xlinkHref="#helpline-24h-1"> </use>
                  </svg>
                  <div className="text-start ms-3">
                    <h6 className="text-uppercase mb-1">24 x 7 service</h6>
                    <p className="text-sm mb-0 text-muted">
                      Free shipping worldwide
                    </p>
                  </div>
                </div>
              </div>
            </div>
            <div className="col-lg-4">
              <div className="d-inline-block">
                <div className="d-flex align-items-end">
                  <svg className="svg-icon svg-icon-big svg-icon-light">
                    <use xlinkHref="#label-tag-1"> </use>
                  </svg>
                  <div className="text-start ms-3">
                    <h6 className="text-uppercase mb-1">Festivaloffers</h6>
                    <p className="text-sm mb-0 text-muted">
                      Free shipping worldwide
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  );
};

export default Services;
