/**
 * Author: Bhargav Parmar
 * Email: bhargavparmar7080@gmail.com
 */
package com.example.IdentityReconciliation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.IdentityReconciliation.dto.IdentityRequest;
import com.example.IdentityReconciliation.dto.IdentityResponse;
import com.example.IdentityReconciliation.service.IdentityServiceImpl;

@RestController
@RequestMapping("/api")
public class IdentityController {
	
	@Autowired
	private IdentityServiceImpl identityService;
	
	@PostMapping("/identify")
	public IdentityResponse identify(@RequestBody IdentityRequest identityRequest) {
		IdentityResponse identityResponse = identityService.identify(identityRequest);
		return identityResponse;
	}

}
