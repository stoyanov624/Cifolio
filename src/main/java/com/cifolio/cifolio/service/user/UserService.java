package com.cifolio.cifolio.service.user;

import com.cifolio.cifolio.models.user.User;
import com.cifolio.cifolio.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void registerUser(User user) {
        userRepository.save(user);
    }
}
