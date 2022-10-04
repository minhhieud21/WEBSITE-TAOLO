import React from "react";
import Breadcrumb from "../../components/Breadcrumb/Breadcrumb";
import FormCheckout from "./FormCheckout/FormCheckout";
import OrderSummary from "./OrderSummary/OrderSummary";

const Checkout = () => {
  return (
    <>
      <Breadcrumb name="Cart /Checkout" />
      <section className="py-5">
        {/* BILLING ADDRESS*/}
        <h2 className="h5 text-uppercase mb-4">Billing details</h2>
        <div className="row">
          <FormCheckout />
          {/* ORDER SUMMARY*/}
          <OrderSummary />
        </div>
      </section>
    </>
  );
};

export default Checkout;
