/**
 * Author: Bhargav Parmar
 * Email: bhargavparmar7080@gmail.com
 */
package com.example.IdentityReconciliation.controller;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.IdentityReconciliation.exception.MissingParameterException;
import com.example.IdentityReconciliation.model.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionControllerAdvice {
	
	@ExceptionHandler()
	public ResponseEntity<Object> missingParameter(MissingParameterException e) {
		ErrorResponse err = new ErrorResponse();
		err.setStatusCode(HttpStatus.BAD_REQUEST.value());
		err.setMessage(e.getMessage());
		err.setTime(Instant.ofEpochMilli(System.currentTimeMillis()));
		
		return new ResponseEntity<Object>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<Object> allException(Exception e){
		ErrorResponse err = new ErrorResponse();
		err.setStatusCode(HttpStatus.BAD_REQUEST.value());
		err.setMessage(e.getMessage());
		err.setTime(Instant.ofEpochMilli(System.currentTimeMillis()));
		
		return new ResponseEntity<Object>(err, HttpStatus.BAD_REQUEST);
	}

}
