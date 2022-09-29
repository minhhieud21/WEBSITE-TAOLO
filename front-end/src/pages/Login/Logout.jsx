import React from 'react'
import { removeLocalStorage } from '../../services/LocalStorageService'
import { auth } from '../../firebase/config';
import { useNavigate } from 'react-router-dom';
export default function Logout({ username }) {
    const navigate = useNavigate();
    const handleLogOut = () => {
        auth.signOut().then(res =>{
            console.log(res)
            navigate("/");
            removeLocalStorage("username");
            window.location.reload();
        }).catch(err =>{
            console.log(err)
        })
    }
    return (
        <div className="dropdown">
            <button className="btn dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                {username}
            </button>
            <ul className="dropdown-menu">
                <li className="dropdown-item">Setting</li>
                <li className="dropdown-item" onClick={handleLogOut}>Logout</li>
            </ul>
        </div>
    )
}
