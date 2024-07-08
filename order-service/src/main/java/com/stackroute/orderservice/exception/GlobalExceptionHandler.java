
 
package com.stackroute.orderservice.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * @description : Handles the custom exception ResourceNotFoundException
	 * @param exception, webRequest : ResourceNotFoundException and WebRequest as
	 *                   Parameters
	 * @return errorResponse : Customized Error Response
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> hanldeResourceNotFoundException(ResourceNotFoundException exception,
			WebRequest webRequest) {

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.NOT_FOUND);
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setPath(webRequest.getDescription(false));

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

	}

	/**
	 * @description : Handles the Application Exceptions
	 * @param exception, webRequest : Exception and WebRequest as Parameters
	 * @return errorResponse : Customized Error Response
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception exception, WebRequest webRequest) {

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now());
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setPath(webRequest.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
