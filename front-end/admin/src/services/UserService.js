import axios from "axios"
import { API_CHANGE_STATUS_ACCOUNT,API_USER } from "constant";


export const getAllUsers = async (token) =>{
  return await axios({
    url: `${process.env.REACT_APP_DEV_ENV}${API_USER}`,
    method: 'get',
    headers: {
      Authorization: token
    }
  })
  
}

export const changeStatusUser = async (accID,status,token) =>{
   await axios({
    url: `${process.env.REACT_APP_DEV_ENV}${API_CHANGE_STATUS_ACCOUNT}?accID=${accID}&status=${status}`,
    method: 'post',
    headers: {
      Authorization: token,
      
    }
  })
}


