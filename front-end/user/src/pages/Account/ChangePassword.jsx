import React, { useState } from "react"
import { useForm } from "react-hook-form"
import { ErrorMessage } from "@hookform/error-message"
import { changePassword } from "../../services"
import { getLocalStorage } from "../../services/LocalStorageService"
import { useNavigate } from "react-router-dom"
// import '../../assets/style.scss'

export default function () {
	const {
		register,
		handleSubmit,
		getValues,
		formState: { errors },
	} = useForm({
		criteriaMode: "all",
	})
	const userId = getLocalStorage("userId")
	const [isPassShow, setIsPassShow] = useState(false)
	const [isNewPassShow, setIsNewPassShow] = useState(false)
	const [isConfirmPassShow, setConfirmPassShow] = useState(false)

	const navigate = useNavigate()

	const [changePassFail, setChangePassFail] = useState(false)


	const handleChangePassword = (data) => {
		const tmpData = {
			accID: 'US011',//userId,
			...data,
		}
		console.log(tmpData)
		changePassword(tmpData)
			.then((res) => {
				navigate("/")
			})
			.catch(e => setChangePassFail(true))
	}

	return (
		<>
			<form onSubmit={handleSubmit(handleChangePassword)}>
				<div className="mb-3">
					<label className="form-label" htmlFor="exampleInputEmail1">
						Current password
					</label>
					<div className="pass-word">
						<input
							{...register("oldpassword", {
								required: true,
							})}
							className="form-control"
							id="exampleInputEmail1"
							type={isPassShow ? "text" : "password"}
						/>
						<i
							className={isPassShow ? "fa fa-eye-slash" : "fa fa-eye"}
					
							onClick={() => setIsPassShow(!isPassShow)}
						></i>
					</div>
					{errors.oldpassword && (
						<p className="text-danger">Please enter your password</p>
					)}
					{changePassFail ? <p className="text-danger">Current password is incorrect</p> : "" }
				</div>
				<div className="mb-3">
					<label className="form-label" htmlFor="exampleInputPassword1">
						New password
					</label>
					<div className="pass-word">
						<input
							{...register("newpassword", {
								required: "Please enter your new password",
								minLength: {
									value: 8,
									message: "Password minimum length is 8.",
								},
							})}
							className="form-control"
							id="exampleInputEmail2"
							type={isNewPassShow ? "text" : "password"}
						/>
						<i
							className={isNewPassShow ? "fa fa-eye-slash" : "fa fa-eye"}
		
							onClick={() => setIsNewPassShow(!isNewPassShow)}
						></i>
					</div>
					<ErrorMessage
						errors={errors}
						name="newpassword"
						render={({ messages }) =>
							messages &&
							Object.entries(messages).map(([type, message]) => (
								<p className="text-danger" key={type}>
									{message}
								</p>
							))
						}
					/>
				</div>
				<div className="mb-3">
					<label className="form-label" htmlFor="exampleInputPassword1">
						Confirm password
					</label>
					<div className="pass-word">
						<input
							{...register("newpassword1", {
								required: "Please enter your confirm password",
								minLength: {
									value: 8,
									message: "Password minimum length is 8.",
								},
								// validate: {
								// 	checkPassWord: (newPass, confirmPass) => {
								// 		return newPass === confirmPass
								// 			? ""
								// 			: "Confirm password and new password is incorrect"
								// 	},
								// },
							})}
							className="form-control"
							id="exampleInputEmail3"
							type={isConfirmPassShow ? "text" : "password"}
						/>
						<i
							className={isConfirmPassShow ? "fa fa-eye-slash" : "fa fa-eye"}
							onClick={() => setConfirmPassShow(!isConfirmPassShow)}
						></i>
					</div>
					<ErrorMessage
						errors={errors}
						name="newpassword1"
						render={({ messages }) =>
							messages &&
							Object.entries(messages).map(([type, message]) => {
								return (
									<p className="text-danger" key={type}>
										{message}
									</p>
								)
							})
						}
					/>
				</div>
				<div>
					<button type="submit" className="btn btn-primary me-3">
						Change
					</button>
				</div>
			</form>
		</>
	)
}
