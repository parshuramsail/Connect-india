package com.stackroute.slotservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IDNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String message;


}
