package com.example.IdentityReconciliation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.IdentityReconciliation.dto.IdentityRequest;
import com.example.IdentityReconciliation.model.Contact;
import com.example.IdentityReconciliation.service.IdentityServiceImpl;

@RestController
@RequestMapping("/api")
public class IdentityController {
	
	@Autowired
	private IdentityServiceImpl identityService;
	
	
	@PostMapping("/identify")
	public List<Contact> identify(@RequestBody IdentityRequest identityRequest) {
		System.out.println(identityRequest);
		return identityService.identify();
	}
}
