import React from "react";
import Breadcrumb from "../../components/Breadcrumb/Breadcrumb";
import OrderTotal from "./OrderTotal/OrderTotal";
import CartTable from "./TableCart/CartTable";

const Cart = () => {
  return (
    <>
      <div>
        {/* HERO SECTION*/}
        <Breadcrumb name="Cart" />
        <section className="py-5">
          <h2 className="h5 text-uppercase mb-4">Shopping cart</h2>
          <div className="row">
            <CartTable />
            {/* ORDER TOTAL*/}
            <OrderTotal />
          </div>
        </section>
      </div>
    </>
  );
};

export default Cart;
