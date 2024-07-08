package com.stackroute.authenticationservice.exception;


public class BadCredentialsException extends Exception {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public BadCredentialsException(String message) {
        this.message = message;
    }
}
