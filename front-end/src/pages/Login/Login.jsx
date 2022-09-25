import React, { useCallback, useEffect } from "react";
import ImgGG from "../../assets/img/google.svg";
//import StyledFirebaseAuth from "react-firebaseui/StyledFirebaseAuth";
//import firebase from "firebase/compat/app";
//import { FirebaseAuth } from "react-firebaseui";
import firebase, { auth } from "../../firebase/config"
/*
const uiConfig = {
  signInFlow: "popup",
  signInSuccessUrl: "/",
  signInOptions: [firebase.auth.GoogleAuthProvider.PROVIDER_ID],
};*/
const ggProvider = new firebase.auth.GoogleAuthProvider();
const Login = () => {
  const handleSignInWithGoogle = () => {
    auth.signInWithPopup(ggProvider).then(res => {
      console.log(res);
    }).catch(err => {
      console.log(err);
    });
  }
  return (
    <>
      <div className="card mb-4" id="forms">
        <div className="card-header">Login</div>
        <div className="card-body">
          <form>
            <div className="mb-3">
              <label className="form-label" htmlFor="exampleInputEmail1">
                Email address
              </label>
              <input
                className="form-control"
                id="exampleInputEmail1"
                type="email"
                aria-describedby="emailHelp"
              />
              <div className="form-text" id="emailHelp">
                We'll never share your email with anyone else.
              </div>
            </div>
            <div className="mb-3">
              <label className="form-label" htmlFor="exampleInputPassword1">
                Password
              </label>
              <input
                className="form-control"
                id="exampleInputPassword1"
                type="password"
              />
            </div>
            <div className="mb-3 form-check">
              <input
                className="form-check-input"
                id="exampleCheck1"
                type="checkbox"
              />
              <label className="form-check-label" htmlFor="exampleCheck1">
                Remember me!
              </label>
            </div>
            <div>
              <button className="btn btn-primary me-3" type="submit">
                Submit
              </button>
            </div>
          </form>
          <div className="ml-3">
            <button className="btn border" onClick={handleSignInWithGoogle}>
              <img
                className="me-2"
                src={ImgGG}
                alt="google"
                style={{ width: "20px", height: "20px" }}
              />
              Google
            </button>
          </div>
        </div>
      </div>
    </>
  );
};

export default Login;
