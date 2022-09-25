import React, { useEffect } from "react";
import { Provider } from "react-redux";
import { PersistGate } from "redux-persist/integration/react";
import ContainerMainLayout from "./layouts/MainLayout/ContainerMainLayout";
import Routers from "./routers";
import { persistor, store } from "./store";

//import StyledFirebaseAuth from 'react-firebaseui/StyledFirebaseAuth';
//import firebase from 'firebase/compat/app';
import 'firebase/compat/auth'

import "./assets/css/style.default.css";

const config = {
  apiKey: 'AIzaSyBG-v1I8sw1lSQ4ElDQD5wIU6uLfV6fGMY',
  authDomain: 'ptpmmmn.firebaseapp.com',
  // ...
};
//firebase.initializeApp(config);


function App() {
  // useEffect(() => {
  //   const unregisterAuthObserver = firebase.auth().onAuthStateChanged(async (user) => {
  //     if(!user){
  //       // user logout, handle something here
  //       console.log("User is not login")
  //       return;
  //     }
  //     const token = await user.getIdToken();
  //     console.log('Login in user: ', user.displayName);
  //     console.log('Login in user: ', token);
  //   });

  //   return () => unregisterAuthObserver();
  // }, []);

  return (
    <>
      <Provider store={store}>
        <PersistGate loading={<div>Loading...</div>} persistor={persistor}>
          <ContainerMainLayout>
            <Routers />
          </ContainerMainLayout>
        </PersistGate>
      </Provider>
    </>
  );
}

export default App;
