package com.e.commerce.application.domain.dtos.user;

import com.e.commerce.application.domain.dtos.validate.user.password.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginInput {
    @NotNull(message = "{user.loginId.require}")
    @NotEmpty(message = "{user.loginId.require}")
    @Email
    private String email;

    @NotNull(message = "{user.password.require}")
    @NotEmpty(message = "{user.password.require}")
    @ValidPassword
    private String password;
}
