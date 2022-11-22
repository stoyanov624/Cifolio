import {Navigate, Outlet} from "react-router-dom";

const ProtectedRoutes = () => {
    const isAuthenticated : boolean = Boolean(JSON.parse(localStorage.getItem('user') as string));

    return(
        isAuthenticated ? <Outlet/> : <Navigate to={'/login'}/>
    )
}

export default ProtectedRoutes;
