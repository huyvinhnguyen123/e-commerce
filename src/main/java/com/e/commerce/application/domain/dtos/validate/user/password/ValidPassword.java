package com.e.commerce.application.domain.dtos.validate.user.password;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordChecker.class)
public @interface ValidPassword {
    String message() default "{inValidPassword}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}