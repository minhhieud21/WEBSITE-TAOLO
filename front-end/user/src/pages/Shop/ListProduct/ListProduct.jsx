import React from "react";
import { Link } from "react-router-dom";
import ItemProduct from "../../../components/ItemProduct/ItemProduct";
import Loading from "../../../components/Loading/Loading";
const ListProduct = ({products,isLoading}) => {


  return (
    <>
      <div className="col-lg-9 order-1 order-lg-2 mb-5 mb-lg-0">
        <div className="row mb-3 align-items-center">
          <div className="col-lg-6 mb-2 mb-lg-0">
            <p className="text-sm text-muted mb-0">
            </p>
          </div>
          <div className="col-lg-6">
            <ul className="list-inline d-flex align-items-center justify-content-lg-end mb-0">
              <li className="list-inline-item">
         
              </li>
            </ul>
          </div>
        </div>
        <div className="row">
        {isLoading && <Loading/>}
        {products && products.map((product) => (
						<ItemProduct
							key={product.proId}
							proId={product.proId}
							proName={product.proName}
							price={product.price}
							img={product.image}
						/>
					))}
        </div>
      </div>
    </>
  );
};

export default ListProduct;
