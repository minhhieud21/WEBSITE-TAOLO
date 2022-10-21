import axios from 'axios';

const getAllProduct = async () => {
  const res = await axios.get(`${process.env.REACT_APP_DEV_ENV}${process.env.REACT_APP_API_PRODUCT}`)
  return res.data;
}


export { getAllProduct }