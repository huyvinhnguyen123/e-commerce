package com.e.commerce.application.domain.dtos.validate.user.birthdate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BirthdateChecker.class)
public @interface ValidBirthdate {
    String message() default "{ValidBirthDay}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
