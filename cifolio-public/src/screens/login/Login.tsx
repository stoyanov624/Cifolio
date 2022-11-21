import {FormEvent} from "react";
import "./Login.css";
import {fetchCities} from "../../services/city/controller";
import {login, logout, register} from "../../services/user/controller";

export default function Login() {
    const handleSubmit = async (event : FormEvent) => {
        event.preventDefault();
        await register("user", "user", "pass");
        await login("user", "pass");
        await fetchCities(0, 12);
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