import React, { useCallback, useState } from "react"
import Popup from "../Popup"
import { Link } from "react-router-dom"
import { formatVnd } from "../../services"

const ItemProduct = ({ proName, price, proId, img }) => {
	const [popup, setPopup] = useState(false)
	const openPopup = useCallback(() => {
		setPopup(!popup)
	}, [])
	
	return (
		<>
			<Popup />
			<div className="col-xl-3 col-lg-4 col-sm-6">
				<div className="product text-center">
					<div className="position-relative mb-3">
						<Link className="d-block" to={`/detail/${proId}`}>
							<img className="img-fluid w-100" src={`/Image/${proId}/${img}`} alt="img" />
						</Link>
						<div className="product-overlay">
							<ul className="mb-0 list-inline">
								<li className="list-inline-item m-0 p-0">
									<Link className="btn btn-sm btn-outline-dark" to="#!">
										<i className="far fa-heart" />
									</Link>
								</li>
								<li className="list-inline-item m-0 p-0">
									<Link className="btn btn-sm btn-dark" to="/cart">
										Add to cart
									</Link>
								</li>
								<li
									className="list-inline-item me-0"
									onClick={() => {
										openPopup()
									}}
								>
									<Link
										className="btn btn-sm btn-outline-dark"
										to="#productView"
										data-bs-toggle="modal"
									>
										<i className="fas fa-expand" />
									</Link>
								</li>
							</ul>
						</div>
					</div>
					<h6>
						{" "}
						<Link className="reset-anchor" to={`/detail/${proId}`}>
							{proName}
						</Link>
					</h6>
					<p className="small text-muted">{price}</p>
				</div>
			</div>
		</>
	)
}

export default ItemProduct
