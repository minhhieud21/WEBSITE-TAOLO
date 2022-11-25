import axios from "axios"
import { API_STATISTICAL,API_URL } from "constant"

export const getStatistical = async (token) => {
	return await axios({
		method: "get",
		url: `${API_URL}${API_STATISTICAL}?type=1`,
		headers:{
			Authorization: token
		}

	})
}

