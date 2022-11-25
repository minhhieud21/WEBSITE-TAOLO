import React from "react";
import Detail from "./Detail/Detail";
import DetailTabs from "./DetailTabs/DetailTabs";
import ProductDetailItem from "./ProductDetailItem/ProductDetailItem";
import SliderProduct from "./SliderProduct/SliderProduct";
import { useEffect, useState } from "react"
import { useParams } from "react-router-dom"
import { createContext } from "react";
import { getLocalStorage, getProductById } from "../../services";


export const ProductDetailProvider = createContext()

const ProductDetail = () => {
	const [productDetail, setProductDetail] = useState({})
	const [arrImg, setArrImg] = useState([])
	const { proId } = useParams()
  const token = getLocalStorage("token")

  useEffect(() => {
		getProductById(proId).then((data) => {
			setProductDetail(data.data)
      setArrImg(data.data.Image)
		})
	}, [proId])



  return (
    <ProductDetailProvider.Provider value={{
      productDetail,
      arrImg,
      token
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
