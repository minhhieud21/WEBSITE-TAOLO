import axios from 'axios';
import { API_URL, API_PRODUCT, API_PRODUCT_EDIT, API_PRODUCT_CHANGE_STATUS } from 'constant';

const getAllProduct = async () => {
  const res = await axios.get(`${API_URL}${API_PRODUCT}?Type=0`)
  return res.data;
}

const getProductById = async (proId) => {
  const res = await axios.get(`${process.env.REACT_APP_DEV_ENV}product/${proId}`)
  return res.data
}

const updateProduct = async (newProduct) => {
  await axios({
    method: "post",
    url: `${API_URL}${API_PRODUCT_EDIT}`,
    data: newProduct,
    headers: { "Content-Type": "multipart/form-data" },
  });
}


const addProduct = async (newProduct) => {
  await axios({
    method: "post",
    url: `${API_URL}${"product/addProduct"}`,
    data: newProduct,
    headers: { "Content-Type": "multipart/form-data" },
  });
}

const changeStatus = async (data) =>{
  await axios({
    method: "post",
    url: `${API_URL}${API_PRODUCT_CHANGE_STATUS}`,
    data: data,
    headers: { "Content-Type": "multipart/form-data" },
  })
}

export { getAllProduct, getProductById, addProduct , updateProduct,changeStatus }