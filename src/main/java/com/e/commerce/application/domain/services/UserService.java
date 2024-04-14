package com.e.commerce.application.domain.services;

import com.e.commerce.application.domain.entities.Role;
import com.e.commerce.application.domain.entities.User;
import com.e.commerce.application.domain.repositories.UserRepository;
import com.e.commerce.application.dtos.UserDataInput;
import com.e.commerce.application.utils.enums.DeleteFlag;
import com.e.commerce.application.utils.enums.LockFlag;
import com.e.commerce.application.utils.enums.RoleData;
import com.e.commerce.application.utils.formats.CustomDateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

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

        log.info("Found user's mail");
        return existUser;
    }

    private User findUserByOldLoginId(String email) {
        User existUser = userRepository.findUserByOldLoginId(email).orElseThrow(
                () -> {
                    log.error("Not found this user: {}", email);
                    return new NullPointerException("Not found this user: " + email);
                }
        );

        log.info("Found user");
        return existUser;
    }

    /**
     * Create user role will set default user
     * @param userDataInput - input user register
     */
    public void createUser(UserDataInput userDataInput) {
        User user = new User();
        user.setUserName(userDataInput.getName());
        user.setBirthDate(CustomDateTimeFormatter.dateStringFormatter(userDataInput.getBirthDate()));
        user.setEmail(userDataInput.getEmail());
        user.setPassword(userDataInput.getPassword());
        user.setLockFlag(LockFlag.NON_LOCK.getCode());
        user.setDeleteFlag(DeleteFlag.NON_DELETE.getCode());

        Role existRole = roleService.findRoleByName(RoleData.USER.getRole());
        user.setRole(existRole);

        userRepository.save(user);
        log.info("Save new user success");
    }
}
