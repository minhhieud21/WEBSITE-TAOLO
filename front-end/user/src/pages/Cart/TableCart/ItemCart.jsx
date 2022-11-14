import React, { useState, useContext } from "react"
import { Link } from "react-router-dom"
import { deleteCartDetailByCartDetailId } from "services"
import { updateCartDetail } from "services"
import img from "../../../assets/img/product-1.jpg"
import * as cartService from "../../../services/CartService"
import { CartAndProductContext } from "layouts/MainLayout/ContainerMainLayout"

const ItemCart = () => {
	const { product, tmp } = useContext(CartAndProductContext)
	const [quantity, setQuantity] = useState(1)
	const handleUpdateCart = (cartDID) => {
		updateCartDetail(cartDID, quantity)
			.then((res) => console.log(res))
			.catch((e) => console.log(e))
	}
	const handleDeleteCartDetail = async (cartDID) => {
		if (window.confirm(`Delete item ?`)) {
			await deleteCartDetailByCartDetailId(cartDID)
		}
	}

	const convertNumber = (a, b) => {
		return Number(a) + Number(b)
	}

	return (
		<>
			{tmp &&
				tmp.map((cartDetail) => {
					return (
						<tr key={cartDetail.cartDID}>
							<th className="ps-0 py-3 border-light" scope="row">
								<div className="d-flex align-items-center">
									<Link
										className="reset-anchor d-block animsition-link"
										to={`/detail/${cartDetail.proID}`}
									>
										<img src={img} alt="..." width={70} />
									</Link>
									<div className="ms-3">
										<strong className="h6">
											<Link
												className="reset-anchor animsition-link"
												to={`/detail/${cartDetail.proID}`}
											>
												abc
											</Link>
										</strong>
									</div>
								</div>
							</th>
							<td className="p-3 align-middle border-light">
								<p className="mb-0 small">{cartDetail.cost}</p>
							</td>
							<td className="p-3 align-middle border-light">
								<div className="border d-flex align-items-center justify-content-between px-3">
									<span className="small text-uppercase text-gray headings-font-family">
										Quantity
									</span>
									<div className="quantity">
										<button className="dec-btn p-0">
											<i
												className="fas fa-caret-left"
												onClick={() => {
													handleUpdateCart(cartDetail.cartDID)
													setQuantity(...Number(cartDetail.quantity) - 1)
												}}
											/>
										</button>
										<input
											className="form-control form-control-sm border-0 shadow-0 p-0"
											type="text"
											value={cartDetail.quantity}
											onChange={() => setQuantity(quantity)}
										/>
										<button className="inc-btn p-0">
											<i
												className="fas fa-caret-right"
												onClick={() => {
													handleUpdateCart(cartDetail.cartDID)
													setQuantity(...Number(cartDetail.quantity) + 1)
												}}
											/>
										</button>
									</div>
								</div>
							</td>
							<td className="p-3 align-middle border-light">
								<p className="mb-0 small">
									{cartDetail.quantity * cartDetail.cost}
								</p>
							</td>
							<td className="p-3 align-middle border-light">
								<i
									className="fas fa-trash-alt small text-muted "
									role="button"
									onClick={() => handleDeleteCartDetail(cartDetail.cartDID)}
								/>
							</td>
						</tr>
					)
				})}
		</>
	)
}

export default ItemCart
