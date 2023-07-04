package com.example.IdentityReconciliation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.IdentityReconciliation.dao.ContactDaoImpl;
import com.example.IdentityReconciliation.model.Contact;

@Service
public class IdentityServiceImpl implements IdentityService{
	
	@Autowired
	private ContactDaoImpl contactDaoImpl;

	@Override
	public List<Contact> identify() {
		// TODO Auto-generated method stub
		return contactDaoImpl.getContact();
	}
	
}
