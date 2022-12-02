package com.cifolio.cifolio.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static com.cifolio.cifolio.constants.UserConstants.USER_ROLE;
import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    @Id  @GeneratedValue(strategy = IDENTITY)
    Long id;

    @Column
    String username;

    @Column
    String password;

    @Column
    String email;

    @Column
    @Builder.Default
    String role = USER_ROLE;
}
