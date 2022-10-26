export const addProduct = (data) => {
    return {
        type: 'product/addProduct',
        payload: data
    }
}

export const getAllUsersRedux = () =>{
    return {
        type: 'user/getAll'
    }
}

export const addUser = (data) =>{
    return {
        type: 'user/addUser',
        payload:data
    }
}