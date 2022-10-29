import axios from 'axios';
import {API_URL, API_PRODUCT} from 'constant';

const getAllProduct = async () => {
  const res = await axios.get(`${API_URL}${API_PRODUCT}`)
  return res.data;
}

const getProductById = async (proId) =>{
  const res = await axios.get(`${process.env.REACT_APP_DEV_ENV}product/${proId}`)
  return res.data
}

export { getAllProduct,getProductById }