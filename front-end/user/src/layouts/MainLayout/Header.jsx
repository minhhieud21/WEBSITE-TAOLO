import React, { useEffect, useState, useContext } from "react"
import { Link, useNavigate } from "react-router-dom"
import {
	checkLocalStorage,
	getLocalStorage,
	getCartDetailByCartID,
} from "../../services"
import Logout from "../../pages/Login/Logout"
import { CartAndProductContext } from "./ContainerMainLayout"
import { useForm } from "react-hook-form"

const Header = () => {
	const { itemCart } = useContext(CartAndProductContext)
	const navigate = useNavigate()
	const { register, handleSubmit, getValues, setValue } = useForm()
	const checkLogin = checkLocalStorage("username")

	
	const handleSearchProduct = () => {
		const searchValue = getValues("searchValue")
		navigate(`/shop?name=${searchValue}`)
	}

	return (
		<>
			<header className="header bg-white">
				<div className="container px-lg-3">
					<nav className="navbar navbar-expand-lg navbar-light py-3 px-lg-0">
						<button
							className="navbar-toggler navbar-toggler-end"
							type="button"
							data-bs-toggle="collapse"
							data-bs-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent"
							aria-expanded="false"
							aria-label="Toggle navigation"
						>
							<span className="navbar-toggler-icon" />
						</button>
						<div
							className="collapse navbar-collapse"
							id="navbarSupportedContent"
						>
							<ul className="navbar-nav me-auto">
								<li className="nav-item">
									{/* Link*/}
									<Link className="nav-link" to="/">
										Taolo
									</Link>
								</li>
							</ul>
							<div className="navbar-nav  me-auto ms-auto w-50">
								<form
									onSubmit={handleSubmit(handleSearchProduct)}
									className="position-relative d-flex align-items-center w-100"
								>
									<input
										type="text"
										placeholder="Search..."
										className="w-100"
										{...register("searchValue")}
									/>
									<i
										className="fa fa-search position-absolute end-0"
										role="button"
									></i>
								</form>
							</div>
							<ul className="navbar-nav ms-auto">
								{checkLogin ? (
									<li className="nav-item pt-2">
										<Link className="nav-link" to="/cart">
											<i className="fas fa-dolly-flatbed me-1 text-gray" />
											Cart
											<small className="text-gray fw-normal">
												({itemCart.length})
											</small>
										</Link>
									</li>
								) : (
									""
								)}

								<li className="nav-item">
									<Link className="nav-link" to="/login">
										{checkLogin ? (
											<Logout username={getLocalStorage("username")} />
										) : (
											<div className="pt-2">
												<i className="fas fa-user me-1 text-gray fw-normal" />
												Login
											</div>
										)}
									</Link>
								</li>
							</ul>
						</div>
					</nav>
				</div>
			</header>
		</>
	)
}

export default Header
