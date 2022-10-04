import React from "react";

const SideBar = () => {
  return (
    <>
      <div className="col-lg-3 order-2 order-lg-1">
        <h5 className="text-uppercase mb-4">Categories</h5>
        <div className="py-2 px-4 bg-dark text-white mb-3">
          <strong className="small text-uppercase fw-bold">
            Fashion &amp; Acc
          </strong>
        </div>
        <ul className="list-unstyled small text-muted ps-lg-4 font-weight-normal">
          <li className="mb-2">
            <a className="reset-anchor" href="#!">
              Women's T-Shirts
            </a>
          </li>
          <li className="mb-2">
            <a className="reset-anchor" href="#!">
              Men's T-Shirts
            </a>
          </li>
          <li className="mb-2">
            <a className="reset-anchor" href="#!">
              Dresses
            </a>
          </li>
          <li className="mb-2">
            <a className="reset-anchor" href="#!">
              Novelty socks
            </a>
          </li>
          <li className="mb-2">
            <a className="reset-anchor" href="#!">
              Women's sunglasses
            </a>
          </li>
          <li className="mb-2">
            <a className="reset-anchor" href="#!">
              Men's sunglasses
            </a>
          </li>
        </ul>
        <div className="py-2 px-4 bg-light mb-3">
          <strong className="small text-uppercase fw-bold">
            Health &amp; Beauty
          </strong>
        </div>
        <ul className="list-unstyled small text-muted ps-lg-4 font-weight-normal">
          <li className="mb-2">
            <a className="reset-anchor" href="#!">
              Shavers
            </a>
          </li>
          <li className="mb-2">
            <a className="reset-anchor" href="#!">
              bags
            </a>
          </li>
          <li className="mb-2">
            <a className="reset-anchor" href="#!">
              Cosmetic
            </a>
          </li>
          <li className="mb-2">
            <a className="reset-anchor" href="#!">
              Nail Art
            </a>
          </li>
          <li className="mb-2">
            <a className="reset-anchor" href="#!">
              Skin Masks &amp; Peels
            </a>
          </li>
          <li className="mb-2">
            <a className="reset-anchor" href="#!">
              Korean cosmetics
            </a>
          </li>
        </ul>
        <div className="py-2 px-4 bg-light mb-3">
          <strong className="small text-uppercase fw-bold">Electronics</strong>
        </div>
        <ul className="list-unstyled small text-muted ps-lg-4 font-weight-normal mb-5">
          <li className="mb-2">
            <a className="reset-anchor" href="#!">
              Electronics
            </a>
          </li>
          <li className="mb-2">
            <a className="reset-anchor" href="#!">
              USB Flash drives
            </a>
          </li>
          <li className="mb-2">
            <a className="reset-anchor" href="#!">
              Headphones
            </a>
          </li>
          <li className="mb-2">
            <a className="reset-anchor" href="#!">
              Portable speakers
            </a>
          </li>
          <li className="mb-2">
            <a className="reset-anchor" href="#!">
              Cell Phone bluetooth headsets
            </a>
          </li>
          <li className="mb-2">
            <a className="reset-anchor" href="#!">
              Keyboards
            </a>
          </li>
        </ul>
        <h6 className="text-uppercase mb-4">Price range</h6>
        <div className="price-range pt-4 mb-5">
          <div id="range" />
          <div className="row pt-2">
            <div className="col-6">
              <strong className="small fw-bold text-uppercase">From</strong>
            </div>
            <div className="col-6 text-end">
              <strong className="small fw-bold text-uppercase">To</strong>
            </div>
          </div>
        </div>
        <h6 className="text-uppercase mb-3">Show only</h6>
        <div className="form-check mb-1">
          <input className="form-check-input" type="checkbox" id="checkbox_1" />
          <label className="form-check-label" htmlFor="checkbox_1">
            Returns Accepted
          </label>
        </div>
        <div className="form-check mb-1">
          <input className="form-check-input" type="checkbox" id="checkbox_2" />
          <label className="form-check-label" htmlFor="checkbox_2">
            Returns Accepted
          </label>
        </div>
        <div className="form-check mb-1">
          <input className="form-check-input" type="checkbox" id="checkbox_3" />
          <label className="form-check-label" htmlFor="checkbox_3">
            Completed Items
          </label>
        </div>
        <div className="form-check mb-1">
          <input className="form-check-input" type="checkbox" id="checkbox_4" />
          <label className="form-check-label" htmlFor="checkbox_4">
            Sold Items
          </label>
        </div>
        <div className="form-check mb-1">
          <input className="form-check-input" type="checkbox" id="checkbox_5" />
          <label className="form-check-label" htmlFor="checkbox_5">
            Deals &amp; Savings
          </label>
        </div>
        <div className="form-check mb-4">
          <input className="form-check-input" type="checkbox" id="checkbox_6" />
          <label className="form-check-label" htmlFor="checkbox_6">
            Authorized Seller
          </label>
        </div>
        <h6 className="text-uppercase mb-3">Buying format</h6>
        <div className="form-check mb-1">
          <input
            className="form-check-input"
            type="radio"
            name="customRadio"
            id="radio_1"
          />
          <label className="form-check-label" htmlFor="radio_1">
            All Listings
          </label>
        </div>
        <div className="form-check mb-1">
          <input
            className="form-check-input"
            type="radio"
            name="customRadio"
            id="radio_2"
          />
          <label className="form-check-label" htmlFor="radio_2">
            Best Offer
          </label>
        </div>
        <div className="form-check mb-1">
          <input
            className="form-check-input"
            type="radio"
            name="customRadio"
            id="radio_3"
          />
          <label className="form-check-label" htmlFor="radio_3">
            Auction
          </label>
        </div>
        <div className="form-check mb-1">
          <input
            className="form-check-input"
            type="radio"
            name="customRadio"
            id="radio_4"
          />
          <label className="form-check-label" htmlFor="radio_4">
            Buy It Now
          </label>
        </div>
      </div>
    </>
  );
};

export default SideBar;
