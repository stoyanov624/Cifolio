package com.cifolio.cifolio.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    String role;
}
