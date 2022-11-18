package com.cifolio.cifolio.service.user;

import com.cifolio.cifolio.model.user.User;
import com.cifolio.cifolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.cifolio.cifolio.constants.UserConstants.DEFAULT_USER_ROLE;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void registerUser(User user) {
        userRepository.save(user);
    }
}
