package com.cifolio.cifolio.controller;

import com.cifolio.cifolio.converters.RegistrationFormToUserEntityConverter;
import com.cifolio.cifolio.dto.RegistrationForm;
import com.cifolio.cifolio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final RegistrationFormToUserEntityConverter registrationFormToUserEntityConverter;

    @PostMapping("/register")
    private ResponseEntity<String> registerUser(@RequestBody RegistrationForm registrationForm) {
        userService.registerUser(registrationFormToUserEntityConverter.apply(registrationForm));
        return ResponseEntity.ok().body("Successful registration!");
    }
}
