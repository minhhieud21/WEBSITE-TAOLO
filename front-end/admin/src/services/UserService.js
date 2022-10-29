import axios from "axios"

export const getAllUsers = async () =>{
  const res = await axios.get(`http://localhost:8080/api/v1/user`)
  return res.data;
}