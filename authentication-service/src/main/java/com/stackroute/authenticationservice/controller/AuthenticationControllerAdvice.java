package com.stackroute.authenticationservice.controller;


import com.stackroute.authenticationservice.exception.BadCredentialsException;
import com.stackroute.authenticationservice.exception.CustomUsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthenticationControllerAdvice {

    @ExceptionHandler(BadCredentialsException.class)
    public String badCredentialException(BadCredentialsException e) {
        return e.getMessage();
    }


    @ExceptionHandler(CustomUsernameNotFoundException.class)
    public String customerNotFound(CustomUsernameNotFoundException e) {
        return e.getMessage();
    }


}
