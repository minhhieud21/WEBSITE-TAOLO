import axios from "axios"
import { API_USER } from "constant";


export const getAllUsers = async () =>{
  const res = await axios.get(`${process.env.REACT_APP_DEV_ENV}${API_USER}`)
  return res.data;
}

