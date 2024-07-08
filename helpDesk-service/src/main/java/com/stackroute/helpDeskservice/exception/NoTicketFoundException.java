package com.stackroute.helpDeskservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NoTicketFoundException extends RuntimeException{
    private String message;
}
