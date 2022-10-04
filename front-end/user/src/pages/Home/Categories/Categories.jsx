import React from "react";
import img from "../../../assets/img/cat-img-1.jpg";
import { Link } from "react-router-dom";
const Categories = () => {
  return (
    <>
      <section className="pt-5">
        <header className="text-center">
          <p className="small text-muted small text-uppercase mb-1">
            Carefully created collections
          </p>
          <h2 className="h5 text-uppercase mb-4">Browse our categories</h2>
        </header>
        <div className="row">
          <div className="col-md-4">
            <Link className="category-item" to="/shop">
              <img className="img-fluid" src={img} alt="" />
              <strong className="category-item-title">Clothes</strong>
            </Link>
          </div>
          <div className="col-md-4">
            <Link className="category-item" to="/shop">
              <img className="img-fluid" src={img} alt="" />
              <strong className="category-item-title">Clothes</strong>
            </Link>
          </div>
          <div className="col-md-4">
            <Link className="category-item" to="/shop">
              <img className="img-fluid" src={img} alt="" />
              <strong className="category-item-title">Clothes</strong>
            </Link>
          </div>
        </div>
      </section>
    </>
  );
};

export default Categories;
