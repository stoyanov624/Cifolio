package com.cifolio.cifolio.constants;

public final class SecurityConstants {
    public static final String JWT_AUTHORITIES_CLAIM_NAME = "roles";
    public static final String JWT_ACCESS_TOKEN_NAME = "user-jwt";
    public static final String[] WHITE_LIST_URIS = {"/h2-console", "/api/register", "/api/login", "/api/logout"};
    private SecurityConstants() {}
}