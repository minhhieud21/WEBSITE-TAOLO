import axios from 'axios';
import { API_URL,API_BILL } from 'constant';

export const getAllBill = async () => {
  const res = await axios.get(`${API_URL}${API_BILL}`)
  return res.data;
}