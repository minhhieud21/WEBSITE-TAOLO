import { createReducer } from "@reduxjs/toolkit";
import { persistReducer } from "redux-persist";
import autoMergeLevel2 from "redux-persist/lib/stateReconciler/autoMergeLevel2";
import storage from "redux-persist/lib/storage";

import { fetchApiPoke } from "./action";

const persistConfig = {
  storage,
  key: "files",
  stateReconciler: autoMergeLevel2,
  blacklist: ["loading", "saving"],
  version: 1,
};

const initState = {
  poke: "",
};

const test = createReducer(initState, (builder) => {
  builder.addCase(fetchApiPoke.fulfilled, (state, { payload }) => {
    state.poke = payload.data;
  });
});

export default persistReducer(persistConfig, test);
