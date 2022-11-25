import axios from 'axios';
import { API_URL, API_BILL, API_ADD_BILL } from 'constant';

export const getAllBill = async (token) => {
	return await axios({
		method: "get",
		url: `${API_URL}${API_BILL}`,
		headers: {
			Authorization: token
		}

	})
}

export const getBillDetailByBillId = async (billId,token) => {
	return await axios({
		method: "get",
		url: `${API_URL}billDetail/getBillDetailByBillID?billID=${billId}`,
	})
}
export const changStatusBill = async (cartID, token) => {
	await axios({
		method: "post",
		url: `${API_URL}${API_ADD_BILL}`,
		data: cartID,
		headers: {
			Authorization: token
		}

	})
}
