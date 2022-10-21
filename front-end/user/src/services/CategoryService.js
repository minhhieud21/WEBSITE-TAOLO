const getAllCategories = async ()=> {
    const res = await fetch(`${process.env.REACT_APP_DEV_ENV}${process.env.REACT_APP_API_CATEGORY}`)
    const data = res.data;
    return data;
}


export {getAllCategories}