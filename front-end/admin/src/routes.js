import Dashboard from "views/Dashboard.js";
import Product from "views/Product";
import Typography from "views/Typography.js";
import Icons from "views/Icons.js";
import Maps from "views/Maps.js";
import Notifications from "views/Notifications.js";
import Upgrade from "views/Upgrade.js";
import UsersPage from "views/UsersPage";
import Bill from "views/Bill";

const dashboardRoutes = [
  {
    bottom: true,
    path: "/upgrade",
    name: "Go user page",
    icon: "nc-icon nc-stre-left",
    component: Upgrade,
    layout: "/admin"
  },
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
    icon: "nc-icon nc-notes",
    component: Bill,
    layout: "/admin"
  },
  {
    path: "/typography",
    name: "Typography",
    icon: "nc-icon nc-paper-2",
    component: Typography,
    layout: "/admin"
  },
  {
    path: "/icons",
    name: "Icons",
    icon: "nc-icon nc-atom",
    component: Icons,
    layout: "/admin"
  },
  {
    path: "/maps",
    name: "Maps",
    icon: "nc-icon nc-pin-3",
    component: Maps,
    layout: "/admin"
  },
  {
    path: "/notifications",
    name: "Notifications",
    icon: "nc-icon nc-bell-55",
    component: Notifications,
    layout: "/admin"
  }
];

export default dashboardRoutes;
