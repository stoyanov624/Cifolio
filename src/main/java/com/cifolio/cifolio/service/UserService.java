package com.cifolio.cifolio.service;

import com.cifolio.cifolio.constants.UserConstants;
import com.cifolio.cifolio.model.Role;
import com.cifolio.cifolio.model.User;
import com.cifolio.cifolio.repository.RoleRepository;
import com.cifolio.cifolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.cifolio.cifolio.constants.UserConstants.DEFAULT_USER_ROLE;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public void registerUser(User user) {
        Role role = roleRepository.findByName(DEFAULT_USER_ROLE)
                .orElseThrow(() -> new IllegalStateException("Default role user not found!"));
        user.getRoles().add(role);
        userRepository.save(user);
    }
}
