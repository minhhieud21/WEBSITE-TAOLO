import axios from 'axios';
import { API_URL, API_PRODUCT, API_PRODUCT_EDIT, API_PRODUCT_CHANGE_STATUS,API_RECEIPT } from 'constant';

export const getAllReceipt = async (token,pageIndex = 0) => {
  return await axios({
    url: `${process.env.REACT_APP_DEV_ENV}${API_RECEIPT}`,
    method: 'get',
    headers: {
       Authorization: token
    }
  })

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
