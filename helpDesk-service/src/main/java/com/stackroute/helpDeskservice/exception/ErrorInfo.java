package com.stackroute.helpDeskservice.exception;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorInfo {
	
	private String message;
	private LocalDate timestamp;
	private String details;
	private HttpStatus status;

}
