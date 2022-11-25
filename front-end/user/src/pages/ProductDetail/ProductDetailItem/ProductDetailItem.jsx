import React from "react"
import { useContext } from "react"
import ItemProduct from "../../../components/ItemProduct/ItemProduct"
import { CartAndProductContext } from "../../../layouts/MainLayout/ContainerMainLayout"
import { formatVnd } from "../../../services"
const ProductDetailItem = () => {
	const { itemCart } = useContext(CartAndProductContext)
	return (
		<>
			<h2 className="h5 text-uppercase mb-4">Related products</h2>
			<div className="row">
				{itemCart &&
					itemCart.map((item) => (
						<ItemProduct
							proName={item.proName}
							price={formatVnd(item.cost)}
							proId={item.proId}
							img={item.image}
						/>
					))}
			</div>
		</>
	)
}

export default ProductDetailItem
