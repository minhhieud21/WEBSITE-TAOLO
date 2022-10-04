import { axiosClient } from "../../api/axiosClient";
import { handleResponse } from "../../utilities";

export const getApiPoke = () => {
  return axiosClient.get(`pokemon?limit=100000&offset=0`);
};
