import axios from "axios";
import { API_URL, API_GET_CART_BY_USER_ID, API_CART_ADD_CART } from "../assets/constant";

const increaseQuantity = (quantity) => {
    return quantity + 1;
}
const decreaseQuantity = (quantity) => {
    return quantity <= 1 ? 1 : quantity - 1;
}

const formatVnd = (value) => {
    return value.toLocaleString('it-IT', { style: 'currency', currency: 'VND' })
}


const getCartByUserId = (userId) => {
    return axios.get(`${API_URL}${API_GET_CART_BY_USER_ID}?accID=${userId}`)
}

const addCart = async (data) => {
    await axios({
        method: "post",
        url: `${API_URL}${API_CART_ADD_CART}`,
        data: data,
    })
}

export { increaseQuantity, decreaseQuantity, formatVnd, getCartByUserId, addCart }