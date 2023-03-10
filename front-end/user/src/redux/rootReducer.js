const initState = {
  product: [],

}

const rootReducer = (state = initState, action) => {
  switch (action.type) {
    case 'product/addProduct':
      return {
        ...state,
        product: [
          ...state.product,
          action.payload
        ]
      }
    default:
      return state;
  }
}
export default rootReducer;
