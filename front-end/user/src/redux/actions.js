export const addProduct = (data) => {
    return {
        type: 'product/addProduct',
        payload: data
    }
}