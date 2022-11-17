package com.cifolio.cifolio.controller;

import com.cifolio.cifolio.converters.RegistrationFormToUserEntityConverter;
import com.cifolio.cifolio.dto.LoginForm;
import com.cifolio.cifolio.dto.RegistrationForm;
import com.cifolio.cifolio.service.token.TokenService;
import com.cifolio.cifolio.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {
    private final UserService userService;
    private final RegistrationFormToUserEntityConverter registrationFormToUserEntityConverter;
    private final TokenService tokenService;
    private final AuthenticationManager authManager;

    @PostMapping("/register")
    private ResponseEntity<String> registerUser(@RequestBody RegistrationForm registrationForm) {
        try {
            userService.registerUser(registrationFormToUserEntityConverter.apply(registrationForm));
            return ResponseEntity.ok().body("Successful registration!");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.badRequest().body("Error occurred while registration attempt!");
        }
    }

    @PostMapping("/login")
    private ResponseEntity<String> login(@RequestBody LoginForm loginForm) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginForm.getUsername(),
                    loginForm.getPassword());
            Authentication authentication = authManager.authenticate(authenticationToken);
            return ResponseEntity.ok().body(tokenService.generateToken(authentication));
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.badRequest().body("Error occurred on login attempt!");
        }
    }
}
