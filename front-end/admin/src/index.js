import React from "react";
import ReactDOM from "react-dom/client";

import { BrowserRouter, Route, Switch, Redirect } from "react-router-dom";
import { Provider } from "react-redux";

import "bootstrap/dist/css/bootstrap.min.css";
import "./assets/css/animate.min.css";
import "./assets/scss/light-bootstrap-dashboard-react.scss?v=2.0.0";
import "./assets/css/demo.css";
import "@fortawesome/fontawesome-free/css/all.min.css";

import AdminLayout from "layouts/Admin.js";
import Login from "components/Login/Login";
import UserEdit from "views/UserEdit";
import ProductEdit from "views/ProductEdit";
import ProductAdd from "views/ProductAdd";
import NotFound from "components/NotfoundPage";

const root = ReactDOM.createRoot(document.getElementById("root"));

const token = localStorage.getItem("token")

root.render(
  <BrowserRouter>
    <Switch>
      <Route path="/admin/login" component={Login} />
      <Route path="/admin/user/:userId" component={UserEdit} />
      <Route path="/admin/product/:productId" component={ProductEdit} />
      <Route path="/admin" render={(props) => <AdminLayout {...props} />} />
      <Redirect from="/" to="/admin/login" />
      <Route path="*" component={NotFound} />
    </Switch>
  </BrowserRouter>
);
