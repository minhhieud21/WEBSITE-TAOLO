import React from "react";
import footer from "../../assets/js/footer.json"
const Footer = () => {
  return (
    <>
      <footer className="bg-dark text-white">
        <div className="container py-4">
          <div className="row py-5">
            <div className="col-md-4 mb-3 mb-md-0">
              <h6 className="text-uppercase mb-3">{footer.CUSTOMER_SERVICES}</h6>
              <ul className="list-unstyled mb-0">
                <li>
                  <a className="footer-link" href="#!">
                    {footer.Help_Contact_Us}
                  </a>
                </li>
                <li>
                  <a className="footer-link" href="#!">
                    {footer.Returns_Refunds}
                  </a>
                </li>
                <li>
                  <a className="footer-link" href="#!">
                    {footer.Online_Stores}
                  </a>
                </li>
                <li>
                  <a className="footer-link" href="#!">
                    {footer.Terms_Conditions}
                  </a>
                </li>
              </ul>
            </div>
            <div className="col-md-4 mb-3 mb-md-0">
              <h6 className="text-uppercase mb-3">Company</h6>
              <ul className="list-unstyled mb-0">
                <li>
                  <a className="footer-link" href="#!">
                    What We Do
                  </a>
                </li>
                <li>
                  <a className="footer-link" href="#!">
                    Available Services
                  </a>
                </li>
                <li>
                  <a className="footer-link" href="#!">
                    Latest Posts
                  </a>
                </li>
                <li>
                  <a className="footer-link" href="#!">
                    FAQs
                  </a>
                </li>
              </ul>
            </div>
            <div className="col-md-4">
              <h6 className="text-uppercase mb-3">Social media</h6>
              <ul className="list-unstyled mb-0">
                <li>
                  <a className="footer-link" href="#!">
                    Twitter
                  </a>
                </li>
                <li>
                  <a className="footer-link" href="#!">
                    Instagram
                  </a>
                </li>
                <li>
                  <a className="footer-link" href="#!">
                    Tumblr
                  </a>
                </li>
                <li>
                  <a className="footer-link" href="#!">
                    Pinterest
                  </a>
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
                  © 2021 All rights reserved.
                </p>
              </div>
              <div className="col-md-6 text-center text-md-end">
                <p className="small text-muted mb-0">
                  Template designed by{" "}
                  <a
                    className="text-white reset-anchor"
                    href="https://bootstrapious.com/p/boutique-bootstrap-e-commerce-template"
                  >
                    Bootstrapious
                  </a>
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
