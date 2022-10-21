const increaseQuantity = (quantity) => {
    return quantity + 1;
}
const decreaseQuantity = (quantity) => {
    return quantity <= 1 ? 1 : quantity - 1;
}


export {increaseQuantity, decreaseQuantity}