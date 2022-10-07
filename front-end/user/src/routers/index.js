import React, { useEffect } from "react";
import { Route, Routes } from "react-router-dom";
import Cart from "../pages/Cart";
import Checkout from "../pages/Checkout";
import Home from "../pages/Home";
import Login from "../pages/Login";
import ProductDetail from "../pages/ProductDetail";
import Shop from "../pages/Shop";
import { getLocalStorage, setLocalStorage } from "../services/LocalStorageService"
import firebase from "firebase/compat/app";
import "firebase/compat/auth";

const config = {
  apiKey: "AIzaSyBG-v1I8sw1lSQ4ElDQD5wIU6uLfV6fGMY",
  authDomain: "ptpmmmn.firebaseapp.com",
  // ...
};
//firebase.initializeApp(config);

const Routers = () => {
  useEffect(() => {
    const unregisterAuthObserver = firebase
      .auth()
      .onAuthStateChanged(async (user) => {
        if (!user) {
          // user logout, handle something here
          console.log("User is not login");
          return;
        }
        const token = await user.getIdToken();
        setLocalStorage("username",user.displayName)
        console.log("Login in user: ", user.displayName);
        console.log("Login in user: ", token);
      });

    return () => unregisterAuthObserver();
  }, []);

  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/shop" element={<Shop />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/detail" element={<ProductDetail />} />
        <Route path="/checkout" element={<Checkout />} />
        <Route path="/login" element={<Login />} />
      </Routes>
    </>
  );
};

export default Routers;