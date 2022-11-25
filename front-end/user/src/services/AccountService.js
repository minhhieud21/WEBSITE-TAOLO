import axios from "axios"
import {
	API_URL,
	API_ACCOUNT_LOGIN,
	API_ACCOUNT_RESET_PASSWORD,
	API_USER,
	API_ACCOUNT_CREATE_NEW_PASSWORD,
	API_ACCOUNT_SIGN_IN
} from "../assets/constant"



const googleLogin = async (data) => {
	await axios({
		method: "post",
		url: `${API_URL}${API_ACCOUNT_LOGIN}?google_login=true&urlID=${data}`,
	})
}

const changePassword = async (data, token) => {
	await axios({
		method: "post",
		url: `${API_URL}account/setPassword`,
		data: data,
		headers: {
			'Content-Type': "multipart/form-data",
			Authorization: token
		},


	})

}

const getUserByUserId = async (userId, token) => {
	return await axios({
		method: "get",
		url: `${API_URL}${API_USER}/${userId}`,
		headers: {
			Authorization: token
		}
	})
}

const forgotPassword = async (data) => {
	await axios({
		method: "post",
		url: `${API_URL}${API_ACCOUNT_RESET_PASSWORD}`,
		data: data,
	})
}

export const signUp = async (data) => {
	await axios({
		method: "post",
		url: `${API_URL}${API_ACCOUNT_SIGN_IN}`,
		data: data,
		headers: {
			'Content-Type': "multipart/form-data"
		},
	})
}


const resetPassword = async (data, cookie) => {
	await axios({
		method: "post",
		url: `${API_URL}${API_ACCOUNT_CREATE_NEW_PASSWORD}`,
		data: data,
		withCredentials: true,
		headers: {
			"Content-type": "application/json",
			'Access-Control-Allow-Origin': "*"
		},
	})

}

export {
	changePassword,
	forgotPassword,
	googleLogin,
	getUserByUserId,
	resetPassword,
}
