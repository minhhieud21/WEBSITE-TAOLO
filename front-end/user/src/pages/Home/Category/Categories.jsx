import React, { useEffect, useState,useMemo } from "react"
import { getAllCategories,getProductByCateId } from "../../../services/CategoryService"
import imac from '../../../assets/img/category/imac.png'
import macMini from '../../../assets/img/category/mac_mini.jpeg'
import macbookAir from '../../../assets/img/category/macbook_air.jpg'
import macbookPro from '../../../assets/img/category/macbook_pro.png'

import Category from "./Category"
const Categories = ({ cateName }) => {
	const [categories, setCategories] = useState([])
	const arrImg = [imac,macMini,macbookAir,macbookPro]
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
				<div className="row" style={{
					alignItems: 'center'
				}}>
					{categories.map((category,index) => (
						<Category
							img={arrImg[index]}
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
