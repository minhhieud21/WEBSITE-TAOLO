import axios from 'axios';
import {API_URL, API_CATE} from 'constant';

const getAllCategory = async (token) => {
  return await axios({
    url: `${API_URL}${API_CATE}`,
    method: 'get',
    headers: {
      Authorization: token
    }
  })
  
  
}


export { getAllCategory }