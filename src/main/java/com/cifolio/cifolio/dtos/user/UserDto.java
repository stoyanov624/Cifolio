package com.cifolio.cifolio.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class UserDto {
    @NotBlank()
    private String username;

    @NotBlank()
    private String password;

    @NotBlank()
    private String email;
}
