import { getAllProduct } from "../services";

let products = []
getAllProduct().then((res) => {
  products = res.data.content
}).catch(e => console.log(e))

const initState = {
  product: products
}

const rootReducer = (state = initState, action) => {
  switch (action.type) {
    case 'product/addProduct':
      return{
        ...state,
        product: [
          ...state.product,
        ]
      }
    default:
      return state;
  }
}
export default rootReducer;
