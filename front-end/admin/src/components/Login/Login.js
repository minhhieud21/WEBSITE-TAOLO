import React, { useState } from 'react';
import { Form, FormGroup, Col, Container, Button } from "react-bootstrap";
import { useHistory } from "react-router-dom";
import { useForm } from 'react-hook-form';
import { login } from 'services';

export default function Login() {

    const { register, handleSubmit, getValues } = useForm()
    const history = useHistory()
    const handleLogin = (data) => {
        login(data).then(res => {
            if (res && res.data) {
                history.push("/");
                localStorage.setItem("userName", getValues("username"))
                localStorage.setItem("userID", res.data.data.userID)
                localStorage.setItem("token", res.data.data.token)
            }

        })
    }
    return (
        <div className='login d-flex'>
            <div className='login__background'>
            </div>
            <Container className='login__form py-5'>
                <Form onSubmit={handleSubmit(handleLogin)}>
                    <FormGroup >
                        <label htmlFor="exampleEmail">
                            Email
                        </label>
                        <input
                            id="exampleEmail"
                            name="email"
                            placeholder="Email"
                            type="text"
                            {...register("username", { required: true })}
                        />
                    </FormGroup>
                    {' '}
                    <FormGroup>
                        <label htmlFor="examplePassword">
                            Password
                        </label>
                        <input
                            id="examplePassword"
                            name="password"
                            placeholder="Password"
                            type="password"
                            {...register("password", { required: true })}
                        />
                    </FormGroup>
                    {' '}
                    <Button type='submit'>
                        Submit
                    </Button>
                </Form>

            </Container>

        </div>
    )
}
