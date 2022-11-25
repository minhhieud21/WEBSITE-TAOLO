import axios from "axios";
import { API_URL, API_GET_CART_BY_USER_ID, API_CART_ADD_CART, API_CART_READY_CHECKOUT,API_GET_CART_CHECKOUT_BY_USER_ID } from "../assets/constant";

const increaseQuantity = (quantity) => {
    return quantity + 1;
}
const decreaseQuantity = (quantity) => {
    return quantity <= 1 ? 1 : quantity - 1;
}

const formatVnd = (value) => {
    return value && Number(value).toLocaleString('it-IT', { style: 'currency', currency: 'VND' })
}


const getCartByUserId = async (userId,token) => {
    return await axios({
        method: "get",
        url: `${API_URL}${API_GET_CART_BY_USER_ID}?accID=${userId}`,
        headers:{
            Authorization: token
        }
    })
}
export const getCartReadyCheckOutByAccID = async (userId,token) => {
    return await axios({
        method: "get",
        url: `${API_URL}${API_GET_CART_CHECKOUT_BY_USER_ID}?accID=${userId}`,
        headers:{
            Authorization: token
        }
    })
}

export const getBillByAccID = async (userId,token) => {
    return await axios({
        method: "get",
        url: `${API_URL}bill/getBillByAccID?accID=${userId}`,
        headers:{
            Authorization: token
        }
    })
}
export const getBillDetailByBillId = async (billId) => {
    return await axios({
        method: "get",
        url: `${API_URL}billDetail/getBillDetailByBillID?billID=${billId}`,
    })
}
const addCart = async (data,token) => {
    await axios({
        method: "post",
        url: `${API_URL}${API_CART_ADD_CART}`,
        data: data,
        headers:{
            Authorization: token
        }
    })
}

export const readyCheckout = async (data,token) =>{
    await axios({
        method: "post",
        url: `${API_URL}${API_CART_READY_CHECKOUT}`,
        data: data,
        headers:{
            Authorization: token
        }
    })
}


export { increaseQuantity, decreaseQuantity, formatVnd, getCartByUserId, addCart }