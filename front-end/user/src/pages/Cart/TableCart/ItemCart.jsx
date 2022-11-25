import React, { useState, useContext, useEffect } from "react"
import { Link } from "react-router-dom"
import {
	deleteCartDetailByCartDetailId,
	updateCartDetail,
	formatVnd,
} from "../../../services"
import { CartAndProductContext } from "../../../layouts/MainLayout/ContainerMainLayout"
import Loading from "../../../components/Loading/Loading"

const ItemCart = () => {
	const { itemCart, token, userId } = useContext(CartAndProductContext)
	const [quantity, setQuantity] = useState(1)

	const handleUpdateCart = async (cartDID, quantity) => {
		updateCartDetail(cartDID, quantity, token)
		setQuantity(quantity)
	}

	const handleDeleteCartDetail = async (cartDID,name) => {
		if (window.confirm(`Delete ${name}?`)) {
			await deleteCartDetailByCartDetailId(cartDID)
		}
	}

	return (
		<>
			{!itemCart ? (
				<Loading />
			) : (
				itemCart.map((cartDetail) => {
					return (
						<tr key={cartDetail.cartDID}>
							<th className="ps-0 py-3 border-light" scope="row">
								<div className="d-flex align-items-center">
									<Link
										className="reset-anchor d-block animsition-link"
										to={`/detail/${cartDetail.proID}`}
									>
										<img
											src={`/Image/${cartDetail.proId}/${cartDetail.image}`}
											alt="..."
											width={70}
										/>
									</Link>
									<div className="ms-3">
										<strong className="h6">
											<Link
												className="reset-anchor animsition-link w-50"
												to={`/detail/${cartDetail.proId}`}
											>
												{cartDetail.proName}
											</Link>
										</strong>
									</div>
								</div>
							</th>
							<td className="p-3 align-middle border-light">
								<p className="mb-0 small">{formatVnd(cartDetail.cost)}</p>
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
													handleUpdateCart(
														cartDetail.cartDID,
														cartDetail.quantity - 1
													)
												}}
											/>
										</button>
										<input
											className="form-control form-control-sm border-0 shadow-0 p-0"
											type="text"
											value={cartDetail.quantity}
											onChange={(e) => console.log(e)}
										/>
										<button className="inc-btn p-0">
											<i
												className="fas fa-caret-right"
												onClick={(e) => {
													handleUpdateCart(
														cartDetail.cartDID,
														cartDetail.quantity + 1
													)
												}}
											/>
										</button>
									</div>
								</div>
							</td>
							<td className="p-3 align-middle border-light">
								<p className="mb-0 small">
									{formatVnd(cartDetail.quantity * cartDetail.cost)}
								</p>
							</td>
							<td className="p-3 align-middle border-light">
								<i
									className="fas fa-trash-alt small text-muted "
									role="button"
									onClick={() => handleDeleteCartDetail(cartDetail.cartDID,cartDetail.proName)}
								/>
							</td>
						</tr>
					)
				})
			)}
		</>
	)
}

export default ItemCart
