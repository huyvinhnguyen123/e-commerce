package com.e.commerce.application.domain.dtos.validate.user.birthdate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BirthdateChecker implements ConstraintValidator<ValidBirthdate, String> {
    private static final java.lang.String CONTENT_VALID_MESSAGE = "{inValidBirthDay}";

    @Override
    public boolean isValid(java.lang.String birthDay, ConstraintValidatorContext context) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        try {
            LocalDate.parse(birthDay, formatter);
        } catch (DateTimeParseException e) {
            addErrorMessage(context);
            return false;
        }
        return true;
    }

    private void addErrorMessage(ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(BirthdateChecker.CONTENT_VALID_MESSAGE)
                .addConstraintViolation();
    }
}