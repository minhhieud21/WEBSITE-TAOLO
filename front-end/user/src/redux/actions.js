export const incrementCart = (data) => {
    return {
        type: 'cart/increment',
        payload: data
    }
}