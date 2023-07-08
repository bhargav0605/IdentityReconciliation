/**
 * Author: Bhargav Parmar
 * Email: bhargavparmar7080@gmail.com
 */
package com.example.IdentityReconciliation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.IdentityReconciliation.dto.IdentityRequest;
import com.example.IdentityReconciliation.dto.IdentityResponse;
import com.example.IdentityReconciliation.exception.MissingParameterException;
import com.example.IdentityReconciliation.service.IdentityServiceImpl;



@RestController
@RequestMapping("/api")
public class IdentityController {
	
	public static Logger logger = LoggerFactory.getLogger(IdentityController.class);
	
	@Autowired
	private IdentityServiceImpl identityService;
	
	@PostMapping("/identify")
	public ResponseEntity<Object> identify(@RequestBody IdentityRequest identityRequest) throws MissingParameterException{

		IdentityResponse identityResponse = null;
		String errorMessage = new String();
		try {

			identityResponse = identityService.identify(identityRequest);
			return new ResponseEntity<Object>(identityResponse, HttpStatus.OK);
			
		} catch (MissingParameterException e) {
			logger.error(e.getMessage());
			errorMessage = e.getMessage();
		}
		return new ResponseEntity<Object>(errorMessage, HttpStatus.BAD_REQUEST);
	}
}
