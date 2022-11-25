import axios from "axios";
import { API_URL, API_GET_CART_DETAIL_BY_CART_ID, API_UPDATE_CART_DETAIL_BY_CART_DETAIL_ID, API_DELETE_CART_DETAIL_BY_CART_DETAIL_ID } from "../assets/constant";

export const getCartDetailByCartID = (cartId) => {
    return axios.get(`${API_URL}${API_GET_CART_DETAIL_BY_CART_ID}?cartID=${cartId}`)
}


export const updateCartDetail = async (cartDID,quantity,token) =>{
    await axios({
		method: "post",
		url: `${API_URL}${API_UPDATE_CART_DETAIL_BY_CART_DETAIL_ID}?cartDID=${cartDID}&quantity=${quantity}`,
        headers:{
            Authorization: token
        }
	})
}

export const deleteCartDetailByCartDetailId = async (cartDID) =>{
    await axios.delete(`${API_URL}${API_DELETE_CART_DETAIL_BY_CART_DETAIL_ID}/${cartDID}`)
}