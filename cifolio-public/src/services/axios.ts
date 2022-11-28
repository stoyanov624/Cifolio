import axios from "axios";

const Axios = axios.create(
    {
        baseURL: "http://localhost:8080/api/",
        withCredentials: true
    }
)
// testing interceptors
Axios.interceptors.response.use(
    res => res,
    error => {
        if((error?.response?.status === 401 && window.location.pathname !== '/login') || error?.response?.status === 403) {
            window.location.replace('http://localhost:3000/login');
        }
        return Promise.reject(error);
    }
)
export default Axios;