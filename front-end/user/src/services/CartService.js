const increaseQuantity = (quantity) => {
    return quantity + 1;
}
const decreaseQuantity = (quantity) => {
    return quantity <= 1 ? 1 : quantity - 1;
}

const formatVnd = (value) =>{
    return value.toLocaleString('it-IT', {style : 'currency', currency : 'VND'})
}

export {increaseQuantity, decreaseQuantity,formatVnd}