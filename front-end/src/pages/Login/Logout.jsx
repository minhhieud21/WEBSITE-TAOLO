import React from 'react'
import { removeLocalStorage } from '../../services/LocalStorageService'
import { auth } from '../../firebase/config';
export default function Logout({ username }) {
    const handleLogOut = () => {
        auth.signOut().then(res =>{
            console.log(res)
            removeLocalStorage("username");
        }).catch(err =>{
            console.log(err)
        })
    }
    return (
        <div className="dropdown">
            <button className="btn btn-primary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                {username}
            </button>
            <ul className="dropdown-menu">
                <li className="dropdown-item">Setting</li>
                <li className="dropdown-item" onClick={handleLogOut}>Logout</li>
            </ul>
        </div>
    )
}
