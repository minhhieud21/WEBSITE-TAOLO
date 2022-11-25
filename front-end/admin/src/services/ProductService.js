import axios from 'axios';
import { API_URL, API_PRODUCT, API_PRODUCT_EDIT, API_PRODUCT_CHANGE_STATUS,API_PRODUCT_SEARCH } from 'constant';

const getAllProduct = async (token,pageIndex = 0) => {
  return await axios({
    url: `${process.env.REACT_APP_DEV_ENV}${API_PRODUCT}?type=0&page=${pageIndex}`,
    method: 'get',
    headers: {
      Authorization: token
    }
  })

}
export const formatVnd = (value) => {
  return value && Number(value).toLocaleString('it-IT', { style: 'currency', currency: 'VND' })
}

const getProductById = async (proId) => {
  const res = await axios.get(`${process.env.REACT_APP_DEV_ENV}product/${proId}`)
  return res.data
}

const updateProduct = async (newProduct, token) => {
  await axios({
    method: "post",
    url: `${API_URL}${API_PRODUCT_EDIT}`,
    data: newProduct,
    headers: {
      "Content-Type": "multipart/form-data",
      Authorization: token

    },
  });
}


const addProduct = async (newProduct, token) => {
  await axios({
    method: "post",
    url: `${API_URL}${"product/addProduct"}`,
    data: newProduct,
    headers: {
      "Content-Type": "multipart/form-data",
      Authorization: token

    },
  });
}

const changeStatus = async (data, token) => {
  await axios({
    method: "post",
    url: `${API_URL}${API_PRODUCT_CHANGE_STATUS}`,
    data: data,
    headers: {
      "Content-Type": "multipart/form-data",
      Authorization: token
    },
  })
}

export const searchProductByName = async (searchValue,token) =>{
  return await axios({
    url: `${API_URL}${API_PRODUCT_SEARCH}?text=${searchValue}&type=0`,
    method: 'get',
    headers: {
      Authorization: token
    }
  })
}

export { getAllProduct, getProductById, addProduct, updateProduct, changeStatus }