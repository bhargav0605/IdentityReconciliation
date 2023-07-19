/**
 * Author: Bhargav Parmar
 * Email: bhargavparmar7080@gmail.com
 */
package com.example.IdentityReconciliation.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.IdentityReconciliation.dto.IdentityRequest;
import com.example.IdentityReconciliation.dto.IdentityResponse;
import com.example.IdentityReconciliation.exception.MissingParameterException;
import com.example.IdentityReconciliation.service.IdentityServiceImpl;

@RestController
@Validated
public class IdentityController {
	
	public static Logger logger = LoggerFactory.getLogger(IdentityController.class);
	
	@Autowired
	private IdentityServiceImpl identityService;
	
	@PostMapping("/identify")
	public ResponseEntity<Object> identify(@Valid @RequestBody(required = false) IdentityRequest identityRequest) throws MissingParameterException{
		
		IdentityResponse identityResponse = null;
		if(identityRequest == null || (identityRequest.getPhoneNumber()==0 && (identityRequest.getEmail()=="" || identityRequest.getEmail()==null) ) ) {
			throw new MissingParameterException("No Parameters are present.");
		} else {
			identityResponse = identityService.identify(identityRequest);
			return new ResponseEntity<Object>(identityResponse, HttpStatus.OK);
		}
	}
}
