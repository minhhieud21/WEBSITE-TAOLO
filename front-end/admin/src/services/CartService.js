import axios from "axios"
import { API_CART,API_URL,API_ADD_BILL,API_GET_CART_DETAIL_BY_CART_ID  } from "constant"

export const getAllCartReadyCheckOut = async (token) => {
	return await axios({
		method: "get",
		url: `${API_URL}${API_CART}`,
		headers:{
			Authorization: token
		}

	})
}

export const getCartDetailByCartID = (cartId) => {
    return axios.get(`${API_URL}${API_GET_CART_DETAIL_BY_CART_ID}?cartID=${cartId}`)
}