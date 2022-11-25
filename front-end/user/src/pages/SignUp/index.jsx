import React, { useEffect, useState, useMemo } from "react"
import { useNavigate, Link } from "react-router-dom"
import { getLocalStorage, signUp } from "../../services"
import { useForm } from "react-hook-form"

export default function SignUp() {
	const [isPassShow, setIsPassShow] = useState(false)
	const [isSignUpFail, setIsSignUpFail] = useState(false)

	const {
		register,
		handleSubmit,
		formState: { errors },
	} = useForm()
	const navigate = useNavigate()
	const textLoginFail = "Username is taken!"

	const handleSignUp = async (data) => {
		console.log(data)
		signUp(data)
			.then((res) => navigate('/login'))
			.catch((e) => setIsSignUpFail(true))
	}

	return (
		<>
			<div className="card mb-4" id="forms">
				<div className="card-header fw-bold text-primary">Sign Up</div>
				<div className="card-body">
					<form onSubmit={handleSubmit(handleSignUp)}>
						<div className="mb-3">
							<label className="form-label" htmlFor="exampleInputEmail1">
								Username
							</label>
							<input
								className="form-control"
								id="low"
								name="low"
								{...register("username", {
									required: true,
								})}
								aria-describedby="emailHelp"
								autoFocus
							/>
							{errors.username?.type === "required" && (
								<p className="text-danger">User name is required</p>
							)}
							{isSignUpFail && <p className="text-danger">{textLoginFail}</p>}
						</div>
						<div className="mb-3">
							<label className="form-label" htmlFor="exampleInputPassword1">
								Password
							</label>
							<div className="pass-word">
								<input
									className="form-control"
									type={isPassShow ? "text" : "password"}
									{...register("password", { required: true, minLength: 8 })}
								/>
								<i
									className={isPassShow ? "fa fa-eye-slash" : "fa fa-eye"}
									onClick={() => setIsPassShow(!isPassShow)}
								></i>
							</div>
							{errors.password?.type === "required" && (
								<p className="text-danger">Please enter password</p>
							)}
							{errors.password?.type === "minLength" && (
								<p className="text-danger">Password at least 8 character</p>
							)}
						</div>
						<div className="mb-3">
							<label className="form-label" htmlFor="exampleInputPassword1">
								Name
							</label>
							<div className="pass-word">
								<input
									className="form-control"
									{...register("name", { required: true })}
								/>
							</div>
							{errors.name && <p className="text-danger">Please enter name</p>}
						</div>
						<div className="mb-3">
							<label className="form-label" htmlFor="exampleInputPassword1">
								Phone number
							</label>
							<div className="pass-word">
								<input
									className="form-control"
									{...register("phone", { required: true, minLength: 10 })}
								/>
							</div>
							{errors.phone?.type === "required" && (
								<p className="text-danger">Please enter phone</p>
							)}
							{errors.phone?.type === "minLength" && (
								<p className="text-danger">
									Phone number at least 10 character
								</p>
							)}
						</div>
						<div className="mb-3">
							<label className="form-label" htmlFor="exampleInputPassword1">
								Address
							</label>
							<div className="pass-word">
								<input
									className="form-control"
									{...register("address", { required: true, minLength: 10 })}
								/>
							</div>
							{errors.address?.type === "required" && (
								<p className="text-danger">Please enter address</p>
							)}
							{errors.address?.type === "minLength" && (
								<p className="text-danger">Address at least 10 character</p>
							)}
						</div>
						<div className="mb-3">
							<label className="form-label" htmlFor="exampleInputPassword1">
								Gmail
							</label>
							<div className="pass-word">
								<input
									className="form-control"
									{...register("gmail", {
										pattern: {
											value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
											message: "invalid email address",
										},
									})}
								/>
							</div>
							{errors.gmail && <p className="text-danger">Invalid gmail</p>}
						</div>
						<div className="mb-3">
							<label className="form-label" htmlFor="exampleInputPassword1">
								Age
							</label>
							<div className="pass-word">
								<input
									className="form-control"
									{...register("age", { required: true, min: 0 })}
								/>
							</div>
							{errors.age?.type === "required" && (
								<p className="text-danger">Please enter age</p>
							)}
							{errors.age?.type === "min" && (
								<p className="text-danger">Age must be greater than 0</p>
							)}
						</div>
						<div className="mb-3 d-flex">
							<label className="form-label" htmlFor="exampleInputPassword1">
								Gender
							</label>
							<div className="">
								<select name="" id="" {...register("sex")}>
									<option value="0" selected>Male</option>
									<option value="1">Female</option>
								</select>
							</div>
						</div>
						<div>
							<button type="submit" className="btn btn-primary me-3">
								Sign up
							</button>
						</div>
					</form>
				</div>
			</div>
		</>
	)
}
