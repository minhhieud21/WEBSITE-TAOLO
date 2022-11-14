import React, { useEffect } from "react";
import { Provider } from "react-redux";
import { PersistGate } from "redux-persist/integration/react";
import ContainerMainLayout from "./layouts/MainLayout/ContainerMainLayout";
import Routers from "./routers";
import 'firebase/compat/auth'
import store from "./redux/store";
import "./assets/css/style.default.css";
import "./assets/css/custom.css"

function App() {
  return (
    <>
      <Provider store={store}>
          <ContainerMainLayout>
            <Routers />
          </ContainerMainLayout>
      </Provider>
    </>
  );
}

export default App;
