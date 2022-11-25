import React, { useState } from "react"
import { Link, useNavigate } from "react-router-dom"
import { forgotPassword } from "../../services"
import { useForm } from "react-hook-form"

export default function ForgotPassword() {
	const {
		register,
		handleSubmit,
		formState: { errors },
	} = useForm()
	const [checkEmail, setCheckEmail] = useState(false)
	const navigate = useNavigate()

	const handleForgotPassword = (data) => {
		const tmpData = {
			...data,
			link_website: "http://localhost:3000/reset-password",
		}
		forgotPassword(tmpData)
			.then((res) => navigate("/reset-password"))
			.catch((e) => setCheckEmail(true))
	}
	return (
		<>
			<form
				style={{
					minHeight: "538px",
					fontSize: "24px",
					fontWeight: "700",
				}}
				onSubmit={handleSubmit(handleForgotPassword)}
			>
				<div className="mb-3 d-flex" style={{
					position: 'absolute',
					top: '30%'
				}}>
					<label className="form-label" htmlFor="exampleInputEmail1">
						Email address
					</label>
					<input
						className="mx-2"
						id="exampleInputEmail1"
						type="text"
						aria-describedby="emailHelp"
						autoFocus
						{...register("gmail", {
							pattern: {
								value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
								message: "invalid email address",
							},
						})}
					/>
					<button className="btn btn-primary me-3" type="submit">
						Submit
					</button>
				</div>
			</form>
			{errors.gmail && <p className="text-danger">Invalid gmail</p>}
			{checkEmail && <p className="text-danger">Not have email in system</p>}
		</>
	)
}
