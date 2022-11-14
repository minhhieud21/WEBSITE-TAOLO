import React from "react"
import { Link } from "react-router-dom"
import ItemCart from "./ItemCart"

const CartTable = () => {
	return (
		<>
			<div className="col-lg-8 mb-4 mb-lg-0">
				{/* CART TABLE*/}
				<div className="table-responsive mb-4">
					<table className="table text-nowrap">
						<thead className="bg-light">
							<tr>
								<th className="border-0 p-3" scope="col">
									{" "}
									<strong className="text-sm text-uppercase">Product</strong>
								</th>
								<th className="border-0 p-3" scope="col">
									{" "}
									<strong className="text-sm text-uppercase">Price</strong>
								</th>
								<th className="border-0 p-3" scope="col">
									{" "}
									<strong className="text-sm text-uppercase">Quantity</strong>
								</th>
								<th className="border-0 p-3" scope="col">
									{" "}
									<strong className="text-sm text-uppercase">Total</strong>
								</th>
								<th className="border-0 p-3" scope="col">
									{" "}
									<strong className="text-sm text-uppercase" />
								</th>
							</tr>
						</thead>
						<tbody className="border-0">
							<ItemCart />
						</tbody>
					</table>
				</div>
				{/* CART NAV*/}
				<div className="bg-light px-4 py-3">
					<div className="row align-items-center text-center">
						<div className="col-md-6 mb-3 mb-md-0 text-md-start">
							<Link className="btn btn-link p-0 text-dark btn-sm" to="/">
								<i className="fas fa-long-arrow-alt-left me-2"> </i>
								Continue shopping
							</Link>
						</div>
						<div className="col-md-6 text-md-end">
							<Link className="btn btn-outline-dark btn-sm" to="/checkout">
								Procceed to checkout
								<i className="fas fa-long-arrow-alt-right ms-2" />
							</Link>
						</div>
					</div>
				</div>
			</div>
		</>
	)
}

export default CartTable
