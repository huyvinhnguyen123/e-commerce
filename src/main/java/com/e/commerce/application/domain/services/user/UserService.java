package com.e.commerce.application.domain.services.user;

import com.e.commerce.application.domain.entities.User;
import com.e.commerce.application.domain.repositories.UserRepository;
import com.e.commerce.application.domain.utils.constant.Logger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findUserById(String userId) {
        User existUser = userRepository.findById(userId).orElseThrow(
                () -> {
                    log.error("Not found this id: {}", userId);
                    return new NullPointerException("Not found this id: " + userId);
                }
        );

        log.info("Found user's id");
        return existUser;
    }

    private User findUserByEmail(String email) {
        User existUser = userRepository.findUserByEmail(email).orElseThrow(
                () -> {
                    log.error("Not found this mail: {}", email);
                    return new NullPointerException("Not found this mail: " + email);
                }
        );

        log.info(Logger.findObjectSuccess("user's mail"));
        return existUser;
    }

    private User findUserByOldLoginId(String email) {
        User existUser = userRepository.findUserByOldLoginId(email).orElseThrow(
                () -> {
                    log.error("Not found this user: {}", email);
                    return new NullPointerException("Not found this user: " + email);
                }
        );

        log.info(Logger.findObjectSuccess("user's login id"));
        return existUser;
    }


}
