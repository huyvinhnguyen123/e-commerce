package com.e.commerce.application.domain.dtos.validate.user.username;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameChecker.class)
public @interface ValidUsername {
    String message() default "{inValidUsername}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
