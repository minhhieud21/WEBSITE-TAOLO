import React, { useEffect, useState } from "react"
import ImgGG from "../../assets/img/google.svg"
import firebase, { auth } from "../../firebase/config"
import { useNavigate, Link } from "react-router-dom"
import axios from "axios"
import { googleLogin, setLocalStorage, getLocalStorage } from "../../services"
import { useForm } from "react-hook-form"

import { ToastContainer,toast } from "react-toastify"
import { PopupSuccess } from "components/Popup/PopupSuccess"

const ggProvider = new firebase.auth.GoogleAuthProvider()

const Login = () => {
	const [isPassShow, setIsPassShow] = useState(false)
	const [isLoginFail, setIsLoginFail] = useState(false)
	const {
		register,
		handleSubmit,
		formState: { errors },
	} = useForm()
	const navigate = useNavigate()
	const textLoginFail = "Username or Password is wrong!"
	const isLogin = getLocalStorage("username")

	useEffect(() => {
		if (isLogin) navigate("/")
	}, [isLogin])

	const handleSignInWithGoogle = () => {
		auth
			.signInWithPopup(ggProvider)
			.then(async (res) => {
				if (res.user) {
					navigate("/")
					window.location.reload()
					await googleLogin(res.user.photoURL)
				}
			})
			.catch((err) => {
				console.log(err)
			})
	}
	const notifySuccess = () => {
		toast.success("Đăng nhập thành công!", {
			position: toast.POSITION.BOTTOM_CENTER,
		})
	}

	const handleBasicAuth = (data) => {
		axios
			.post(`${process.env.REACT_APP_DEV_ENV}account/login`, data)
			.then((res) => {
				if (res) {
					setLocalStorage("username", data.username)
					setLocalStorage("userId", res.data.data)
					setLocalStorage("token", res.data.data)
					navigate("/")
				}
			})
			.catch((e) => setIsLoginFail(true))
	}
	return (
		<>
			<div className="card mb-4" id="forms">
				<div className="card-header">Login</div>
				<div className="card-body">
					<form onSubmit={handleSubmit(handleBasicAuth)}>
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
						{isLoginFail ? <p className="text-danger">{textLoginFail}</p> : ""}
						<Link
							className="d-flex justify-content-end"
							to={`/forgot-password`}
						>
							Forget password!
						</Link>

						<div>
							<button type="submit" className="btn btn-primary me-3">
								Login
							</button>
						</div>
					</form>
					<div
						className="ml-2"
						style={{
							position: "absolute",
							left: "7%",
							bottom: "5%",
							marginLeft: "10px",
						}}
					>
						<button className="btn border" onClick={handleSignInWithGoogle}>
							<img
								className=""
								src={ImgGG}
								alt="google"
								style={{ width: "21px", height: "21px" }}
							/>
							Google
						</button>
					</div>
				</div>
			</div>
			<ToastContainer autoClose={1500} />
		</>
	)
}

export default Login
