import {createContext, ReactNode, useContext, useState} from "react";
import {User, UserLoginCredentials, UserRegisterCredentials} from "../services/user/interfaces";
import {authenticateUser, createUser, logoutUser} from "../services/user/controller";

interface AuthContext {
    user: User | null,
    login: (userCredentials: UserLoginCredentials) => Promise<User>;
    isAuthenticated: () => boolean;
    register: (registrationCredentials: UserRegisterCredentials) => void;
    logout: () => void;
}

type ChildrenProps = { children?: ReactNode };

const DEFAULT_FUNCTION = () => {
    throw new Error("Context is missing!")
}

const authContext = createContext<AuthContext>({
    user: null,
    login: DEFAULT_FUNCTION,
    isAuthenticated: DEFAULT_FUNCTION,
    register: DEFAULT_FUNCTION,
    logout: DEFAULT_FUNCTION
});

export function AuthProvider({children} : ChildrenProps) {
    const auth = useProvideAuth();
    return <authContext.Provider value={auth}>{children}</authContext.Provider>
}

export const useAuth = () => {
    return useContext(authContext)
}

function useProvideAuth() {
    const [user, setUser] = useState<User | null>(() => {
        const userObject = localStorage.getItem('user');
        return userObject ? JSON.parse(userObject) : null;
    });

    const login = async (userCredentials: UserLoginCredentials) => {
        const loggedUser : User = await authenticateUser(userCredentials);
        setUser(loggedUser);
        localStorage.setItem('user', JSON.stringify(loggedUser));
        return loggedUser;
    }

    const isAuthenticated = () => {
        return user !== null;
    }

    const register = async (registrationCredentials: UserRegisterCredentials) => {
        await createUser(registrationCredentials);
    }

    const logout = async () => {
        await logoutUser();
        setUser(null);
        localStorage.removeItem('user');
    }

    return {
        user,
        login,
        isAuthenticated,
        register,
        logout
    }
}