import {FormEvent, useEffect, useState} from "react";
import "./Signing.css";
import {useNavigate} from "react-router-dom";
import {UserLoginCredentials} from "../../services/user/interfaces";
import {updateStateOnInputChange} from "../../utils/InputManager";
import {useAuth} from "../../hooks/useAuth";

export default function SignIn() {
    const navigate = useNavigate();
    const [userCredentials, setUserCredentials] = useState({
        username: '',
        password: ''} as UserLoginCredentials
    );
    const auth = useAuth();
    const [errorMessage, setErrorMessage] = useState('');

    const handleSubmit = async (event : FormEvent) => {
        event.preventDefault();
        try {
            await auth.login(userCredentials);
            navigate("/home", {replace: true});
        } catch (error : any) {
            if (error.response.status === 401) {
                setErrorMessage('Wrong username or password!');
            }
        }
    }

    const goToRegisterPage = () => {
        navigate('/register');
    }

    useEffect(() => {
       auth.isAuthenticated() && auth.logout();
    }, [])

    return (<div className={"signing-form-container center"}>
            <form className={"signing-form"} onSubmit={handleSubmit}>
                {errorMessage && <p className={'error-message bold'}>{errorMessage}</p>}
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
                <div className={"signing-button-container"}>
                    <button className={"clickable"} type={"submit"}>Login</button>
                    <button onClick={goToRegisterPage} className={"clickable"} type={"button"}>Register</button>
                </div>
            </form>
        </div>
    )
}