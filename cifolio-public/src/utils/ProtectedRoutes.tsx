import {Navigate, Outlet} from "react-router-dom";

const ProtectedRoutes = () => {
    const user = localStorage.getItem('user');
    const isAuthenticated : boolean = Boolean(user) && Boolean(JSON.parse(localStorage.getItem('user') as string));

    return(
        isAuthenticated ? <Outlet/> : <Navigate to={'/login'}/>
    )
}

export default ProtectedRoutes;
