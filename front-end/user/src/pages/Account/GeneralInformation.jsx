import React, { useState, useEffect, useContext } from "react"
import { useForm } from "react-hook-form"
import { getUserByUserId } from "../../services"
import { getLocalStorage } from "../../services/LocalStorageService"
import { useNavigate } from "react-router-dom"
import { CartAndProductContext } from "../../layouts/MainLayout/ContainerMainLayout"

export default function () {
	const { register, handleSubmit } = useForm()
	const userId = getLocalStorage("userId")
	const {token} = useContext(CartAndProductContext)
	const [userInfo, setUserInfo] = useState({})

	useEffect(() => {
		getUserByUserId(userId,token)
			.then((res) => {
				console.log(res.data)
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
							defaultValue={userInfo.name}
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
							defaultValue={userInfo.phone}

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
							defaultValue={userInfo.address}

						/>
					</div>
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
