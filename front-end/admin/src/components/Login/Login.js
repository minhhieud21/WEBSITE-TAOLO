import React, { useState } from 'react';
import { Form, FormGroup, Col, Container, Button } from "react-bootstrap";
import { useHistory } from "react-router-dom";

export default function Login() {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const history = useHistory()
    const handleLogin = (e) => {
        e.preventDefault();
        if (username, password) {
            history.push("/admin/dashboard")
        }
    }
    return (
        <Container className='py-5'>
            <Form onSubmit={handleLogin}>
                <FormGroup >
                    <label htmlFor="exampleEmail">
                        Email
                    </label>
                    <input
                        id="exampleEmail"
                        name="email"
                        placeholder="Email"
                        type="email"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
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
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </FormGroup>
                {' '}
                <Button type='submit'>
                    Submit
                </Button>
            </Form>

        </Container>
    )
}
