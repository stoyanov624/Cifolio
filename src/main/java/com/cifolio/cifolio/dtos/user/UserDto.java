package com.cifolio.cifolio.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String email;
}
