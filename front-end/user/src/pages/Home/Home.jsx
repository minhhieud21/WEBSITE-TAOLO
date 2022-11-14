import React from "react";
import Popup from "../../components/Popup";
import Banner from "./Banner/Banner";
import Categories from "./Category/Categories";
import HomeProduct from "./HomeProduct/HomeProduct";
import NewsLetter from "./NewsLetter/NewsLetter";
import Services from "./Services/Services";

const Home = () => {
  return (
    <>
      <Popup />
      <Banner />
      {/* CATEGORIES SECTION*/}
      <Categories />
      {/* TRENDING PRODUCTS*/}
      <HomeProduct />
      {/* SERVICES*/}
      <Services />
      {/* NEWSLETTER*/}
      <NewsLetter />
    </>
  );
};

export default Home;
