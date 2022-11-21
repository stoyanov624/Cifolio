import {FormEvent, useState} from "react";
import "./Login.css";
import {useNavigate} from "react-router-dom";
import {login} from "../../services/user/controller";
import { UserLoginCredentials } from "../../services/user/interfaces";
import {updateStateOnInputChange} from "../../utils/InputManager";

export default function Login() {
    const navigate = useNavigate();
    const [userCredentials, setUserCredentials] = useState({
        username: '',
        password: ''} as UserLoginCredentials
    );

    const handleSubmit = async (event : FormEvent) => {
        event.preventDefault();
        try {
            await login(userCredentials);
            navigate("/home", {replace: true});
        } catch (error : any) {
            console.log("Error in login!");
        }
    }

    return (<div className={"login-form-container center"}>
            <form className={"login-form"} onSubmit={handleSubmit}>
                <input
                    placeholder={"Username"}
                    type={"text"}
                    value={userCredentials.username}
                    name={"username"}
                    onChange={event => updateStateOnInputChange(event, setUserCredentials)}
                />

                <input
                    placeholder={"Password"}
                    type={"password"}
                    value={userCredentials.password}
                    name={"password"}
                    onChange={event => updateStateOnInputChange(event, setUserCredentials)}
                />
                <div className={"login-button-container"}>
                    <button className={"clickable"} type={"submit"}>Login</button>
                    <button className={"clickable"} type={"button"}>Register</button>
                </div>
            </form>
        </div>
    )
}