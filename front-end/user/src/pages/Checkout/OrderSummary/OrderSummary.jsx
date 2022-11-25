import React from "react"
import { useContext } from "react"
import { CartAndProductContext } from "../../../layouts/MainLayout/ContainerMainLayout"
import { formatVnd } from "../../../services"
const OrderSummary = () => {
	const { itemCart, cart } = useContext(CartAndProductContext)

	return (
		<>
			<div className="col-lg-4">
				<div className="card border-0 rounded-0 p-lg-4 bg-light">
					<div className="card-body">
						<h5 className="text-uppercase mb-4">Your order</h5>
						<ul className="list-unstyled mb-0">
							{itemCart &&
								itemCart.map((item) => (
									<li className="d-flex align-items-center justify-content-between border-bottom my-2">
										<strong className="small fw-bold">{item.proName}</strong>
										<span className="text-muted small">
											{formatVnd(item.cost)}
										</span>
										<hr />
									</li>
								))}
							<li className="d-flex align-items-center justify-content-between">
								<strong className="text-uppercase small fw-bold">Total</strong>
								<span>{formatVnd(cart.totalCost)}</span>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</>
	)
}

export default OrderSummary
