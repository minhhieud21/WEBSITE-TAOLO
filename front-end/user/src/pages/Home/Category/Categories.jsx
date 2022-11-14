import React, { useEffect, useState } from "react"
import img from "../../../assets/img/cat-img-1.jpg"
import img1 from "../../../assets/img/cat-img-2.jpg"
import { Link } from "react-router-dom"
import { getAllCategories,getProductByCateId } from "../../../services/CategoryService"

import Category from "./Category"
const Categories = ({ cateName }) => {
	const [categories, setCategories] = useState([])
	useEffect(() => {
		getAllCategories()
			.then((res) => {
				setCategories(res.data)
			})
			.catch((e) => console.log(e))
	}, [])
	
	return (
		<>
			<section className="pt-5">
				<header className="text-center">
					<p className="small text-muted small text-uppercase mb-1">
						Carefully created collections
					</p>
					<h2 className="h5 text-uppercase mb-4">Browse our categories</h2>
				</header>
				<div className="row">
					{categories.map((category) => (
						<Category
							img={img}
							id={category.cateID}
							key={category.cateID}
							cateName={category.cateName}
						/>
					))}
				</div>
			</section>
			
			
		</>
	)
}

export default Categories
