import axios from 'axios';
import {API_URL, API_CATE} from 'constant';

const getAllCategory = async () => {
  const res = await axios.get(`${API_URL}${API_CATE}`)
  return res.data;
}


export { getAllCategory }