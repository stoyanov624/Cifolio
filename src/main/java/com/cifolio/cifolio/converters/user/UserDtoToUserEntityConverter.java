package com.cifolio.cifolio.converters.user;

import com.cifolio.cifolio.dtos.user.UserDto;
import com.cifolio.cifolio.models.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.function.Function;


@Component
@RequiredArgsConstructor
public class UserDtoToUserEntityConverter implements Function<UserDto, User> {
    private final PasswordEncoder passwordEncoder;
    @Override
    public User apply(UserDto userDto) {
        return convertToUserEntity(userDto);
    }

    private User convertToUserEntity(UserDto userDto) {
        return User.builder()
            .username(userDto.getUsername())
            .email(userDto.getEmail())
            .password(passwordEncoder.encode(userDto.getPassword()))
            .build();
    }
}