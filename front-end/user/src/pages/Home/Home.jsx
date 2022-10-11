import React from "react";
import Popup from "../../components/Popup";
import Banner from "./Banner/Banner";
import Categories from "./Categories/Categories";
import HomeProduct from "./HomeProduct/HomeProduct";
import NewsLetter from "./NewsLetter/NewsLetter";
import Services from "./Services/Services";
import { v4 as uuidv4 } from "uuid";
const Home = () => {
  console.log(uuidv4())
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
