package com.e.commerce.application.web;

import com.e.commerce.application.domain.services.UserService;
import com.e.commerce.application.dtos.UserDataInput;
import com.e.commerce.application.web.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto<Object>> createUser(UserDataInput userDataInput) {
        log.info("Request creating user...");
        userService.createUser(userDataInput);
        return ResponseEntity.ok(ResponseDto.build().withMessage("OK"));
    }
}
