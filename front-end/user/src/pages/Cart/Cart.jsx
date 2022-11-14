import React, { useEffect, useState, createContext } from "react"
import Breadcrumb from "../../components/Breadcrumb/Breadcrumb"
import { getLocalStorage } from "../../services"
import OrderTotal from "./OrderTotal/OrderTotal"
import CartTable from "./TableCart/CartTable"
import { useNavigate } from "react-router-dom"

export const CartContext = createContext()

const Cart = () => {
	const navigate = useNavigate()
	const isLogin = getLocalStorage("username")

	useEffect(() => {
		if (!isLogin) navigate("/login")
	}, [isLogin])

	return (
	
			<div>
				{/* HERO SECTION*/}
				<Breadcrumb name="Cart" />
				<section className="py-5">
					<h2 className="h5 text-uppercase mb-4">Shopping cart</h2>
					<div className="row">
						<CartTable />
						{/* ORDER TOTAL*/}
						<OrderTotal />
					</div>
				</section>
			</div>
	)
}

export default Cart
