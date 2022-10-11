
const initState = {
  cart: [
    {
      id: '',
      name: '',
      amount: 1
    }
  ]
}

const rootReducer = (state = initState, action) => {
  switch (action.type) {
    case 'cart/increment':
      return {
        ...state,
        cart: [
          ...state.cart,
          state.cart.amount + 1
        ]
      }

    default:
      return state;
  }
}
export default rootReducer;
