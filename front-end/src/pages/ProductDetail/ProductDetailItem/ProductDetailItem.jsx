import React from "react";
import ItemProduct from "../../../components/ItemProduct/ItemProduct";

const ProductDetailItem = () => {
  return (
    <>
      <h2 className="h5 text-uppercase mb-4">Related products</h2>
      <div className="row">
        <ItemProduct />
        <ItemProduct />
        <ItemProduct />
        <ItemProduct />
      </div>
    </>
  );
};

export default ProductDetailItem;
