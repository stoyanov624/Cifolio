package com.cifolio.cifolio.converters;
import com.cifolio.cifolio.dto.RegistrationForm;
import com.cifolio.cifolio.model.User;
import org.springframework.stereotype.Component;
import java.util.function.Function;

@Component
public class RegistrationFormToUserEntityConverter implements Function<RegistrationForm, User> {
    @Override
    public User apply(RegistrationForm registrationForm) {
        return convertToCityEntity(registrationForm);
    }

    private User convertToCityEntity(RegistrationForm registrationForm) {
        User user = new User();
        user.setUsername(registrationForm.getUsername());
        user.setEmail(registrationForm.getEmail());
        user.setPassword(registrationForm.getPassword());
        return user;
    }
}