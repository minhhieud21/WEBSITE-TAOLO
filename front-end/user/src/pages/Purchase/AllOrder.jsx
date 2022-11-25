import React, { useContext, useEffect, useMemo, useState } from "react"
import { CartAndProductContext } from "../../layouts/MainLayout/ContainerMainLayout"
import { formatVnd } from "../../services"
export default function AllOrder({ waitConfirm, confirmed }) {
	const { itemCart } = useContext(CartAndProductContext)
	const [allOrders, setAllOrders] = useState([])

	useEffect(() => {
	  setAllOrders([...waitConfirm, ...confirmed])
	
	}, [waitConfirm,confirmed])
	
	// const allOrders = useMemo(() => {
	// 	return [...waitConfirm, ...confirmed]
	// }, [itemCart])

	console.log(allOrders)
	return (
		<div
			className="tab-pane fade show active"
			id="all"
			role="tabpanel"
			aria-labelledby="all-tab"
		>
			<div className="p-4 p-lg-5 bg-white">
				<h6 className="text-uppercase">Wait for order</h6>
				<table className="table">
					<thead>
						<tr>
							<th scope="col">Name</th>
							<th scope="col">Phone</th>
							<th scope="col">Address</th>
							<th scope="col">Method Pay</th>
							<th scope="col">Description</th>
							<th scope="col">Total Quantity</th>
							<th scope="col">Total Cost</th>
							<th scope="col">Status</th>
						</tr>
					</thead>
					<tbody>
						{allOrders &&
							allOrders.map((item, index) => (
								<tr key={index}>
									<th scope="row">{item.name}</th>
									<td>{item.phone}</td>
									<td>{item.address}</td>
									<td>{item.methodPay}</td>
									<td>{item.description}</td>
									<td>{item.totalQuantity}</td>
									<td>{formatVnd(item.totalCost)}</td>
									<td className={item.cartID ? 'fw-bold text-danger' : 'fw-bold text-success'}>{item.cartID ? 'Wait for confirm': 'Confirm'}</td>

								</tr>
							))}
					</tbody>
				</table>
			</div>
		</div>
	)
}
