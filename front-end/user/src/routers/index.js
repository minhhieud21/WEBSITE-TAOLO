import React, { useEffect } from "react";
import { Route, Routes } from "react-router-dom";
import Cart from "../pages/Cart";
import Checkout from "../pages/Checkout";
import Home from "../pages/Home";
import Login from "../pages/Login";
import ProductDetail from "../pages/ProductDetail";
import Shop from "../pages/Shop";
import { setLocalStorage } from "../services/LocalStorageService"
import firebase from "firebase/compat/app";
import "firebase/compat/auth";
import ForgotPassword from "../pages/Login/ForgotPassword";
import ResetPassword from "../pages/Login/ResetPassword";
import EditAccount from "../pages/Account/EditAccount";
import { PopupSuccess } from "../components/Popup/PopupSuccess";
import { toast, ToastContainer } from "react-toastify";
import SignUp from "../pages/SignUp";
import Purchase from "../pages/Purchase";

const notifySuccess = () => {
  toast.success("Đăng nhập thành công!", {
    position: toast.POSITION.BOTTOM_CENTER,
  })
}

const Routers = () => {
  useEffect(() => {
    const unregisterAuthObserver = firebase
      .auth()
      .onAuthStateChanged(async (user) => {
        if (!user) {
          return;
        }
        const token = await user.getIdToken();
        setLocalStorage("username", user.displayName)
        PopupSuccess("ok")
      });

    return () => unregisterAuthObserver();
  }, []);

  return (
    <>
      <Routes>
        <Route path="/detail/:proId" element={<ProductDetail />} />
        <Route path="/account" element={<EditAccount />} />
        <Route path="/purchase" element={<Purchase />} />
        <Route path="/shop" element={<Shop />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/checkout" element={<Checkout />} />
        <Route path="/login" element={<Login />} />
        <Route path="/sign-up" element={<SignUp />} />
        <Route path="/forgot-password" element={<ForgotPassword />} />
        <Route path="/reset-password" element={<ResetPassword />} />
        <Route path="/" element={<Home />} />
      </Routes>
      <ToastContainer />
    </>
  );
};

export default Routers;
