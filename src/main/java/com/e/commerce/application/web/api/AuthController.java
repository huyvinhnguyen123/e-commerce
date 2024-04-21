package com.e.commerce.application.web.api;

import com.e.commerce.application.config.security.JwtUtils;
import com.e.commerce.application.domain.exceptions.HandleRequest;
import com.e.commerce.application.domain.services.admin.PermissionService;
import com.e.commerce.application.domain.services.auth.AuthService;
import com.e.commerce.application.domain.dtos.user.LoginInput;
import com.e.commerce.application.domain.dtos.user.UserDataInput;
import com.e.commerce.application.domain.utils.constant.Logger;
import com.e.commerce.application.web.response.ResponseDto;
import com.e.commerce.application.web.response.login.LoginMapper;
import com.e.commerce.application.web.response.login.LoginResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class AuthController {
    private final AuthService authService;
    private final PermissionService permissionService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto<Object>> createUser(@Valid @RequestBody UserDataInput userDataInput,
                                                          BindingResult bindingResult) {
        log.info(Logger.requestApi("createUser()"));

        // Check for validation errors in the input
        if (bindingResult.hasErrors()) {
            Map<String, String> fieldErrors = new HashMap<>();
            return HandleRequest.validateRequest(HttpStatus.BAD_REQUEST, fieldErrors, bindingResult);
        }

        authService.createUser(userDataInput);
        return ResponseEntity.ok(ResponseDto.build().withMessage("OK"));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<Object>> login(@Valid @RequestBody LoginInput loginInput,
                                                     BindingResult bindingResult) {
        log.info(Logger.requestApi("login()"));

        // Check for validation errors in the input
        if (bindingResult.hasErrors()) {
            Map<String, String> fieldErrors = new HashMap<>();
            return HandleRequest.validateRequest(HttpStatus.BAD_REQUEST, fieldErrors, bindingResult);
        }

        String token = authService.authenticate(loginInput, authenticationManager, jwtUtils);
        String refreshToken = UUID.randomUUID().toString();
        LoginResponse loginResponse = LoginMapper.mapToLogin(token, refreshToken);
        log.info("Login successfully");
        return ResponseEntity.ok(ResponseDto.build().withData(loginResponse));
    }
}
