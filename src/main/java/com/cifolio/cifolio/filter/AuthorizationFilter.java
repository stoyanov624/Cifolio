package com.cifolio.cifolio.filter;

import com.cifolio.cifolio.exception_handling.exceptions.UnauthenticatedUserException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liquibase.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.cifolio.cifolio.constants.SecurityConstants.JWT_ACCESS_TOKEN_NAME;
import static com.cifolio.cifolio.constants.SecurityConstants.JWT_AUTHORITIES_CLAIM_NAME;
import static java.util.Arrays.stream;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {
    private final JwtDecoder jwtDecoder;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/api/login") || request.getServletPath().equals("/api/register") || request.getServletPath().equals("/api/logout")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            authorizeUser(request);
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            handleAuthenticationError(exception, response);
        }
    }

    private void authorizeUser(HttpServletRequest request) {
        Jwt jwt = jwtDecoder.decode(getAuthenticatedCookie(request));
        String username = jwt.getSubject();
        String[] roles = jwt.getClaim(JWT_AUTHORITIES_CLAIM_NAME).toString().split(",");

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        stream(roles).forEach(role -> {authorities.add(new SimpleGrantedAuthority(role));});

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private String getAuthenticatedCookie(HttpServletRequest request) {
        if(request.getCookies() == null || request.getCookies().length == 0) {
            throw new UnauthenticatedUserException("Unauthorized user!");
        }

        Optional<String> userCookie = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(JWT_ACCESS_TOKEN_NAME))
                .map(Cookie::getValue)
                .findAny();

        if(userCookie.isEmpty() || StringUtil.isEmpty(userCookie.get())) {
            throw new UnauthenticatedUserException("Unauthorized user!");
        }

        return userCookie.get();
    }

    private void handleAuthenticationError(Exception exception, HttpServletResponse response) throws IOException {
        response.setStatus(UNAUTHORIZED.value());
        response.setHeader("Error", exception.getMessage());

        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put("error_message", "Unauthorized user!");
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), errorMessage);
    }
}
