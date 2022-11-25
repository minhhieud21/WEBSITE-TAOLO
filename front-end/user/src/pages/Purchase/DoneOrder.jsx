import React, { useContext, useEffect, useMemo, useState } from "react"
import { CartAndProductContext } from "../../layouts/MainLayout/ContainerMainLayout"
import {
	formatVnd,
	getBillByAccID,
	getBillDetailByBillId,
	setLocalStorage,
} from "../../services"

export default function DoneOrder({bill}) {

	return (
		<div
			className="tab-pane fade active"
			id="confirmed"
			role="tabpanel"
			aria-labelledby="confirmed"
		>
			<div className="p-4 p-lg-5 bg-white">
				<h6 className="text-uppercase">Done</h6>
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
						</tr>
					</thead>
					<tbody>
						{bill &&
						bill.map((item,index) => 
							<tr key={index}>
								<th scope="row">{item.name}</th>
								<td>{item.phone}</td>
								<td>{item.address}</td>
								<td>{item.methodPay}</td>
								<td>{item.description}</td>
								<td>{item.totalQuantity}</td>
								<td>{formatVnd(item.totalCost)}</td>
							</tr>
						)}
					</tbody>
				</table>
			</div>
		</div>
	)
}
