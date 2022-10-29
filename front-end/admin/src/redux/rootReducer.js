
const initState = {
  product: [],
  users: []
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
    case 'user/addUser':
      return {
        ...state,
        users: [
          ...state.users,
          action.payload
        ]
      }
    default:
      return state;
  }
}
export default rootReducer;
