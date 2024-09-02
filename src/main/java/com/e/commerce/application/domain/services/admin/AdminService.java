package com.e.commerce.application.domain.services.admin;

import com.e.commerce.application.config.security.PasswordEncrypt;
import com.e.commerce.application.domain.dtos.user.UserData;
import com.e.commerce.application.domain.dtos.user.UserDataInput;
import com.e.commerce.application.domain.dtos.user.UserImageData;
import com.e.commerce.application.domain.entities.Role;
import com.e.commerce.application.domain.entities.User;
import com.e.commerce.application.domain.exceptions.custom.DuplicateValueException;
import com.e.commerce.application.domain.repositories.UserRepository;
import com.e.commerce.application.domain.repositories.object.UserSelect;
import com.e.commerce.application.domain.utils.constant.Logger;
import com.e.commerce.application.domain.utils.enums.DeleteFlag;
import com.e.commerce.application.domain.utils.enums.LockFlag;
import com.e.commerce.application.domain.utils.enums.RoleData;
import com.e.commerce.application.domain.utils.formats.CustomDateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public void createAndCheckUserRoleSystemDuplicate(UserDataInput userDataInput) {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
           createSystem(userDataInput);
        } else {
            for (User user: users) {
                if(user.getRole().getRoleName().equals("ROLE_SYSTEM")) {
                    log.error(Logger.createObjectFail("user"));
                    log.info("user with role system is existed");
                    throw new DuplicateValueException("user with role system is existed");
                } else {
                    createSystem(userDataInput);
                }
            }
        }
    }

    private void createSystem(UserDataInput userDataInput) {
        User user = new User();
        user.setUserName(userDataInput.getName());
        user.setBirthDate(CustomDateTimeFormatter.dateStringFormatter(userDataInput.getBirthDate()));
        user.setEmail(userDataInput.getEmail());
        user.setPassword(PasswordEncrypt.bcryptPassword(userDataInput.getPassword()));
        user.setLockFlag(LockFlag.NON_LOCK.getCode());
        user.setDeleteFlag(DeleteFlag.NON_DELETE.getCode());

        Role existRole = roleService.findRoleByName(RoleData.SYSTEM.getRole());
        user.setRole(existRole);

        userRepository.save(user);
        log.info(Logger.createObjectSuccess("User"));
    }

    public void createAdmin(UserDataInput userDataInput) {
        User user = new User();
        user.setUserName(userDataInput.getName());
        user.setBirthDate(CustomDateTimeFormatter.dateStringFormatter(userDataInput.getBirthDate()));
        user.setEmail(userDataInput.getEmail());
        user.setPassword(PasswordEncrypt.bcryptPassword(userDataInput.getPassword()));
        user.setLockFlag(LockFlag.NON_LOCK.getCode());
        user.setDeleteFlag(DeleteFlag.NON_DELETE.getCode());

        Role existRole = roleService.findRoleByName(RoleData.ADMIN.getRole());
        user.setRole(existRole);

        userRepository.save(user);
        log.info(Logger.createObjectSuccess("User"));
    }

    public Page<UserData> findAllUsers(String role, Pageable pageable) {
        Page<UserSelect> users = userRepository.findAllUsers(role, pageable);

        return users.map(user -> {
           UserData userData = new UserData();
           userData.setId(user.getUser_id());
           userData.setName(user.getUsername());
           userData.setBirthDate(user.getBirthdate());
           userData.setEmail(user.getEmail());
           userData.setRole(user.getRole_name());
           userData.setDateCreate(user.getDate_create());

           log.info(Logger.findListObjectSuccess("users"));
           return userData;
        });
    }

    public Page<UserImageData> findAllUsersWithImage(String role, Pageable pageable) {
        Page<UserSelect> users = userRepository.findAllUsersWithImage(role, pageable);

        return users.map(user -> {
            UserImageData userData = new UserImageData();
            userData.setId(user.getUser_id());
            userData.setName(user.getUsername());
            userData.setBirthDate(user.getBirthdate());
            userData.setEmail(user.getEmail());
            userData.setRole(user.getRole_name());
            userData.setDateCreate(user.getDate_create());
            userData.setImageName(user.getImageName());
            userData.setImagePath(user.getImagePath());

            log.info(Logger.findListObjectSuccess("users"));
            return userData;
        });
    }
}
