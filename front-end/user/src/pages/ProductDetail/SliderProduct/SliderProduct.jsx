import React from "react"
import { useState, useMemo, useContext } from "react"
import DetailSliderProduct from "./DetailSliderProduct"
import { ProductDetailProvider } from "../ProductDetail"

const SliderProduct = () => {
	const [currentImg, setCurrentImg] = useState(0)
	const { productDetail, arrImg } = useContext(ProductDetailProvider)

	const imgSlice = useMemo(() => {
		return arrImg.slice(0, 5)
	}, [arrImg])

	return (
		<div className="col-lg-6">
			{/* PRODUCT SLIDER*/}
			<div className="row m-sm-0">
				<div className="col-sm-2 p-sm-0 order-2 order-sm-1 mt-2 mt-sm-0 px-xl-2">
					<div className="swiper product-slider-thumbs">
						{imgSlice &&
							imgSlice.map((imgSlice, index) => (
								<div
									className="swiper-wrapper"
									key={index}
									onClick={(e) => setCurrentImg(index)}
								>
									<DetailSliderProduct
										img={`/Image/${productDetail.proId}/${imgSlice}`}
									/>
								</div>
							))}
					</div>
				</div>
				<div className="col-sm-10 order-1 order-sm-2">
					<div className="swiper product-slider">
						<div className="swiper-wrapper">
							<div className="swiper-slide h-auto">
								<img
									className="img-fluid"
									src={`/Image/${productDetail.proId}/${imgSlice[currentImg]}`}
									alt="..."
								/>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	)
}

export default SliderProduct
