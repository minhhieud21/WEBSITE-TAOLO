import React from "react"
import { Link } from "react-router-dom"
export default function Category({ cateName, img, id }) {
	return (
		<div className="col">
			<Link className="category-item" to={`/shop?cateName=${cateName}&cateId=${id}`} >
				<img className="img-fluid" src={img} alt={cateName} />
				<strong className="category-item-title">{cateName}</strong>
			</Link>
			
		</div>
	)
}
