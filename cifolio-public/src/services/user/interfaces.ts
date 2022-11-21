interface UserLoginCredentials {
    username: string,
    password: string
}

interface UserRegisterCredentials extends UserLoginCredentials {
    email: string;
}

export type {
    UserLoginCredentials,
    UserRegisterCredentials
}