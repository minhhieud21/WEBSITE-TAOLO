import React from "react"
import { useEffect, useState } from "react"
import { Link, useParams } from "react-router-dom"
import {
	decreaseQuantity,
	increaseQuantity,
	formatVnd,
	getLocalStorage,
	addCart,
	setLocalStorage,
	getCartByUserId,
} from "../../../services"

import { ToastContainer, toast } from "react-toastify"
import "react-toastify/dist/ReactToastify.css"
import { useContext } from "react"
import { ProductDetailProvider } from "../ProductDetail"

const Detail = () => {
	const [quantity, setQuantity] = useState(1)
	const [success, setSuccess] = useState(true)
	const [isLogged, setIsLogged] = useState(false)
	const userId = getLocalStorage("userId")
	const [des, setDes] = useState([])

	const { productDetail, token } = useContext(ProductDetailProvider)
	const { proId } = useParams()

	useEffect(() => {
		if (getLocalStorage("username")) {
			setIsLogged(true)
		}
	}, [])

	const notifySuccess = () => {
		toast.success("Thêm giỏ hàng thành công!", {
			position: toast.POSITION.TOP_RIGHT,
		})
	}

	const notifyFail = () => {
		toast.error(" Thêm giỏ hàng thất bại!", {
			position: toast.POSITION.TOP_RIGHT,
		})
	}

	const addToCart = async () => {
		if (token) {
			const data = {
				accID: userId,
				proID: proId,
				quantity: quantity,
			}
			addCart(data, token)
				.then((res) => {
					getCartByUserId(userId, token)
						.then((res) => {
							setLocalStorage("cartId", res.data.data.cartID)
						})
						.catch((e) => e)
				})
				.catch((e) => setSuccess(false))
			if (success) {
				notifySuccess()
			} else {
				notifyFail()
			}
		}
	}

	return (
		<>
			<div className="col-lg-6">
				<h1>{productDetail.proName}</h1>
				<p className="text-muted lead">{formatVnd(productDetail.price)}</p>
				<p>{productDetail.description}</p>
				{/* <ul>
					{des.map((de, index) => (
						<li key={index}>{de}</li>
					))}
				</ul> */}
				<p className="text-sm mb-4"></p>
				{productDetail.quantity && productDetail.quantity > 0 ? (
					<div className="row align-items-stretch mb-4">
						<div className="col-sm-5 pr-sm-0">
							<div className="border d-flex align-items-center justify-content-between py-1 px-3 bg-white border-white">
								<span className="small text-uppercase text-gray mr-4 no-select">
									Quantity
								</span>
								<div className="quantity">
									<button className="dec-btn p-0">
										<i
											className="fas fa-caret-left"
											onClick={() => {
												return setQuantity(decreaseQuantity(quantity))
											}}
										/>
									</button>
									<input
										className="form-control border-0 shadow-0 p-0"
										type="text"
										value={quantity}
										onChange={() => setQuantity(quantity)}
									/>
									<button className="inc-btn p-0">
										<i
											className="fas fa-caret-right"
											onClick={() => {
												return setQuantity(increaseQuantity(quantity))
											}}
										/>
									</button>
								</div>
							</div>
						</div>
						<div className="col-sm-3 pl-sm-0">
							<Link
								onClick={addToCart}
								className="btn btn-primary text-white text-uppercase btn-sm btn-block h-100 d-flex align-items-center justify-content-center px-0"
								to={isLogged ? "#" : "/login"}
							>
								Add to cart
							</Link>
						</div>
					</div>
				) : (
					<div className="d-flex justify-content-center fs-1 text-danger text-uppercase">
						Out of stock!
					</div>
				)}
				<br />
				<ToastContainer autoClose={50} />
			</div>
		</>
	)
}

export default Detail
