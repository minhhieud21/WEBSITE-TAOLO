import { createAsyncThunk } from "@reduxjs/toolkit";
import { getApiPoke } from "./api";

export const fetchApiPoke = createAsyncThunk("API_POKE", async () => {
  const response = await getApiPoke();
  console.log("🚀 ~ file: action.js ~ line 6 ~ fetchApiPoke ~ response", response.data)
  return response;
});
