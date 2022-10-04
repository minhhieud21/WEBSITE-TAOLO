import React from "react";
import Footer from "./Footer";
import Header from "./Header";

const ContainerMainLayout = ({ children }) => {
  return (
    <>
      <div className="container">
        <Header />
        {children}
      </div>
      <Footer />
    </>
  );
};

export default ContainerMainLayout;
