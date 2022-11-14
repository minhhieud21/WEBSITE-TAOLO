import React from "react"
import { removeLocalStorage } from "../../services/LocalStorageService"
import { auth } from "../../firebase/config"
import { useNavigate, Link } from "react-router-dom"
export default function Logout({ username }) {
	const navigate = useNavigate()
	const handleLogOut = () => {
		auth
			.signOut()
			.then((res) => {
				removeLocalStorage("username")
				removeLocalStorage("userId")
				navigate("/")
				window.location.reload()
			})
			.catch((err) => {
				console.log(err)
			})
	}
	return (
		<div className="dropdown">
			<button
				className="btn dropdown-toggle"
				type="button"
				data-bs-toggle="dropdown"
				aria-expanded="false"
			>
				{username}
			</button>
			<ul className="dropdown-menu">
				<li className="dropdown-item"><Link to={`/account`}>Setting</Link></li>
				<li className="dropdown-item" onClick={handleLogOut}>
					Logout
				</li>
			</ul>
		</div>
	)
}
