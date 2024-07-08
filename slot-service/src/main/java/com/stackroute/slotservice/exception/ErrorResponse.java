package com.stackroute.slotservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
    private String path;

}
