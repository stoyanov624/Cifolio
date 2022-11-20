import {FormEvent} from "react";
import "./Login.css";

export default function Login() {
    const handleSubmit = (event : FormEvent) => {
        event.preventDefault();
        console.log("Log in!")
    }

    return (<div className={"login-form-container center"}>
            <form className={"login-form"} onSubmit={handleSubmit}>
                <input placeholder={"Username"} type={"text"}/>
                <input placeholder={"Password"} type={"text"}/>
                <div className={"login-button-container"}>
                    <button className={"clickable"} type={"submit"}>Login</button>
                    <button className={"clickable"} type={"button"}>Register</button>
                </div>
            </form>
        </div>
    )
}