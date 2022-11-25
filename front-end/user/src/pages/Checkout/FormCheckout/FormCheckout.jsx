import React, { useState } from "react"
import { useForm } from "react-hook-form"
import { useNavigate } from "react-router-dom"
import { getLocalStorage, readyCheckout } from "../../../services"
import { PopUpFail } from "../../../components/Popup/PopupFail"
import { useContext } from "react"
import { CartAndProductContext } from "../../../layouts/MainLayout/ContainerMainLayout"

const FormCheckout = () => {
	const {
		register,
		handleSubmit,
		formState: { errors },
	} = useForm()
	const navigate = useNavigate()
	const [methodPay, setMethodPay] = useState("cod")
	const cartID = getLocalStorage("cartId")
	const {token} = useContext(CartAndProductContext)
	
	const onSubmitCheckout = (data) => {
		const infoCheckout = { ...data, methodPay, cartID }
		readyCheckout(infoCheckout,token)
			.then((res) => navigate("/"))
			.catch((e) => console.log(e))
	}

	return (
		<>
			<div className="col-lg-8">
				<form onSubmit={handleSubmit(onSubmitCheckout)}>
					<div className="row gy-3">
						<div className="col-lg-12">
							<label
								className="form-label text-sm text-uppercase"
								htmlFor="firstName"
							>
								Full Name{" "}
							</label>
							<input
								className="form-control form-control-lg"
								{...register("name", { required: true })}
								type="text"
								id="firstName"
								placeholder="Enter your full name"
							/>
						</div>
						{errors.name?.type === "required" && (
							<p className="text-danger">Name is required</p>
						)}
						<div className="col-lg-12">
							<label
								className="form-label text-sm text-uppercase"
								htmlFor="email"
							>
								Address{" "}
							</label>
							<input
								{...register("address", { required: true })}
								className="form-control form-control-lg"
								type="text"
								id="email"
								placeholder="Address"
							/>
						</div>
						{errors.address?.type === "required" && (
							<p className="text-danger">Address is required</p>
						)}
						<div className="col-lg-6">
							<label
								className="form-label text-sm text-uppercase"
								htmlFor="phone"
							>
								Phone number{" "}
							</label>
							<input
								{...register("phone", { required: true, minLength: 10 })}
								className="form-control form-control-lg"
								type="tel"
								id="phone"
							/>
						</div>

						<div className="col-lg-6 form-group">
							<label
								className="form-label text-sm text-uppercase"
								htmlFor="countryAlt"
							>
								Pay-method
							</label>
							<div className="d-flex">
								<div className="form-check me-3">
									<input
										role="button"
										className="form-check-input"
										type="radio"
										value="cod"
										id="cod"
										name="pay-method"
										defaultChecked
										onClick={(e) => setMethodPay(e.target.value)}
									/>
									<label
										role="button"
										className="form-check-label"
										htmlFor="cod"
									>
										COD
									</label>
								</div>
								<div className="form-check">
									<input
										onClick={(e) => setMethodPay(e.target.value)}
										className="form-check-input"
										type="radio"
										value="momo"
										id="momo"
										name="pay-method"
										role="button"
									/>
									<label
										role="button"
										className="form-check-label"
										htmlFor="momo"
									>
										Momo
									</label>
								</div>
							</div>
						</div>
					</div>
					{errors.phone?.type === "required" && (
						<p className="text-danger">Phone is required</p>
					)}{" "}
					{errors.phone?.type === 'minLength' && (
						<p className="text-danger">Phone is 10 digits</p>
					)}
					<div className="col-lg-12 form-group">
						<label
							className="form-label text-sm text-uppercase"
							htmlFor="phone"
						>
							Description{" "}
						</label>
						<textarea
							{...register("description")}
							className="form-control form-control-lg"
							type="tel"
							id="phone"
							placeholder="description"
						/>
					</div>
					<div className="col-lg-12 form-group mt-3">
						<button
							className="btn btn-primary text-white text-uppercase fw-bold"
							type="submit"
						>
							Checkout
						</button>
					</div>
				</form>
			</div>
		</>
	)
}

export default FormCheckout
