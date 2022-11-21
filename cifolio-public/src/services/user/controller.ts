import axios from "../axios";
import {UserLoginCredentials, UserRegisterCredentials} from "./interfaces";

const login = async (userCredentials: UserLoginCredentials) => {
    return (await axios.post("/login", userCredentials)).data;
}

const register = async (registrationForm: UserRegisterCredentials) => {
    return (await axios.post("/register", registrationForm)).data;
}

const logout = async () => {
    return (await axios.delete("/logout")).data;
}

export {login, register, logout}