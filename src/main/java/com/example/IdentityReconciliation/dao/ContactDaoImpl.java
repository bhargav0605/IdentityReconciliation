/**
 * Author: Bhargav Parmar
 * Email: bhargavparmar7080@gmail.com
 */
package com.example.IdentityReconciliation.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.IdentityReconciliation.dto.IdentityRequest;
import com.example.IdentityReconciliation.dto.IdentityResponse;
import com.example.IdentityReconciliation.enums.LinkPrecedence;
import com.example.IdentityReconciliation.exception.MissingParameterException;
import com.example.IdentityReconciliation.model.Contact;

@Repository
@Transactional
public class ContactDaoImpl implements ContactDao{
	
	private static Logger logger = LoggerFactory.getLogger(ContactDaoImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Contact> getContact(IdentityRequest identityRequest) {
		
		logger.info("Request body in service: "+identityRequest);
		String phoneNumber = Long.toString(identityRequest.getPhoneNumber());
		String email = identityRequest.getEmail();
		
		if(identityRequest.getPhoneNumber() == 0) {
			phoneNumber = null;
		}
		
		if(email == "") {
			email = null;
		}
		
		List<Contact> contact = null;
		
		// Email and Phone Number available
		if((email != null  && phoneNumber != null)) {
//			if((email != null && !phoneNumber.equals("0")) && 
//					(email != "" && !phoneNumber.equals("0"))) {
			
			logger.info("Case 1: email and phoneNumber present.");
			
			contact = getAllContact(phoneNumber, email);
			
			// Contact Null Check
			if(!contact.isEmpty()) {
				// All the code will be here 
				boolean emailFound = getSingleContact("email", email);
				boolean phoneNumberFound = getSingleContact("phoneNumber", phoneNumber);
				
				if(emailFound && phoneNumberFound) {
					
					// Checking LinkedID
					logger.info("Checking if linkedId is null for everyone or not.");
					int linkedIdNullCounter = 0;
					for(Contact c : contact) {
						if(c.getLinkedId() == null) {
							linkedIdNullCounter++;
						}
					}
					if(linkedIdNullCounter > 1) {
						
						logger.info("There is no PRIMARY and SECONDARY.");
						logger.info("Check createdAt to find older contact.");
						
						// Object controlling
						Contact c1 = contact.get(0);
						LocalDateTime dt = c1.getCreatedAt();
						for(Contact c : contact) {
							
							if(c.getCreatedAt().isBefore(dt)) {
								dt = c.getCreatedAt();
								c1 = c;
							}
						}
						
						logger.info("Oldest is: "+dt);
						logger.info("Contact with oldest date is: "+c1);
						
						// TO update "SECONDARY"
						LinkPrecedence linkPrecendence = LinkPrecedence.SECONDARY;
						LocalDateTime updatedAt = LocalDateTime.now();
						int id = c1.getId();
						
						int rowsAffected = updateContact(linkPrecendence, updatedAt, id, contact);
						logger.info("Affected rows: "+rowsAffected);
						logger.info("Contact is updated.");
						
						entityManager.flush();  // Flush changes to the database
						entityManager.clear();  // Clear the cache
						
						contact = getAllContact(phoneNumber, email);
						return contact;
						
					} else {
						logger.info("PRIMARY and SECONDARY is defined so return the response.");
						return contact;
					}
					
				} else {
					// Create contact with secondary tag
					logger.info("Create Secondary tagged contact.");
					
						int id = 0;
						LinkPrecedence linkPrecedence = LinkPrecedence.SECONDARY;
						
						// GET id of primary 
						for(Contact c : contact) {
							if(c.getLinkPrecendence()==LinkPrecedence.PRIMARY) {
								id = c.getId();
								break;
							}
						}
						
						// Create contact
						createContact(email, phoneNumber, id, linkPrecedence);
						
						logger.info("New SECONDARY contact has been created, fetching new contacts for response.");
						
						entityManager.flush();  // Flush changes to the database
						entityManager.clear();  // Clear the cache
						
						// Send new contacts
						contact = getAllContact(phoneNumber, email);
						return contact;
				}
			} else {
				//Create "Primary" contact here
				logger.info("Contacts is empty.");
				createContact(email, phoneNumber, null , LinkPrecedence.PRIMARY);
				
				entityManager.flush();  // Flush changes to the database
				entityManager.clear();  // Clear the cache
				
				// Send new contacts
				contact = getAllContact(phoneNumber, email);
				return contact;
			}
			
		} else if (
					((email != null && phoneNumber == null)) ||
					((email == null && phoneNumber != null))
				) {
//				System.out.println((email != null || email == "" ));
//				
//				System.out.println(email != null);
//				System.out.println(email == "");
				
				System.out.println((email != null && phoneNumber == null) || (email != "" && phoneNumber == null));
				System.out.println((email == null || email == "") && phoneNumber != null);

//			if (
//					(email != null && phoneNumber.equals("0")) || (email != "" && phoneNumber.equals(0)) || 
//					(email == null && !phoneNumber.equals("0")) || (email == "" && !phoneNumber.equals(0))
//				) {

			logger.info("Case 2: Any one of them is missing email: "+email+" phone: "+phoneNumber);
			contact = getAllContact(phoneNumber, email);
			if(!contact.isEmpty()) {
				return contact;
			} else {
				if(phoneNumber.equals("0") || phoneNumber == "" || phoneNumber==null) {
					phoneNumber = null;
					createContact(email, phoneNumber, null , LinkPrecedence.PRIMARY);
				} else if (email==null ||  email == ""){
					email = null;
					createContact(email, phoneNumber, null , LinkPrecedence.PRIMARY);
				}
				
				entityManager.flush();  // Flush changes to the database
				entityManager.clear();  // Clear the cache
				
				// Send new contacts
				contact = getAllContact(phoneNumber, email);
				return contact;
			}
		} else if((email == null && phoneNumber==null)){
			logger.info("Case 3: Null");
			throw new MissingParameterException("Both Parameters are missing.");
		}
		return contact;
	}
	
	// Get All Contact
	private List<Contact> getAllContact(String phoneNumber, String email){
		logger.info("In Get all contact Email: "+email+" phoneNumber: "+phoneNumber);
		String hql = "";
		List<Contact> contact = null;
		
		hql = "SELECT c FROM Contact c \r\n"
				+ "WHERE c.email = :email OR c.phoneNumber = :phoneNumber \r\n"
				+ "    OR c.email IN (SELECT c2.email FROM Contact c2 WHERE c2.phoneNumber = :phoneNumber) \r\n"
				+ "    OR c.phoneNumber IN (SELECT c3.phoneNumber FROM Contact c3 WHERE c3.email = :email)";
		
		logger.info("Get all contact query is: "+hql);
		
		contact = entityManager.createQuery(hql, Contact.class)
				.setParameter("phoneNumber", phoneNumber)
				.setParameter("email", email)
				.getResultList();
		
		logger.info("Received contact list from db is: "+contact);
		return contact;
	}
	
	// Update contact
	private int updateContact(LinkPrecedence linkPrecendence, LocalDateTime updatedAt, int id, List<Contact> contact) {
		String hqlUpdate = 
				"UPDATE Contact c SET c.linkPrecendence = :linkPrecendence, c.updatedAt = :updatedAt, c.linkedId = :linkedId WHERE c.id = :id";
		logger.info("Update contact query is: "+hqlUpdate);
		int rowsaffected = 0;
		for (Contact c : contact) {
			if(c.getId() != id) {
				rowsaffected = entityManager.createQuery(hqlUpdate)
				.setParameter("linkPrecendence", linkPrecendence)
				.setParameter("updatedAt", updatedAt)
				.setParameter("linkedId", id)
				.setParameter("id", c.getId())
				.executeUpdate();
			}
		}
		entityManager.flush();

		logger.info("Check update in db");
		return rowsaffected;
	}
	
	// Single Contact	
	private boolean getSingleContact(String key, String value) {
		String hqlSingle = "SELECT c FROM Contact c WHERE c."+key+" = :"+key+"";
		List<Contact> contact = null;
		boolean found = false;
		
		logger.info("Get status of contact is: "+hqlSingle);
		
		contact = entityManager.createQuery(hqlSingle, Contact.class)
				.setParameter(key, value)
				.getResultList();

		if(!contact.isEmpty()) {
			found = true;
			contact = null;
			return found;
		} 
		return found;
	}

	private void createContact(String email, String phoneNumber, Integer id, LinkPrecedence linkPrecedence) {

		Contact c = new Contact();

		c.setCreatedAt(LocalDateTime.now());
		c.setUpdatedAt(LocalDateTime.now());
		c.setEmail(email);
		c.setPhoneNumber(phoneNumber);
		c.setDeletedAt(null);
		c.setLinkPrecendence(linkPrecedence);
		c.setLinkedId(id);
		
//		String hqlSingle = 
//				"INSERT INTO Contact (createdAt, updatedAt, email, phoneNumber, linkPrecendence, linkedId) "
//				+ "VALUES (:createdAt, :updatedAt, :email, :phoneNumber, :linkPrecendence, :linkedId)";
//		
//		entityManager.createQuery(hqlSingle)
//		.setParameter("createdAt", c.getCreatedAt())
//		.setParameter("updatedAt", c.getUpdatedAt())
//		.setParameter("email", c.getEmail())
//		.setParameter("phoneNumber", c.getPhoneNumber())
//		.setParameter("linkPrecendence", c.getLinkPrecendence())
//		.setParameter("linkedId", c.getLinkedId())
//		.executeUpdate();
		
		Contact managedContact = entityManager.merge(c);
		entityManager.persist(managedContact);
		logger.info("Contact is created.");
	}
}
