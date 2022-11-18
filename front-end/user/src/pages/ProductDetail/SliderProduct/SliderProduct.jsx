import React from "react"
import { useState } from "react"
import DetailSliderProduct from "./DetailSliderProduct"
import img from "../../../assets/img/product-detail-1.jpg"
import img1 from "../../../assets/img/product-detail-2.jpg"
import img2 from "../../../assets/img/product-detail-3.jpg"
import { useContext } from "react"
import { ProductDetailProvider } from "../ProductDetail"
const SliderProduct = () => {
	const [currentImg, setCurrentImg] = useState(img)
	const arrImg = [img, img1, img2]
	const { productDetail } = useContext(ProductDetailProvider)
  
	return (
		<div className="col-lg-6">
			{/* PRODUCT SLIDER*/}
			<div className="row m-sm-0">
				<div className="col-sm-2 p-sm-0 order-2 order-sm-1 mt-2 mt-sm-0 px-xl-2">
					<div className="swiper product-slider-thumbs">
						<div className="swiper-wrapper">
							{arrImg.map((img, index) => (
								<DetailSliderProduct
									key={index}
									img={img}
									callBack={() => setCurrentImg(img)}
								/>
							))}
						</div>
					</div>
				</div>
				<div className="col-sm-10 order-1 order-sm-2">
					<div className="swiper product-slider">
						<div className="swiper-wrapper">
							<div className="swiper-slide h-auto">
								<img className="img-fluid" src={currentImg} alt="..." />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	)
}

export default SliderProduct
