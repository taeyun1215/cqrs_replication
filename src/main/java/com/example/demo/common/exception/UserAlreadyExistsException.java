package com.example.demo.common.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException() {
        super(ErrorMessage.USER_DUPLICATE.getMessage());
    }
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
