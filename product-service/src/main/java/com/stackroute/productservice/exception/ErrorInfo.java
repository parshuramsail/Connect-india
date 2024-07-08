package com.stackroute.productservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorInfo {

	private LocalDate timestamp;
	private String message;
	private String details;
	private  String status;
	private String path;
	

}
