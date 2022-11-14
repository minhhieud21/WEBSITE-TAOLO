import React, { useState, useEffect } from "react"
import { useForm } from "react-hook-form"
import { getUserByUserId } from "../../services"
import { getLocalStorage } from "../../services/LocalStorageService"
import { useNavigate } from "react-router-dom"

export default function () {
	const { register, handleSubmit } = useForm()
	const userId = getLocalStorage("userId")

	const [userInfo, setUserInfo] = useState({})

	useEffect(() => {
		getUserByUserId(userId)
			.then((res) => {
				setUserInfo(res.data.data)
			})
			.catch((e) => console.log(e))
	}, [userId])

	return (
		<>
			<form>
				<div className="mb-3">
					<label className="form-label" htmlFor="exampleInputEmail1">
						User Name
					</label>
					<div className="d-flex">
						<input
							className="form-control"
							id="exampleInputEmail1"
							type="text"
							readOnly
							value={userInfo.name}
						/>
					</div>
				</div>
				<div className="mb-3">
					<label className="form-label" htmlFor="exampleInputPassword1">
						Phone
					</label>
					<div className="d-flex">
						<input
							className="form-control"
							id="exampleInputEmail2"
							type="text"
							value={userInfo.phone}

						/>
					</div>
				</div>
				<div className="mb-3">
					<label className="form-label" htmlFor="exampleInputPassword1">
						Address
					</label>
					<div className="d-flex">
						<input
							className="form-control"
							id="exampleInputEmail3"
							type="text"
							value={userInfo.address}

						/>
					</div>
				</div>
			</form>
		</>
	)
}
