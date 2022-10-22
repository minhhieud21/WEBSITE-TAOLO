import React from "react"
import { useEffect } from "react"
import { useState } from "react"
import ItemProduct from "../../../components/ItemProduct/ItemProduct"
import { getAllProduct } from "../../../services"
const HomeProduct = () => {
	const [products, setProducts] = useState([])

	useEffect(() => {
		getAllProduct().then((res) => {
			setProducts(res.data.content)
		})
	}, [])

	return (
		<>
			<section className="py-5">
				<header>
					<p className="small text-muted small text-uppercase mb-1">
						Made the hard way
					</p>
					<h2 className="h5 text-uppercase mb-4">Top trending products</h2>
				</header>
				<div className="row">
					{products.map((product) => (
						<ItemProduct
							key={product.proId}
							proId={product.proId}
							proName={product.proName}
							price={product.price}
						/>
					))}
				</div>
			</section>
		</>
	)
}

export default HomeProduct
