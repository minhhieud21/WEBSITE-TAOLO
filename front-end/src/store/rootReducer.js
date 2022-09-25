import { combineReducers } from "@reduxjs/toolkit";
import produce from "immer";

import test from "./features/test/reducer";

const appReudcer = combineReducers({
  //import keys reducer
  test,
});

const rootReducer = (state, action) => {
  const newState = produce(state, (draft) => {
    // can handle global action here
  });
  return appReudcer(newState, action);
};

export default rootReducer;
