package com.example.IdentityReconciliation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.IdentityReconciliation.model.Contact;
import com.example.IdentityReconciliation.service.IdentityService;

@RestController
@RequestMapping("/api")
public class IdentityController {
	
	@Autowired
	private IdentityService identityService;
	
	
	@PostMapping("/identify")
	public List<Contact> hello() {
		return identityService.identify();
	}
}
