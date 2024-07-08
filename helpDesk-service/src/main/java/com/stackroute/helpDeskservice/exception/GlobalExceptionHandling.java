package com.stackroute.helpDeskservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(TicketIdNotFoundException.class)
    public ResponseEntity<ErrorInfo> ticketIdNotFound(TicketIdNotFoundException ticketIdNotFoundException, WebRequest webRequest) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage(ticketIdNotFoundException.getMessage());
        errorInfo.setTimestamp(LocalDate.now());
        errorInfo.setDetails(webRequest.getDescription(false));
        errorInfo.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoTicketFoundException.class)
    public ResponseEntity<ErrorInfo> noTicketFound(NoTicketFoundException noTicketFoundException, WebRequest webRequest) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage(noTicketFoundException.getMessage());
        errorInfo.setTimestamp(LocalDate.now());
        errorInfo.setDetails(webRequest.getDescription(false));
        errorInfo.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }
}
