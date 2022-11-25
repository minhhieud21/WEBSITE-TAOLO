import React, { useEffect, useState, createContext, useMemo } from "react"
import Footer from "./Footer"
import Header from "./Header"
import {
	getCartDetailByCartID,
	getAllProduct,
	getLocalStorage,
	getCartByUserId,
	setLocalStorage,
} from "../../services"

export const CartAndProductContext = createContext()

const ContainerMainLayout = ({ children }) => {
	const [product, setProduct] = useState([])
	const [isLoading, setIsLoading] = useState(false)
	const [cartId, setCartId] = useState("")
	const [cartDetail, setCartDetail] = useState([])
	const [cart, setCart] = useState([])
	const token = getLocalStorage("token")
	const userId = getLocalStorage("userId")

	useEffect(() => {
		setIsLoading(true)
		if (token && userId) {
			getCartByUserId(userId, token).then((res) => {
				if(res){
					setCart(res.data.data)
					setCartId(res.data.data.cartID)
					setLocalStorage('cartId',res.data.data.cartID)
				}
			}).catch(e => e)
		}
		getCartDetailByCartID(cartId).then((res) => {
			setCartDetail(res.data.data)
		})

		getAllProduct()
			.then((res) => {
				setProduct(res.data.content)
			})
			.finally(() => {
				setIsLoading(false)
			})
	}, [token,cartDetail.length])



	const itemCart = useMemo(() => {
		let itemCart = []
		cartDetail.filter((cart) => ({
			...cart,
			...product.filter((pro) => {
				if (cart.proID == pro.proId && cart.cartID === cartId) {
					const tmp = {
						...pro,
						quantity: cart.quantity,
						cost: cart.cost,
						cartDID: cart.cartDID,
					}
					itemCart.push(tmp)
				}
			}),
		}))
		return itemCart
	}, [product, cartDetail.length])

	return (
		<CartAndProductContext.Provider
			value={{
				product,
				setProduct,
				cartDetail,
				setCartDetail,
				cart,
				itemCart,
				userId,
				token,
				cartId,
				isLoading,
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
