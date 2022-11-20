package com.cifolio.cifolio.controller;

import com.cifolio.cifolio.converters.RegistrationFormToUserEntityConverter;
import com.cifolio.cifolio.dto.RegistrationForm;
import com.cifolio.cifolio.service.user.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {
    private final UserService userService;
    private final RegistrationFormToUserEntityConverter registrationFormToUserEntityConverter;

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

    @DeleteMapping("/logout")
    private ResponseEntity<Void> logout() {
        try {
            ResponseCookie deleteSpringCookie = ResponseCookie
                    .from("user-jwt", null)
                    .build();

            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.SET_COOKIE, deleteSpringCookie.toString())
                    .build();
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
