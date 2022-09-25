import React from "react";
import img from "../../../assets/img/product-detail-1.jpg";

const SliderProduct = () => {
  return (
    <>
      <div className="col-lg-6">
        {/* PRODUCT SLIDER*/}
        <div className="row m-sm-0">
          <div className="col-sm-2 p-sm-0 order-2 order-sm-1 mt-2 mt-sm-0 px-xl-2">
            <div className="swiper product-slider-thumbs">
              <div className="swiper-wrapper">
                <div className="swiper-slide h-auto swiper-thumb-item mb-3">
                  <img className="w-100" src={img} alt="..." />
                </div>
                <div className="swiper-slide h-auto swiper-thumb-item mb-3">
                  <img className="w-100" src={img} alt="..." />
                </div>
                <div className="swiper-slide h-auto swiper-thumb-item mb-3">
                  <img className="w-100" src={img} alt="..." />
                </div>
                <div className="swiper-slide h-auto swiper-thumb-item mb-3">
                  <img className="w-100" src={img} alt="..." />
                </div>
              </div>
            </div>
          </div>
          <div className="col-sm-10 order-1 order-sm-2">
            <div className="swiper product-slider">
              <div className="swiper-wrapper">
                <div className="swiper-slide h-auto">
                  <a
                    className="glightbox product-view"
                    href="img/product-detail-1.jpg"
                    data-gallery="gallery2"
                    data-glightbox="Product item 1"
                  >
                    <img className="img-fluid" src={img} alt="..." />
                  </a>
                </div>
                {/* <div className="swiper-slide h-auto">
                  <a
                    className="glightbox product-view"
                    href="img/product-detail-2.jpg"
                    data-gallery="gallery2"
                    data-glightbox="Product item 2"
                  >
                    <img
                      className="img-fluid"
                      src={"img/product-detail-2.jpg"}
                      alt="..."
                    />
                  </a>
                </div>
                <div className="swiper-slide h-auto">
                  <a
                    className="glightbox product-view"
                    href="img/product-detail-3.jpg"
                    data-gallery="gallery2"
                    data-glightbox="Product item 3"
                  >
                    <img
                      className="img-fluid"
                      src="img/product-detail-3.jpg"
                      alt="..."
                    />
                  </a>
                </div>
                <div className="swiper-slide h-auto">
                  <a
                    className="glightbox product-view"
                    href="img/product-detail-4.jpg"
                    data-gallery="gallery2"
                    data-glightbox="Product item 4"
                  >
                    <img
                      className="img-fluid"
                      src="img/product-detail-4.jpg"
                      alt="..."
                    />
                  </a>
                </div> */}
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default SliderProduct;
