package com.cifolio.cifolio.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {
    private String username;
    private String password;
    private String email;
}
