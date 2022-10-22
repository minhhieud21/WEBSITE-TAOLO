import React from "react"
import { Link } from "react-router-dom"
export default function Category({ cateName, img }) {
	return (
		<div className="col-md-3">
			<Link className="category-item" to="/shop">
				<img className="img-fluid" src={img} alt={cateName} />
				<strong className="category-item-title">{cateName}</strong>
			</Link>
		</div>
	)
}
