import React from "react"
import { useForm } from "react-hook-form"
import { resetPassword } from "../../services"

export default function ResetPassword() {
	const { register, handleSubmit } = useForm()
	const cookie = document.cookie

	const handleResetPassword = async (data) => {
		await resetPassword(data, cookie)
	}
	return (
		<>
			<div className="card mb-4" id="forms">
				<div className="card-header">Login</div>
				<div className="card-body">
					<form onSubmit={handleSubmit(handleResetPassword)}>
						<div className="mb-3">
							<label className="form-label" htmlFor="exampleInputEmail1">
								Verified code
							</label>
							<input
								className="form-control"
								id="exampleInputEmail1"
								type="text"
								aria-describedby="emailHelp"
								autoFocus
								{...register("Verification_code")}
							/>
						</div>
						<div className="mb-3">
							<label className="form-label" htmlFor="exampleInputEmail1">
								New password
							</label>
							<input
								className="form-control"
								id="exampleInputEmail2"
								type="text"
								aria-describedby="emailHelp"
								{...register("password_new")}
							/>
						</div>
						<div className="mb-3">
							<label className="form-label" htmlFor="exampleInputEmail1">
								Confirm password
							</label>
							<input
								className="form-control"
								id="exampleInputEmail3"
								type="text"
								aria-describedby="emailHelp"
								{...register("password_confirm")}
							/>
						</div>
						<button className="btn btn-primary me-3" type="submit">
							Submit
						</button>
					</form>
				</div>
			</div>
		</>
	)
}
