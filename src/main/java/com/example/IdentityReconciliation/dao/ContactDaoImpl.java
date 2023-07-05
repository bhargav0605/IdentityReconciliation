/**
 * Author: Bhargav Parmar
 * Email: bhargavparmar7080@gmail.com
 */
package com.example.IdentityReconciliation.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.IdentityReconciliation.dto.IdentityRequest;
import com.example.IdentityReconciliation.model.Contact;

@Repository
public class ContactDaoImpl implements ContactDao{
	
	private static Logger logger = LoggerFactory.getLogger(ContactDaoImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Contact> getContact(IdentityRequest identityRequest) {
		
		logger.info("Request body is: "+identityRequest);
		String phoneNumber = Long.toString(identityRequest.getPhoneNumber());
		String email = identityRequest.getEmail();
		

		String hql = "";
		List<Contact> contact = null;
		
		/**
		 * If contact list is empty means no contact found than create one so 
		 * no exception will be there for now.
		 */
		
		if(email != null && !phoneNumber.equals("0")) {
			hql = 
					"SELECT e FROM Contact e WHERE e.phoneNumber = :phoneNumber "
					+ "OR "
					+ "e.email = :email";
			
			logger.info("Email and Phone Number available in request body.");
			logger.info("Query is: "+hql);
			
			contact = entityManager.createQuery(hql, Contact.class)
					.setParameter("phoneNumber", phoneNumber)
					.setParameter("email", email)
					.getResultList();
			
			if(contact == null) {
				System.out.println("Create contact");
			}
			
		} else if (email == null && !phoneNumber.equals("0")) {
			System.out.println("email is null, phone number is not empty");
			hql = "SELECT c FROM Contact c WHERE c.email IN (SELECT c2.email FROM Contact c2 WHERE c2.phoneNumber = :phoneNumber)";
			
			logger.info("Email is null and Phone Number available in request body.");
			logger.info("Query is: "+hql);
			
			contact = entityManager.createQuery(hql, Contact.class)
					.setParameter("phoneNumber", phoneNumber)
					.getResultList();
			if(contact == null) {
				System.out.println("Create contact");
			}
			
		} else if (email != null && phoneNumber.equals("0")){
//			System.out.println("email is not null, phone number is empty");
			hql = "SELECT c FROM Contact c WHERE c.phoneNumber IN (SELECT c2.phoneNumber FROM Contact c2 WHERE c2.email = :email)";
			
			logger.info("Email availabe and Phone Number is null in request body.");
			logger.info("Query is: "+hql);
			
			contact = entityManager.createQuery(hql, Contact.class)
					.setParameter("email", email)
					.getResultList();
			if(contact == null) {
				System.out.println("Create contact");
			}
		}

		return contact;
	}
}
