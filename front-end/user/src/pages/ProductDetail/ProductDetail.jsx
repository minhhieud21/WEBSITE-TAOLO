import React from "react";
import Detail from "./Detail/Detail";
import DetailTabs from "./DetailTabs/DetailTabs";
import ProductDetailItem from "./ProductDetailItem/ProductDetailItem";
import SliderProduct from "./SliderProduct/SliderProduct";
import { useEffect, useState } from "react"
import { useParams } from "react-router-dom"
import { createContext } from "react";
import { getProductById } from "../../services";


export const ProductDetailProvider = createContext()

const ProductDetail = () => {
	const [productDetail, setProductDetail] = useState({})
	const { proId } = useParams()
  
  useEffect(() => {
		getProductById(proId).then((data) => {
			setProductDetail(data.data)
		})
	}, [proId])



  return (
    <ProductDetailProvider.Provider value={{
      productDetail
    }}>
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
    </ProductDetailProvider.Provider>
  );
};

export default ProductDetail;
