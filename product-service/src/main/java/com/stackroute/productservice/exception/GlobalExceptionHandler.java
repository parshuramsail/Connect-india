package com.stackroute.productservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler {

	/*
	 * Custom Exception
	 * @description: Handles the IdNotFoundException
	 * @param exception, webRequest: IdNotFoundException and WebRequest as Parameter
	 * @return errorResponse: Customized error response
	 * */
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ErrorInfo> idNotFoundException(IdNotFoundException exception, WebRequest webrequest){
		
		ErrorInfo errorinfo = new ErrorInfo();
		errorinfo.setTimestamp(LocalDate.now());
		errorinfo.setMessage(exception.getMessage());
		errorinfo.setDetails(webrequest.getDescription(false));
		errorinfo.setStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
		errorinfo.setPath(webrequest.getDescription(false));
		
		return new ResponseEntity<>(errorinfo, HttpStatus.NOT_FOUND);
	}

	/*
	 * Custom Exception
	 * @description: Handles the ProductNotFoundException
	 * @param exception, webRequest: ProductNotFoundException and WebRequest as Parameter
	 * @return errorResponse: Customized error response
	 * */
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorInfo> productNotFoundException(ProductNotFoundException exception, WebRequest webrequest){

		ErrorInfo errorinfo = new ErrorInfo();
		errorinfo.setTimestamp(LocalDate.now());
		errorinfo.setMessage(exception.getMessage());
		errorinfo.setDetails(webrequest.getDescription(false));
		errorinfo.setStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
		errorinfo.setPath(webrequest.getDescription(false));

		return new ResponseEntity<>(errorinfo, HttpStatus.NOT_FOUND);
	}
	/*
	 * Application Exception
	 * @description: Handles the Application Exception
	 * @param exception, webRequest: Exception and WebRequest as Parameter
	 * @return errorResponse: Customized error response
	 * */
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> exception(Exception exception, WebRequest webrequest){
		
		
		ErrorInfo errorinfo = new ErrorInfo();
		errorinfo.setTimestamp(LocalDate.now());
		errorinfo.setMessage(exception.getMessage());
		errorinfo.setDetails(webrequest.getDescription(false));
		errorinfo.setStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
		errorinfo.setPath(webrequest.getDescription(false));

		
		return new ResponseEntity<>(errorinfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
