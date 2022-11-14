import React from "react"
import { Link } from "react-router-dom"

const SideBar = ({ categories, cateId }) => {
	return (
		<>
			<div className="col-lg-3 order-2 order-lg-1">
				<h5 className="text-uppercase mb-4">Categories</h5>
				<div className="py-2 px-4 bg-dark text-white mb-3">
					<strong className="small text-uppercase fw-bold">Taolo shop</strong>
				</div>
				<ul className="list-unstyled small text-muted ps-lg-4 font-weight-normal">
					{categories
						? categories.map((cate) => {
								if (cateId === cate.cateID) {
									return (
										<li key={cate.cateID} className="mb-2 text-primary">
											<Link
												key={cate.cateID}
												className="reset-anchor"
												to={`/shop?cateName=${cate.cateName}&cateId=${cate.cateID}`}
											>
												{cate.cateName}
											</Link>
										</li>
									)
								} else {
									return (
										<li key={cate.cateID} className="mb-2">
											<Link
												key={cate.cateID}
												className="reset-anchor"
												to={`/shop?cateName=${cate.cateName}&cateId=${cate.cateID}`}
											>
												{cate.cateName}
											</Link>
										</li>
									)
								}
						  })
						: ""}
				</ul>
			</div>
		</>
	)
}

export default SideBar
