package com.stackroute.helpDeskservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TicketIdNotFoundException extends RuntimeException{
    private String message;
}
