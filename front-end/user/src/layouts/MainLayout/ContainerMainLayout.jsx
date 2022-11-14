import React, { useEffect, useState, createContext } from "react"
import Footer from "./Footer"
import Header from "./Header"
import { getCartDetailByCartID, getAllProduct, getLocalStorage } from "../../services"

export const CartAndProductContext = createContext()

const ContainerMainLayout = ({ children }) => {
	const [product, setProduct] = useState([])
	const [tmp, setTmp] = useState([])
	const [cartDetail, setCartDetail] = useState([])
	const token = getLocalStorage('token')

	useEffect(() => {
		getCartDetailByCartID("C003").then((res) => {
			setTmp(res.data)
		})
		getAllProduct().then((res) => {
			setProduct(res.data)
		})
	}, [])

	return (
		<CartAndProductContext.Provider
			value={{
				product,
				setProduct,
				tmp,
				setCartDetail,
				token
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
