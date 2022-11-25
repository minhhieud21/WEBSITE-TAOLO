import React, { useState } from 'react';
import { Form, FormGroup, Col, Container, Button, Input } from "react-bootstrap";
import { useHistory } from "react-router-dom";
import { useForm } from 'react-hook-form';
import { login } from 'services';

export default function Login() {

    const { register, handleSubmit, getValues } = useForm()
    const [isloginFail, setIsloginFail] = useState(false)
    const history = useHistory()
    const handleLogin = (data) => {
        login(data).then(res => {
            if (res && res.data) {
                localStorage.setItem("userName", getValues("username"))
                localStorage.setItem("userID", res.data.data.userID)
                localStorage.setItem("token", res.data.data.token)
                history.push("/admin/dashboard");
            }

        }).catch(e => setIsloginFail(true))
    }
    return (
        <div className='login'>
            <Form className='login__form' onSubmit={handleSubmit(handleLogin)}>
                <h3>Sign In</h3>
                <div className="mb-3">
                    <label>Email address</label>
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Enter email"
                        {...register("username", { required: true })}

                    />
                </div>
                <div className="mb-3">
                    <label>Password</label>
                    <input
                        type="password"
                        className="form-control"
                        placeholder="Enter password"
                        {...register("password", { required: true })}
                    />
                </div>
                {isloginFail &&  <p className="text-danger">Username or password is incorrect!</p>}
                <div className="login__form__btn">
                    <button type="submit" className="btn btn-primary">
                        Login
                    </button>
                </div>
            </Form>
        </div>

    )
}
