import Dashboard from "views/Dashboard.js";
import Product from "views/Product";
import Typography from "views/Typography.js";
import Icons from "views/Icons.js";
import Maps from "views/Maps.js";
import Notifications from "views/Notifications.js";
import Upgrade from "views/Upgrade.js";
import UsersPage from "views/UsersPage";
import Bill from "views/Bill";
import Receipt from "views/Receipt";
import Category from "views/Category";


const dashboardRoutes = [
  {
    path: "/dashboard",
    name: "Dashboard",
    icon: "nc-icon nc-chart-pie-35",
    component: Dashboard,
    layout: "/admin"
  },
  {
    path: "/user",
    name: "User",
    icon: "nc-icon nc-circle-09",
    component: UsersPage,
    layout: "/admin"
  },
  {
    path: "/product",
    name: "Product",
    icon: "nc-icon nc-app",
    component: Product,
    layout: "/admin"
  },
  {
    path: "/bill",
    name: "Bill",
    icon: "nc-icon nc-paper-2",
    component: Bill,
    layout: "/admin"
  },
  {
    path: "/receipt",
    name: "Receipt",
    icon: "nc-icon nc-notes",
    component: Receipt,
    layout: "/admin"
  },
  {
    path: "/category",
    name: "Category",
    icon: "nc-icon nc-layers-3",
    component: Category,
    layout: "/admin"
  },
];

export default dashboardRoutes;
