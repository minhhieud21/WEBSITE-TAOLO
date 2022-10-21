const getAllCategories = async ()=> {
    const res = await fetch(`${process.env.REACT_APP_DEV_ENV}category/getAllCategoryUser`)//${process.env.REACT_APP_API_CATEGORY}`)
    const data = await res.json();
    return data;
}


export {getAllCategories}