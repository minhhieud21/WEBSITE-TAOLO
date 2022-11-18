import { getLocalStorage } from "../services"

export const API_URL = "http://localhost:8080/api/v1/"

export const API_PRODUCT = "product/getAllProduct"
export const API_PRODUCT_GET_PRODUCT_BY_CATE_ID = "product/getProduct"
export const API_PRODUCT_ADD = "product/addProduct"
export const API_PRODUCT_EDIT = "product/updateProduct"
export const API_PRODUCT_CHANGE_STATUS = "product/changestatus"
export const API_PRODUCT_SEARCH = "product/search"

export const API_CATE = "category/getAllCategoryAdmin"
export const API_USER= "user/getUserByID"

export const API_ACCOUNT_RESET_PASSWORD = "account/resetpassword"
export const API_ACCOUNT_CREATE_NEW_PASSWORD = "account/createnewpassword"
export const API_ACCOUNT_LOGIN = "account/login"

export const API_CART_ADD_CART = "cart/addCart"
export const API_CART_READY_CHECKOUT = "cart/readyCheckout"
export const API_GET_CART_BY_USER_ID = "cart/getCartByAccID"


export const API_GET_CART_DETAIL_BY_CART_ID = "cartdetail/getCartDetailByCartID"
export const API_UPDATE_CART_DETAIL_BY_CART_DETAIL_ID = "cartdetail/updateCartDetail"
export const API_DELETE_CART_DETAIL_BY_CART_DETAIL_ID = "cartdetail/deleteCartDetail"





