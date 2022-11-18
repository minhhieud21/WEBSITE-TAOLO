import React, { useEffect, useState, createContext } from "react"
import Footer from "./Footer"
import Header from "./Header"
import {
	getCartDetailByCartID,
	getAllProduct,
	getLocalStorage,
	getCartByUserId,
	setLocalStorage,
} from "../../services"
import { useCallback } from "react"

export const CartAndProductContext = createContext()

const ContainerMainLayout = ({ children }) => {
	const [product, setProduct] = useState([])
	const [cartId, setCartId] = useState("")
	const [cartDetail, setCartDetail] = useState([])
	const [itemCart, setItemCart] = useState([])
	const token = getLocalStorage("token")
	const userId = getLocalStorage("userId")

	useEffect(() => {
		getCartByUserId(userId).then((res) => {
			setLocalStorage("cartId", res.data.data.cartID)
			setCartId(res.data.data.cartID)
		})
		
		getAllProduct().then((res) => {
			setProduct(res.data)
		})
	}, [])

	useCallback(
	  () => {
		filterCartDetail()
	  },
	  [itemCart.length]
	)
	

	const filterCartDetail = () =>{
		const tmp = []
		getCartDetailByCartID(cartId).then((res) => {
			setCartDetail(res.data.data)
		})

		product.filter(pro => {
			cartDetail.some(el => {
				if(el.proID == pro.proId){
					tmp.push(pro)
					//setCartDetail(pro)
				}
			})
		})
		setItemCart(tmp)
		return tmp;
	}

	return (
		<CartAndProductContext.Provider
			value={{
				product,
				setProduct,
				cartDetail,
				setCartDetail,
				itemCart,
				userId,
				token,
				cartId
			}}
		>
			<div className="container">
				<Header />
				{children}
			</div>
			<Footer />
		</CartAndProductContext.Provider>
	)
}

export default ContainerMainLayout
