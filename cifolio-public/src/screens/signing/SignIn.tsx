import {FormEvent, useState} from "react";
import "./Signing.css";
import {useNavigate} from "react-router-dom";
import {login} from "../../services/user/controller";
import {User, UserLoginCredentials} from "../../services/user/interfaces";
import {updateStateOnInputChange} from "../../utils/InputManager";

export default function SignIn() {
    const navigate = useNavigate();
    const [userCredentials, setUserCredentials] = useState({
        username: '',
        password: ''} as UserLoginCredentials
    );
    const [errorMessage, setErrorMessage] = useState('');

    const handleSubmit = async (event : FormEvent) => {
        event.preventDefault();
        try {
            const user : User = await login(userCredentials);
            localStorage.setItem('user', JSON.stringify(user));
            setErrorMessage('');
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