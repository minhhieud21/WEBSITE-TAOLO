import axios from "axios";

const baseURL = "https://pokeapi.co/api/v2/";

export const axiosClient = axios.create({
  baseURL,
  headers: {
    "content-type": "application/json",
  },
});

export const updateClient = axios.create({
  baseURL,
  headers: {
    Accept: "multipart/form-data",
    "Content-Type": "multipart/form-data",
  },
  processData: false,
  contentType: false,
});

export const setAccessToken = (t) => {
  axiosClient.defaults.headers.common.Authorization = `Bearer ${t}`;
  axiosClient.defaults.headers.common['X-CSRF-TOKEN'] = t;

  updateClient.defaults.headers.common.Authorization = `Bearer ${t}`;
  updateClient.defaults.headers.common['X-CSRF-TOKEN'] = t;
};