interface UserLoginCredentials {
    username: string,
    password: string
}

interface UserRegisterCredentials extends UserLoginCredentials {
    email: string;
}

interface User {
    username: string,
    role: string
}

export type {
    UserLoginCredentials,
    UserRegisterCredentials,
    User
}