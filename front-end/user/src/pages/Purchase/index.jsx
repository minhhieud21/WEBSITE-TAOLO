import React from "react"
import AllOrder from "./AllOrder"
import DoneOrder from "./DoneOrder"
import ShippingOrder from "./ShippingOrder"
import WaitForConfirmOrder from "./WaitForConfirmOrder"
import WaitForPickupOrder from "./WaitForPickUpOrder"

export default function Purchase() {
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
						Tất cả
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
						Chờ xác nhận
					</a>
				</li>
				<li className="nav-item">
					<a
						className="nav-link text-uppercase"
						id="reviews-tab"
						data-bs-toggle="tab"
						href="#wait-for-pick-up"
						role="tab"
						aria-controls="wait-for-pick-up"
						aria-selected="false"
					>
						Chờ lấy hàng
					</a>
				</li>
				<li className="nav-item">
					<a
						className="nav-link text-uppercase"
						id="shipping-tab"
						data-bs-toggle="tab"
						href="#shipping"
						role="tab"
						aria-controls="shipping"
						aria-selected="false"
					>
						Đang giao
					</a>
				</li>
				<li className="nav-item">
					<a
						className="nav-link text-uppercase"
						id="done-tab"
						data-bs-toggle="tab"
						href="#done"
						role="tab"
						aria-controls="done"
						aria-selected="false"
					>
						Đã giao
					</a>
				</li>
			</ul>

			<div className="tab-content mb-5" id="myTabContent" style={{minHeight:'800px'}}>
				<AllOrder />
				<WaitForConfirmOrder />
				<WaitForPickupOrder />
				<ShippingOrder />
				<DoneOrder />
			</div>
		</>
	)
}
