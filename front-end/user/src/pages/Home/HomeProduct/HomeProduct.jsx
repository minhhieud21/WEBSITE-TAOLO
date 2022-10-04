import React from "react";
import ItemProduct from "../../../components/ItemProduct/ItemProduct";

const HomeProduct = () => {
  return (
    <>
      <section className="py-5">
        <header>
          <p className="small text-muted small text-uppercase mb-1">
            Made the hard way
          </p>
          <h2 className="h5 text-uppercase mb-4">Top trending products</h2>
        </header>
        <div className="row">
          <ItemProduct />
          <ItemProduct />
          <ItemProduct />
          <ItemProduct />
          <ItemProduct />
          <ItemProduct />
          <ItemProduct />
          <ItemProduct />
        </div>
      </section>
    </>
  );
};

export default HomeProduct;
