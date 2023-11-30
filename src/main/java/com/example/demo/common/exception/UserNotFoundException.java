package com.example.demo.common.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super(ErrorMessage.USER_NOTFOUND.getMessage());
    }
    public UserNotFoundException(String message) {
        super(message);
    }
}
