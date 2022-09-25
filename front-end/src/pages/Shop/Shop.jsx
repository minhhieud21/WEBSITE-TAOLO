import React from "react";
import Breadcrumb from "../../components/Breadcrumb/Breadcrumb";
import ListProduct from "./ListProduct/ListProduct";
import SideBar from "./SideBar/SideBar";

const Shop = () => {
  return (
    <>
      <div className="container">
        {/* HERO SECTION*/}
        <Breadcrumb name="Shop" />
        <section className="py-5">
          <div className="container p-0">
            <div className="row">
              {/* SHOP SIDEBAR*/}
              <SideBar />
              {/* SHOP LISTING*/}
              <ListProduct />
            </div>
          </div>
        </section>
      </div>
    </>
  );
};

export default Shop;
