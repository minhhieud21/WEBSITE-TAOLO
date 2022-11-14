import axios from "axios"
import {
	API_URL,
	API_ACCOUNT_LOGIN,
	API_ACCOUNT_RESET_PASSWORD,
	API_USER,
	API_ACCOUNT_CREATE_NEW_PASSWORD,
	token
} from "../assets/constant"



const googleLogin = async (data) => {
	await axios({
		method: "post",
		url: `${API_URL}${API_ACCOUNT_LOGIN}?google_login=true&urlID=${data}`,
	})
}

const changePassword = async (data) => {
	await axios({
		method: "post",
		url: `${API_URL}account/setPassword`,
		data: data,
		headers: {
			'Content-Type': "multipart/form-data",
			'Authorization': 'Bearer ' + token
		},


	})
	console.log(token)
}
const getUserByUserId = async (userId) => {
	return await axios.get(`${API_URL}${API_USER}/${userId}`)
}

const forgotPassword = async (data) => {
	await axios({
		method: "post",
		url: `${API_URL}${API_ACCOUNT_RESET_PASSWORD}`,
		data: data,
	})
}

const resetPassword = async (data) => {
	await axios({
		method: "post",
		url: `${API_URL}${API_ACCOUNT_CREATE_NEW_PASSWORD}`,
		data: data,
	})
}

export {
	changePassword,
	forgotPassword,
	googleLogin,
	getUserByUserId,
	resetPassword,
}
