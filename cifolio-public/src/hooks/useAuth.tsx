import {createContext, ReactNode, useContext, useEffect, useMemo, useState} from "react";
import {User, UserLoginCredentials} from "../services/user/interfaces";
import {authenticateUser} from "../services/user/controller";

interface AuthContext {
    user: User | null,
    login: (userCredentials: UserLoginCredentials) => Promise<User>;
    isAuthenticated: () => boolean;
    // register: () => void;
}

type ChildrenProps = { children?: ReactNode };

const DEFAULT_FUNCTION = () => {
    throw new Error("Context is missing!")
}

const authContext = createContext<AuthContext>({
    user: null,
    login: DEFAULT_FUNCTION,
    isAuthenticated: DEFAULT_FUNCTION
    // register: DEFAULT_FUNCTION,
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

    return {
        user,
        login,
        isAuthenticated
    }
}