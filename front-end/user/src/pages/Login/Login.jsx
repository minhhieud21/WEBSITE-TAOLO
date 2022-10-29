import React, { useCallback, useEffect, useState } from "react"
import ImgGG from "../../assets/img/google.svg"
import firebase, { auth } from "../../firebase/config"
import { useNavigate } from "react-router-dom"
import { getLocalStorage } from "../../services/LocalStorageService"
import axios from "axios"
import { setLocalStorage } from "../../services/LocalStorageService"

const ggProvider = new firebase.auth.GoogleAuthProvider()
const Login = () => {
	const [userName, setUserName] = useState("")
	const [passWord, setPassWord] = useState("")
	const [isPassShow, setIsPassShow] = useState(false)
	const [isLoginFail, setIsLoginFail] = useState(false)
	const textLoginFail = "Username or Password is wrong!"
	const isLogin = getLocalStorage("username")
	const navigate = useNavigate()
	useEffect(() => {
		if (isLogin) navigate("/")
	}, [isLogin])

	const handleSignInWithGoogle = () => {
		auth
			.signInWithPopup(ggProvider)
			.then((res) => {
				if (res) {
					navigate("/")
					window.location.reload()
				}
			})
			.catch((err) => {
				console.log(err)
			})
	}

	const handleBasicAuth = (event) => {
		event.preventDefault()
		const params = {
			username: userName,
			password: passWord,
		}
		axios
			.post(`${process.env.REACT_APP_DEV_ENV}account/login`, params)
			.then((res) => {
				if (res && res.data) {
					setLocalStorage("username", userName)
					navigate("/")
					window.location.reload()
				}
			})
			.catch((e) => setIsLoginFail(!isLoginFail))
	}
	return (
		<>
			<div className="card mb-4" id="forms">
				<div className="card-header">Login</div>
				<div className="card-body">
					<form onSubmit={handleBasicAuth}>
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
								value={userName}
								onChange={(e) => setUserName(e.target.value)}
							/>
							<div className="form-text" id="emailHelp">
								We'll never share your email with anyone else.
							</div>
						</div>
						<div className="mb-3">
							<label className="form-label" htmlFor="exampleInputPassword1">
								Password
							</label>
							<div className="d-flex">
								<input
									className="form-control"
									id="exampleInputPassword1"
									type={isPassShow ? "text" : "password"}
									value={passWord}
									onChange={(e) => setPassWord(e.target.value)}
								/>
								<i
									className={isPassShow ? "fa fa-eye-slash" : "fa fa-eye"}
									style={{
										position: "absolute",
										right: "2%",
										top: "68%",
										cursor: "pointer",
									}}
									onClick={() => setIsPassShow(!isPassShow)}
								></i>
							</div>
							{isLoginFail ? (
								<span className="form-text text-danger fw-bold" id="emailHelp">
									{textLoginFail}
								</span>
							) : (
								""
							)}
						</div>
						<div>
							<button
								type="submit"
								className="btn btn-primary me-3"
								onClick={handleBasicAuth}
							>
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
		</>
	)
}

export default Login
