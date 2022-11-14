const { API_LOGIN } = require("constant")
const { API_URL } = require("constant")
import axios from "axios"

export const login = async (data) => {
	return await axios({
		method: "post",
		url: `${API_URL}${API_LOGIN}`,
        data
	})
}

