package com.insomniacoder.authservice.user.exception;

public class UserNotFoundException extends Throwable {

    private String message;

    public UserNotFoundException(Long id, String message) {
        this.message = message + id;
    }
}
