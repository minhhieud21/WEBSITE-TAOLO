import React, { useEffect, useState } from "react"
import { useNavigate, Link } from "react-router-dom"
import { googleLogin, setLocalStorage, getLocalStorage } from "../../services"
import { useForm } from "react-hook-form"
export default function SignUp() {

    const [isPassShow, setIsPassShow] = useState(false)
    const [isConfirmPassShow, setIsConfirmPassShow] = useState(false)
	const [isLoginFail, setIsLoginFail] = useState(false)
	const {
		register,
		handleSubmit,
		formState: { errors },
	} = useForm()
	const navigate = useNavigate()
	const textLoginFail = "Username or Password is wrong!"
	const isLogin = getLocalStorage("username")

    const handleSignUp = (data) =>{
        console.log(data)
    }

	return (
		<>
			<div className="card mb-4" id="forms">
				<div className="card-header">Sign Up</div>
				<div className="card-body">
					<form onSubmit={handleSubmit(handleSignUp)}>
						<div className="mb-3">
							<label className="form-label" htmlFor="exampleInputEmail1">
								Email address
							</label>
							<input
								className="form-control"
								id="exampleInputEmail1"
								type="text"
								aria-describedby="emailHelp"
								autoFocus
								{...register("username", { required: true })}
							/>
							{errors.username && (
								<p className="text-danger">Please enter username</p>
							)}
						</div>
						<div className="mb-3">
							<label className="form-label" htmlFor="exampleInputPassword1">
								Password
							</label>
							<div className="pass-word">
								<input
									className="form-control"
									id="exampleInputPassword1"
									type={isPassShow ? "text" : "password"}
									{...register("password", { required: true, minLength: 8 })}
								/>
								<i
									className={isPassShow ? "fa fa-eye-slash" : "fa fa-eye"}
									onClick={() => setIsPassShow(!isPassShow)}
								></i>
							</div>
							{errors.password && (
								<p className="text-danger">Please enter password</p>
							)}
						</div>
                        <div className="mb-3">
							<label className="form-label" htmlFor="exampleInputPassword1">
								Confirm password
							</label>
							<div className="pass-word">
								<input
									className="form-control"
									id="exampleInputPassword1"
									type={isConfirmPassShow ? "text" : "password"}
									{...register("password", { required: true, minLength: 8 })}
								/>
								<i
									className={isConfirmPassShow ? "fa fa-eye-slash" : "fa fa-eye"}
									onClick={() => setIsConfirmPassShow(!isConfirmPassShow)}
								></i>
							</div>
							{errors.password && (
								<p className="text-danger">Please enter password</p>
							)}
						</div>
                        <div className="mb-3">
							<label className="form-label" htmlFor="exampleInputPassword1">
								Email
							</label>
							<div className="pass-word">
								<input
									className="form-control"
									id="exampleInputPassword1"
									type={isConfirmPassShow ? "text" : "password"}
									{...register("confirmPassword", { required: true, minLength: 8 })}
								/>
							</div>
							{errors.confirmPassword && (
								<p className="text-danger">Please enter password</p>
							)}
						</div>
						{isLoginFail ? <p className="text-danger">{textLoginFail}</p> : ""}
	
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
