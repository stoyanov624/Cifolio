import axios from "../axios";

const registrationForm = {
    username: "user",
    password: "pass",
    email: "email"
}

const loginData = {
    username: "user",
    password: "pass"
}

const login = async (username: string, password: string) => {
    return (await axios.post("/login", loginData)).data;
}

const register = async (username: string, email: string, password: string) => {
    return (await axios.post("/register", registrationForm)).data;
}

export {login, register}