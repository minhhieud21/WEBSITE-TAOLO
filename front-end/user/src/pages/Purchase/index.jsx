import React, { useEffect, useState } from "react"
import { useContext } from "react"
import { CartAndProductContext } from "../../layouts/MainLayout/ContainerMainLayout"
import { getBillByAccID, getCartReadyCheckOutByAccID } from "../../services"
import AllOrder from "./AllOrder"
import DoneOrder from "./DoneOrder"
import ShippingOrder from "./ShippingOrder"
import WaitForConfirmOrder from "./WaitForConfirmOrder"
import WaitForPickupOrder from "./WaitForPickUpOrder"

export default function Purchase() {
	const { token, userId, product,itemCart } = useContext(CartAndProductContext)
	const [bill, setBill] = useState([])
	const [waitConfirm, setWaitConfirm] = useState([])

	useEffect(() => {
		getBillByAccID(userId, token).then((res) => {
			setBill(res.data.data)
		})
		getCartReadyCheckOutByAccID(userId,token).then(res =>{
			setWaitConfirm(res.data.data)
		})
	}, [userId])
	

	return (
		<>
			<ul className="nav nav-tabs border-0 d-flex justify-content-around" id="myTab" role="tablist">
				<li className="nav-item">
					<a
						className="nav-link text-uppercase active"
						id="description-tab"
						data-bs-toggle="tab"
						href="#all"
						role="tab"
						aria-controls="all"
						aria-selected="true"
					>
						All orders
					</a>
				</li>
				<li className="nav-item">
					<a
						className="nav-link text-uppercase"
						id="reviews-tab"
						data-bs-toggle="tab"
						href="#wait-for-confirm"
						role="tab"
						aria-controls="wait-for-confirm"
						aria-selected="false"
					>
						Wait for confirm
					</a>
				</li>
				<li className="nav-item">
					<a
						className="nav-link text-uppercase"
						id="shipping-tab"
						data-bs-toggle="tab"
						href="#confirmed"
						role="tab"
						aria-controls="confirmed"
						aria-selected="false"
					>
						Confirmed
					</a>
				</li>
			</ul>

			<div className="tab-content mb-5" id="myTabContent" style={{minHeight:'800px'}}>
				<AllOrder waitConfirm={waitConfirm} confirmed={bill} />
				<WaitForConfirmOrder itemCart={waitConfirm} />
				<DoneOrder bill={bill} />
			</div>
		</>
	)
}
