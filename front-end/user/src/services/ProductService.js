import axios from 'axios';
import { API_URL,API_PRODUCT_GET_PRODUCT_BY_CATE_ID, API_PRODUCT_SEARCH } from '../assets/constant';

const getAllProduct = async () => {
  //const res = await axios.get(`${process.env.REACT_APP_DEV_ENV}${process.env.REACT_APP_API_PRODUCT}`)
  const res = await axios.get(`${process.env.REACT_APP_DEV_ENV}product/getAllProduct`)
  return res.data;
}

const getProductById = async (proId) =>{
  const res = await axios.get(`${process.env.REACT_APP_DEV_ENV}product/${proId}`)
  return res.data
}

const getProductByCateId = async (cateId) => {
  return await axios.get(`${API_URL}${API_PRODUCT_GET_PRODUCT_BY_CATE_ID}?cateId=${cateId}`,{
    headers: {
        
    }
  })
}

export const searchProductByName = async (searchValue) =>{
  return await axios.get(`${API_URL}${API_PRODUCT_SEARCH}?text=${searchValue}`)
}

export { getAllProduct,getProductById,getProductByCateId }