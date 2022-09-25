import React from "react";
import Detail from "./Detail/Detail";
import DetailTabs from "./DetailTabs/DetailTabs";
import ProductDetailItem from "./ProductDetailItem/ProductDetailItem";
import SliderProduct from "./SliderProduct/SliderProduct";

const ProductDetail = () => {
  return (
    <>
      <section className="py-5">
        <div className="container">
          <div className="row mb-5">
            <SliderProduct />
            {/* PRODUCT DETAILS*/}
            <Detail />
          </div>
          {/* DETAILS TABS*/}
          <DetailTabs />
          {/* RELATED PRODUCTS*/}
          <ProductDetailItem />
        </div>
      </section>
    </>
  );
};

export default ProductDetail;
