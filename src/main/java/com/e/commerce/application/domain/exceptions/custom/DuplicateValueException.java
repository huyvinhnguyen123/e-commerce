package com.e.commerce.application.domain.exceptions.custom;

public class DuplicateValueException extends RuntimeException {
    public DuplicateValueException() {}
    public DuplicateValueException(String message) {
        super(message);
    }
}
