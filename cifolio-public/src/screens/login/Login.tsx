import {FormEvent} from "react";
import "./Login.css";
import {useNavigate} from "react-router-dom";

export default function Login() {
    const navigate = useNavigate();

    const handleSubmit = async (event : FormEvent) => {
        event.preventDefault();
        // if user credentials are fine go to ->
        navigate("/home", {replace: true});
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