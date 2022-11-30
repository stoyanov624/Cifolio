package com.cifolio.cifolio.controller;

import com.cifolio.cifolio.converters.user.UserDtoToUserEntityConverter;
import com.cifolio.cifolio.dto.user.UserDto;
import com.cifolio.cifolio.service.user.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.cifolio.cifolio.constants.SecurityConstants.JWT_ACCESS_TOKEN_NAME;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {
    private final UserService userService;
    private final UserDtoToUserEntityConverter userDtoToUserEntityConverter;

    @PostMapping("/register")
    private ResponseEntity<String> registerUser(@RequestBody UserDto registrationForm) {
        userService.registerUser(userDtoToUserEntityConverter.apply(registrationForm));
        return ResponseEntity.ok().body("Successful registration!");
    }

    @DeleteMapping("/logout")
    private ResponseEntity<Void> logout() {
        ResponseCookie deleteSpringCookie = ResponseCookie
                .from(JWT_ACCESS_TOKEN_NAME, "")
                .build();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, deleteSpringCookie.toString())
                .build();
    }
}
