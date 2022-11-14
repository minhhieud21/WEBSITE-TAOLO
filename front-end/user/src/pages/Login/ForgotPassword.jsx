import React, { useState } from "react"
import { Link } from "react-router-dom"
import { forgotPassword } from "../../services"
import { useForm } from "react-hook-form"

export default function ForgotPassword() {
	const { register, handleSubmit } = useForm()

	const [email, setEmail] = useState("")

	console.log(email)
	const handleForgotPassword = (data) => {
		const tmpData = {
			...data,
			link_website: "http://localhost:3000/reset-password",
		}
		forgotPassword(tmpData)
			.then((res) => console.log(res))
			.catch((e) => console.log(e))
	}
	return (
		<>
			<form onSubmit={handleSubmit(handleForgotPassword)}>
				<div className="mb-3 d-flex">
					<label className="form-label" htmlFor="exampleInputEmail1">
						Email address
					</label>
					<input
						className="mx-2"
						id="exampleInputEmail1"
						type="email"
						aria-describedby="emailHelp"
						autoFocus
						{...register("gmail")}
					/>
					<button className="btn btn-primary me-3" type="submit">
						Submit
					</button>
				</div>
			</form>
		</>
	)
}
