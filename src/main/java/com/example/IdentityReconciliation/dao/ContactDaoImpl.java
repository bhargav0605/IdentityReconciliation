/**
 * Author: Bhargav Parmar
 * Email: bhargavparmar7080@gmail.com
 */
package com.example.IdentityReconciliation.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.IdentityReconciliation.dto.IdentityRequest;
import com.example.IdentityReconciliation.model.Contact;

@Repository
public class ContactDaoImpl implements ContactDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Contact> getContact(IdentityRequest identityRequest) {
		
		String phoneNumber = Long.toString(identityRequest.getPhoneNumber());
		String email = identityRequest.getEmail();
		

		String hql = null;
		List<Contact> contact = null;
		if(email != null && !phoneNumber.equals("0")) {
			System.out.println("Both available");
			hql = 
					"SELECT e FROM Contact e WHERE e.phoneNumber = :phoneNumber "
					+ "OR "
					+ "e.email = :email";
			contact = entityManager.createQuery(hql, Contact.class)
					.setParameter("phoneNumber", phoneNumber)
					.setParameter("email", email)
					.getResultList();
			
		} else if (email == null && !phoneNumber.equals("0")) {
			System.out.println("email is null, phone number is not empty");
			hql = "SELECT c FROM Contact c WHERE c.email IN (SELECT c2.email FROM Contact c2 WHERE c2.phoneNumber = :phoneNumber)";
			
			contact = entityManager.createQuery(hql, Contact.class)
					.setParameter("phoneNumber", phoneNumber)
					.getResultList();
			
		} else {
			System.out.println("email is not null, phone number is empty");
			hql = "SELECT c FROM Contact c WHERE c.phoneNumber IN (SELECT c2.phoneNumber FROM Contact c2 WHERE c2.email = :email)";
			
			contact = entityManager.createQuery(hql, Contact.class)
					.setParameter("email", email)
					.getResultList();
		}

		return contact;
	}
}
