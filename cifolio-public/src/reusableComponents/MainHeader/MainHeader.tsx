import "./MainHeader.css"
import {useAuth} from "../../hooks/useAuth";
import {Link, To, useMatch, useResolvedPath} from "react-router-dom";

export default function MainHeader() {
    const auth = useAuth();

    return (
        <div className={`${auth.isAuthenticated() ? 'mainHeader wide' : 'mainHeader'}`}>
            <h1>Cifolio</h1>

            {auth.isAuthenticated() &&
            <nav className={"navigationBar"}>
                <ul className={"navigation-options"}>
                    <CustomLink to={'/login'}>Logout</CustomLink>
                    <CustomLink to={'/home'}>Home</CustomLink>
                    <CustomLink to={'/guides'}>Guides</CustomLink>
                </ul>
            </nav>
            }
        </div>
    )

    function CustomLink({to, children , ...props} : any) {
        const resolvedPath = useResolvedPath(to);
        const isActive = useMatch({path: resolvedPath.pathname, end: true});

        return (
            <Link className={`${isActive ? "active-link" : "custom-link"}`} to={to} {...props}>{children}</Link>
        )
    }
}