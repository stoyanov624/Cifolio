package com.cifolio.cifolio.dto.user;

import lombok.Data;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String email;
}
