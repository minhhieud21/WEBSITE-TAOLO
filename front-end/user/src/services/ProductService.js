import axios from 'axios';

const getAllProduct = async () => {
  //const res = await axios.get(`${process.env.REACT_APP_DEV_ENV}${process.env.REACT_APP_API_PRODUCT}`)
  const res = await axios.get(`${process.env.REACT_APP_DEV_ENV}product/getAllProduct`)
  return res.data;
}

const getProductById = async (proId) =>{
  const res = await axios.get(`${process.env.REACT_APP_DEV_ENV}product/${proId}`)
  return res.data
}

export { getAllProduct,getProductById }