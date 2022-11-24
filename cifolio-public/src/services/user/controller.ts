import axios from "../axios";
import {UserLoginCredentials, UserRegisterCredentials} from "./interfaces";

const authenticateUser = async (userCredentials: UserLoginCredentials) => {
    return (await axios.post("/login", userCredentials)).data;
}

const createUser = async (registrationForm: UserRegisterCredentials) => {
    return (await axios.post("/register", registrationForm)).data;
}

const logoutUser = async () => {
    return (await axios.delete("/logout")).data;
}

export {authenticateUser, createUser, logoutUser}