import React from "react";
import img from "../../../assets/img/cat-img-1.jpg";

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
            <a className="category-item" href="shop.html">
              <img className="img-fluid" src={img} alt="" />
              <strong className="category-item-title">Clothes</strong>
            </a>
          </div>
          <div className="col-md-4">
            <a className="category-item" href="shop.html">
              <img className="img-fluid" src={img} alt="" />
              <strong className="category-item-title">Clothes</strong>
            </a>
          </div>
          <div className="col-md-4">
            <a className="category-item" href="shop.html">
              <img className="img-fluid" src={img} alt="" />
              <strong className="category-item-title">Clothes</strong>
            </a>
          </div>
        </div>
      </section>
    </>
  );
};

export default Categories;
