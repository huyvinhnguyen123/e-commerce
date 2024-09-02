package com.e.commerce.application.domain.dtos.validate.user.username;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameChecker implements ConstraintValidator<ValidUsername, String> {
    private static final String CONTENT_VALID_MESSAGE = "{inValidUsername.number.format}";
    private static final String REQUIRE_MESSAGE = "{user.username.require}";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || value.isEmpty()) {
            addErrorMessage(context, REQUIRE_MESSAGE);
            return false;
        }

        char firstCharacter = value.charAt(0);
        if(firstCharacter >= '0' && firstCharacter < '9') {
            addErrorMessage(context, CONTENT_VALID_MESSAGE);
            return false;
        }

        return true;
    }

    private void addErrorMessage(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}
