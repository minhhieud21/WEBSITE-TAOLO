import React from "react";
import { Link } from "react-router-dom";
import * as LocalStorageService from "../../services/LocalStorageService"
import Logout from "../../pages/Login/Logout";
import { useState } from "react";

const Header = () => {
  const checkLogin = LocalStorageService.checkLocalStorage('username');
  return (
    <>
      <header className="header bg-white">
        <div className="container px-lg-3">
          <nav className="navbar navbar-expand-lg navbar-light py-3 px-lg-0">
            <Link className="navbar-brand" to="/">
              <span className="fw-bold text-uppercase text-dark">Boutique</span>
            </Link>
            <button
              className="navbar-toggler navbar-toggler-end"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon" />
            </button>
            <div
              className="collapse navbar-collapse"
              id="navbarSupportedContent"
            >
              <ul className="navbar-nav me-auto">
                <li className="nav-item">
                  {/* Link*/}
                  <Link className="nav-link" to="/shop">
                    Shop
                  </Link>
                </li>
              </ul>
              <ul className="navbar-nav ms-auto">
                <li className="nav-item pt-2">
                  <Link className="nav-link" to="/cart">
                    {" "}
                    <i className="fas fa-dolly-flatbed me-1 text-gray" />
                    Cart<small className="text-gray fw-normal">(2)</small>
                  </Link>
                </li>
                {/* <li className="nav-item">
                  <Link className="nav-link" href="#!">
                    {" "}
                    <i className="far fa-heart me-1" />
                    <small className="text-gray fw-normal"> (0)</small>
                  </Link>
                </li> */}
                <li className="nav-item">
                  <Link className="nav-link" to="/login">
                    {
                      checkLogin ?  <Logout username={LocalStorageService.getLocalStorage('username')} /> : <div className="pt-2"><i className="fas fa-user me-1 text-gray fw-normal" />Login</div>
                    }
                  </Link>
                </li>
              </ul>
            </div>
          </nav>
        </div>
      </header>
    </>
  );
};

export default Header;
