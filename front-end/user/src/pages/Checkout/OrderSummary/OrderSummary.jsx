import React from "react";

const OrderSummary = () => {
  return (
    <>
      <div className="col-lg-4">
        <div className="card border-0 rounded-0 p-lg-4 bg-light">
          <div className="card-body">
            <h5 className="text-uppercase mb-4">Your order</h5>
            <ul className="list-unstyled mb-0">
              <li className="d-flex align-items-center justify-content-between">
                <strong className="small fw-bold">
                  Red digital smartwatch
                </strong>
                <span className="text-muted small">$250</span>
              </li>
              <li className="border-bottom my-2" />
              <li className="d-flex align-items-center justify-content-between">
                <strong className="small fw-bold">
                  Gray Nike running shoes
                </strong>
                <span className="text-muted small">$351</span>
              </li>
              <li className="border-bottom my-2" />
              <li className="d-flex align-items-center justify-content-between">
                <strong className="text-uppercase small fw-bold">Total</strong>
                <span>$601</span>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </>
  );
};

export default OrderSummary;
