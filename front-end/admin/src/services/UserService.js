import axios from "axios"


export const getAllUsers = async () =>{
  const res = await axios.get(`${process.env.REACT_APP_DEV_ENV}${process.env.REACT_APP_API_US}`)
  return res.data;
}