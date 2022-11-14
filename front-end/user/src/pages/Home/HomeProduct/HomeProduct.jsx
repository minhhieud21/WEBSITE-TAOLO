import React, { useContext } from "react"
import ItemProduct from "../../../components/ItemProduct/ItemProduct"
import { CartAndProductContext } from "layouts/MainLayout/ContainerMainLayout"

const HomeProduct = () => {
	const { product } = useContext(CartAndProductContext)

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
					{product &&
						product.map((product) => (
							<ItemProduct
								key={product.proId}
								proId={product.proId}
								proName={product.proName}
								price={product.price}
								img={product.image}
							/>
						))}
				</div>
			</section>
		</>
	)
}

export default HomeProduct
