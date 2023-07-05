/**
 * Author: Bhargav Parmar
 * Email: bhargavparmar7080@gmail.com
 */
package com.example.IdentityReconciliation.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.IdentityReconciliation.dao.ContactDaoImpl;
import com.example.IdentityReconciliation.dto.ContactResponse;
import com.example.IdentityReconciliation.dto.IdentityRequest;
import com.example.IdentityReconciliation.dto.IdentityResponse;
import com.example.IdentityReconciliation.exception.MissingParameterException;
import com.example.IdentityReconciliation.model.Contact;

@Service
public class IdentityServiceImpl implements IdentityService{
	
	private static Logger logger = LoggerFactory.getLogger(IdentityServiceImpl.class);
	
	@Autowired
	private ContactDaoImpl contactDaoImpl;

	@Override
	public IdentityResponse identify(IdentityRequest identityRequest) {
		
		IdentityResponse identityResponse = null;
		
			List<Contact> contacts = contactDaoImpl.getContact(identityRequest);
			if(contacts == null) {
				throw new MissingParameterException("Parameters are Missing");
			} else {
				
				logger.info("Contacts found: "+contacts);
				
				// Email and phoneNumber
				List<String> email = new ArrayList<>();
				List<String> phoneNumbers = new ArrayList<>();
				
				List<Integer> secondaryContactIds = new ArrayList<>();
				
				ContactResponse contactResponse = new ContactResponse();
				
				for (Contact contact : contacts) {

					if(!email.contains(contact.getEmail())) {
						email.add(contact.getEmail());
					}
					if(!phoneNumbers.contains(contact.getPhoneNumber())){
						phoneNumbers.add(contact.getPhoneNumber());
					}
					if(contact.getLinkPrecendence().toString() == "PRIMARY") {
						contactResponse.setPrimaryContatctId(contact.getId());
					} else {
						secondaryContactIds.add(contact.getId());
					}
				}

				contactResponse.setEmails(email);
				contactResponse.setPhoneNumbers(phoneNumbers);
				contactResponse.setSecondaryContactIds(secondaryContactIds);
				
				identityResponse = new IdentityResponse();
				identityResponse.setContact(contactResponse);
				
				return identityResponse;
				
			}
			
		
	}
	
}
