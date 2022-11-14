import React, { useState } from "react"
import { useForm } from "react-hook-form"
import { changePassword } from "../../services"
import { getLocalStorage } from "../../services/LocalStorageService"
import { Link, useNavigate } from "react-router-dom"
import ChangePassword from "./ChangePassword"
import GeneralInformation from "./GeneralInformation"

export default function EditAccount() {

	const [active, setActive] = useState(1)

	return (
		<>
			<div className="card mb-4" id="forms">
				<div className="d-flex card-header">
					<ul className="nav nav-pills" style={{cursor: "pointer"}}>
						<li className="nav-item">
							<Link
								className={active === 1 ? "nav-link active" : "nav-link"}
								aria-current="page"
								onClick={()=> setActive(1)}
							>
								General Information
							</Link>
						</li>
						<li className="nav-item">
							<Link className={active === 2 ? "nav-link active" : "nav-link"} onClick={()=> setActive(2)}>
								Change Password
							</Link>
						</li>
					</ul>
				</div>
				<div className="card-body">
					{ active === 1 ? <GeneralInformation/> : <ChangePassword /> }
					
					
				</div>
			</div>
		</>
	)
}
