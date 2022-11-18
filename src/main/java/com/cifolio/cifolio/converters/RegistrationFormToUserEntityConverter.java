package com.cifolio.cifolio.converters;
import com.cifolio.cifolio.dto.RegistrationForm;
import com.cifolio.cifolio.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.function.Function;

import static com.cifolio.cifolio.constants.UserConstants.DEFAULT_USER_ROLE;

@Component
@RequiredArgsConstructor
public class RegistrationFormToUserEntityConverter implements Function<RegistrationForm, User> {
    private final PasswordEncoder passwordEncoder;
    @Override
    public User apply(RegistrationForm registrationForm) {
        return convertToCityEntity(registrationForm);
    }

    private User convertToCityEntity(RegistrationForm registrationForm) {
        User user = new User();
        user.setUsername(registrationForm.getUsername());
        user.setEmail(registrationForm.getEmail());
        user.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
        user.setRole(DEFAULT_USER_ROLE);
        return user;
    }
}