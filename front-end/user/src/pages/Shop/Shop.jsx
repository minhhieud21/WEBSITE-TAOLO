import React, { useState, useEffect, useContext } from "react"
import Breadcrumb from "../../components/Breadcrumb/Breadcrumb"
import ListProduct from "./ListProduct/ListProduct"
import SideBar from "./SideBar/SideBar"
import { useLocation } from "react-router-dom"
import {
	getAllCategories,
	getProductByCateId,
	searchProductByName,
} from "../../services"
import { CartAndProductContext } from "../../layouts/MainLayout/ContainerMainLayout"
const Shop = () => {
	const search = useLocation().search
	const cateId = new URLSearchParams(search).get("cateId")
	const searchValue = new URLSearchParams(search).get("name")
	const { product } = useContext(CartAndProductContext)
	const [products, setProducts] = useState([])
	const [categories, setCategories] = useState([])

	useEffect(() => {
		getAllCategories()
			.then((res) => {
				setCategories(res.data)
			})
			.catch((e) => console.log(e))

		fetchProduct(cateId, searchValue)
	}, [cateId, searchValue])

	const fetchProduct = (cateId, searchValue) => {
		try {
			/**
			 * filter with category
			 */
			if (cateId && !searchValue) {
				getProductByCateId(cateId).then((res) => {
					setProducts(res.data.data)
				})
			} 
			/**
			 * search with product name
			 */
			else {
				searchProductByName(searchValue).then((res) => {
					setProducts(res.data.data)
				})
			}
		} catch (error) {
			
		}
	}

	return (
		<>
			<div className="container">
				{/* HERO SECTION*/}
				<Breadcrumb name="Shop" />
				<section className="py-5">
					<div className="container p-0">
						<div className="row">
							{/* SHOP SIDEBAR*/}
							<SideBar categories={categories} cateId={cateId} />
							{/* SHOP LISTING*/}
							<ListProduct products={products} />
						</div>
					</div>
				</section>
			</div>
		</>
	)
}

export default Shop
