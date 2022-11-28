import {useNavigate} from "react-router-dom";
import {FormEvent, useState} from "react";
import {UserRegisterCredentials} from "../../services/user/interfaces";
import {updateStateOnInputChange} from "../../utils/InputManager";
import {useAuth} from "../../hooks/useAuth";

export default function SignUp() {
    const navigate = useNavigate();
    const auth = useAuth();
    const [userCredentials, setUserCredentials] = useState({
        username: '',
        email: '',
        password: ''} as UserRegisterCredentials
    );

    const handleSubmit = async (event : FormEvent) => {
        event.preventDefault();
        try {
            await auth.register(userCredentials)
            goToLoginPage();
        } catch (error) {
            console.log(error);
        }
    }

    const goToLoginPage = () => {
        navigate('/login')
    }

    return (<div className={"signing-form-container center"}>
        <form className={"signing-form"} onSubmit={handleSubmit}>
            <input
            placeholder={"Username"}
            type={"text"}
            value={userCredentials.username}
            name={"username"}
            onChange={event => updateStateOnInputChange(event, setUserCredentials)}
            />

            <input
                placeholder={"Email"}
                type={"text"}
                value={userCredentials.email}
                name={"email"}
                onChange={event => updateStateOnInputChange(event, setUserCredentials)}
            />

            <input
            placeholder={"Password"}
            type={"password"}
            value={userCredentials.password}
            name={"password"}
            onChange={event => updateStateOnInputChange(event, setUserCredentials)}
            />

            <div className={"signing-button-container"}>
                <button onClick={goToLoginPage} className={"clickable"} type={"button"}>Back</button>
                <button className={"clickable"} type={"submit"}>Register</button>
            </div>
        </form>
    </div>
    )
}