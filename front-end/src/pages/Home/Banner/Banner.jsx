import React from "react";
import { Link } from "react-router-dom";
import banner from "../../../assets/img/hero-shop.jpg";

const Banner = () => {
  return (
    <>
      <section
        className="hero pb-3 bg-cover bg-center d-flex align-items-center"
        style={{ background: `url(${banner})` }}
      >
        <div className="container py-5">
          <div className="row px-4 px-lg-5">
            <div className="col-lg-6">
              <p className="text-muted small text-uppercase mb-2">
                New Inspiration 2020
              </p>
              <h1 className="h2 text-uppercase mb-3">20% off on new season</h1>
              <Link className="btn btn-dark" to="/shop">
                Browse collections
              </Link>
            </div>
          </div>
        </div>
      </section>
    </>
  );
};

export default Banner;
