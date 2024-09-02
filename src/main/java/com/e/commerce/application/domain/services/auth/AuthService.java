package com.e.commerce.application.domain.services.auth;

import com.e.commerce.application.config.security.JwtUtils;
import com.e.commerce.application.config.security.PasswordEncrypt;
import com.e.commerce.application.domain.entities.Role;
import com.e.commerce.application.domain.entities.User;
import com.e.commerce.application.domain.repositories.UserRepository;
import com.e.commerce.application.domain.services.admin.PermissionService;
import com.e.commerce.application.domain.services.admin.RoleService;
import com.e.commerce.application.domain.dtos.user.LoginInput;
import com.e.commerce.application.domain.dtos.user.UserDataInput;
import com.e.commerce.application.domain.utils.constant.Logger;
import com.e.commerce.application.domain.utils.enums.DeleteFlag;
import com.e.commerce.application.domain.utils.enums.LockFlag;
import com.e.commerce.application.domain.utils.enums.RoleData;
import com.e.commerce.application.domain.utils.formats.CustomDateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PermissionService permissionService;

    /**
     * Create user
     *
     * @param userDataInput - input user register
     */
    public void createUser(UserDataInput userDataInput) {
        User user = new User();
        user.setUserName(userDataInput.getName());
        user.setBirthDate(CustomDateTimeFormatter.dateStringFormatter(userDataInput.getBirthDate()));
        user.setEmail(userDataInput.getEmail());
        user.setPassword(PasswordEncrypt.bcryptPassword(userDataInput.getPassword()));
        user.setLockFlag(LockFlag.NON_LOCK.getCode());
        user.setDeleteFlag(DeleteFlag.NON_DELETE.getCode());

        Role existRole = roleService.findRoleByName(RoleData.USER.getRole());
        user.setRole(existRole);

        userRepository.save(user);
        log.info(Logger.createObjectSuccess("User"));
    }

    /**
     * Authenticate user
     *
     * @param loginInput - input login inform
     */
    public String authenticate(LoginInput loginInput, AuthenticationManager authenticationManager, JwtUtils jwtUtil) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginInput.getEmail(),
                loginInput.getPassword()
        );

        Authentication login = authenticationManager.authenticate(authentication);

        User user = (User) login.getPrincipal();

        // Check if the user is deleted or locked
        if (user.getLockFlag() == 1 || user.getDeleteFlag() == 1) {
            log.error(Logger.loginFail());
        }

        String token = jwtUtil.createToken(user);

        log.info(Logger.loginSuccess());
        log.info("Create token success: {}", token);
        return token;
    }
}