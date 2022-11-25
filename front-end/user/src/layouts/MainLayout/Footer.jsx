import React from "react";
import { Link } from "react-router-dom";
import footer from "../../assets/js/footer.json"
const Footer = () => {
  return (
    <>
      <footer className="bg-dark text-white ">
        <div className="container py-4">
          <div className="row py-5">
            <div className="col-md-4 mb-3 mb-md-0">
              <h6 className="text-uppercase mb-3">{footer.CUSTOMER_SERVICES}</h6>
              <ul className="list-unstyled mb-0">
                <li>
                  <Link className="footer-link" to="#!">
                    {footer.Help_Contact_Us}
                  </Link>
                </li>
                <li>
                  <Link className="footer-link" to="#!">
                    {footer.Returns_Refunds}
                  </Link>
                </li>
                <li>
                  <Link className="footer-link" to="#!">
                    {footer.Online_Stores}
                  </Link>
                </li>
                <li>
                  <Link className="footer-link" to="#!">
                    {footer.Terms_Conditions}
                  </Link>
                </li>
              </ul>
            </div>
            <div className="col-md-4 mb-3 mb-md-0">
              <h6 className="text-uppercase mb-3">Company</h6>
              <ul className="list-unstyled mb-0">
                <li>
                  <Link className="footer-link" to="#!">
                    What We Do
                  </Link>
                </li>
                <li>
                  <Link className="footer-link" to="#!">
                    Available Services
                  </Link>
                </li>
                <li>
                  <Link className="footer-link" to="#!">
                    Latest Posts
                  </Link>
                </li>
                <li>
                  <Link className="footer-link" to="#!">
                    FAQs
                  </Link>
                </li>
              </ul>
            </div>
            <div className="col-md-4">
              <h6 className="text-uppercase mb-3">Social media</h6>
              <ul className="list-unstyled mb-0">
                <li>
                  <Link className="footer-link" to="#!">
                    Twitter
                  </Link>
                </li>
                <li>
                  <Link className="footer-link" to="#!">
                    Instagram
                  </Link>
                </li>
                <li>
                  <Link className="footer-link" to="#!">
                    Tumblr
                  </Link>
                </li>
                <li>
                  <Link className="footer-link" to="#!">
                    Pinterest
                  </Link>
                </li>
              </ul>
            </div>
          </div>
          <div
            className="border-top pt-4"
            style={{ borderColor: "#1d1d1d !important" }}
          >
            <div className="row">
              <div className="col-md-6 text-center text-md-start">
                <p className="small text-muted mb-0">
                  © 2022 All rights reserved.
                </p>
              </div>
              <div className="col-md-6 text-center text-md-end">
                <p className="small text-muted mb-0">
                  Template designed by{" "}
            
                    Taolo
                
                </p>
                {/* If you want to remove the backlink, please purchase the Attribution-Free License. See details in readme.txt or license.txt. Thanks!*/}
              </div>
            </div>
          </div>
        </div>
      </footer>
    </>
  );
};

export default Footer;
