package com.e.commerce.application.web.api;

import com.e.commerce.application.domain.dtos.user.UserData;
import com.e.commerce.application.domain.dtos.user.UserDataInput;
import com.e.commerce.application.domain.dtos.user.UserImageData;
import com.e.commerce.application.domain.exceptions.HandleRequest;
import com.e.commerce.application.domain.services.admin.AdminService;
import com.e.commerce.application.domain.services.admin.RoleService;
import com.e.commerce.application.domain.services.file.TypeService;
import com.e.commerce.application.domain.utils.constant.Logger;
import com.e.commerce.application.web.common.AuthenticatorECommerce;
import com.e.commerce.application.web.response.ResponseDto;
import com.e.commerce.application.web.response.base.BaseResponse;
import com.e.commerce.application.web.response.user.UserImageResponse;
import com.e.commerce.application.web.response.user.UserMapper;
import com.e.commerce.application.web.response.user.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class AdminController {
    private final AdminService adminService;
    private final RoleService roleService;
    private final TypeService typeService;
    private final AuthenticatorECommerce authenticatorECommerce;

    @PostMapping("/create-default/roles")
    public ResponseEntity<ResponseDto<Object>> createDefaultRole() {
        log.info(Logger.requestApi("createDefaultRole()"));
        roleService.createAndCheckRoleDuplicate();
        return ResponseEntity.ok(ResponseDto.build().withMessage("OK"));
    }

    @PostMapping("e-commerce/create-system")
    public ResponseEntity<ResponseDto<Object>> createSystem(@Valid @RequestBody UserDataInput userDataInput,
                                                            BindingResult bindingResult) throws Exception {
        log.info(Logger.requestApi("createSystem()"));

        // Check for validation errors in the input
        if (bindingResult.hasErrors()) {
            Map<String, String> fieldErrors = new HashMap<>();
            return HandleRequest.validateRequest(HttpStatus.BAD_REQUEST, fieldErrors, bindingResult);
        }

        adminService.createAndCheckUserRoleSystemDuplicate(userDataInput);
        return ResponseEntity.ok(ResponseDto.build().withMessage("OK"));
    }

    @PostMapping("admin/create")
    @PreAuthorize("hasAnyRole('ADMIN','SYSTEM')")
    public ResponseEntity<ResponseDto<Object>> createAdmin(@Valid @RequestBody UserDataInput userDataInput,
                                                           BindingResult bindingResult) {
        log.info(Logger.requestApi("createAdmin()"));

        // Check for validation errors in the input
        if (bindingResult.hasErrors()) {
            Map<String, String> fieldErrors = new HashMap<>();
            return HandleRequest.validateRequest(HttpStatus.BAD_REQUEST, fieldErrors, bindingResult);
        }

        adminService.createAdmin(userDataInput);
        return ResponseEntity.ok(ResponseDto.build().withMessage("OK"));
    }

    @GetMapping("/admin/users")
    @PreAuthorize("hasAnyRole('ADMIN','SYSTEM')")
    public ResponseEntity<ResponseDto<Object>> findAllUsers(@RequestParam String roleCode,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "1") int size) {
        log.info(Logger.requestApi("findAllUsers()"));

        String actualRole = authenticatorECommerce.extractRole(roleCode);

        Pageable pageable = PageRequest.of(page, size, Sort.by("username").ascending());
        Page<UserData> userDataPage = adminService.findAllUsers(actualRole, pageable);
        Long totalUsers = userDataPage.getTotalElements();
        int totalPages = userDataPage.getTotalPages();
        BaseResponse baseResponse = new BaseResponse(page, size, totalPages);
        UserResponse userResponse = UserMapper.mapToUser(userDataPage.getContent(), totalUsers, baseResponse);

        return ResponseEntity.ok(ResponseDto.build().withData(userResponse));
    }

    @GetMapping("/admin/users-image")
    @PreAuthorize("hasAnyRole('ADMIN','SYSTEM')")
    public ResponseEntity<ResponseDto<Object>> findAllUsersWithImage(@RequestParam String roleCode,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "1") int size) {
        log.info(Logger.requestApi("findAllUsersWithImage()"));

        String actualRole = authenticatorECommerce.extractRole(roleCode);

        Pageable pageable = PageRequest.of(page, size, Sort.by("username").ascending());
        Page<UserImageData> userDataPage = adminService.findAllUsersWithImage(actualRole, pageable);
        Long totalUsers = userDataPage.getTotalElements();
        int totalPages = userDataPage.getTotalPages();
        BaseResponse baseResponse = new BaseResponse(page, size, totalPages);
        UserImageResponse userResponse = UserMapper.mapToUserImage(userDataPage.getContent(), totalUsers, baseResponse);

        return ResponseEntity.ok(ResponseDto.build().withData(userResponse));
    }

    @PostMapping("/admin/create-default-types")
    @PreAuthorize("hasAnyRole('ADMIN','SYSTEM')")
    public ResponseEntity<ResponseDto<Object>> createDefaultType() {
        log.info(Logger.requestApi("createDefaultType()"));
        typeService.createAndCheckTypeDuplicate();
        return ResponseEntity.ok(ResponseDto.build().withMessage("OK"));
    }
}
