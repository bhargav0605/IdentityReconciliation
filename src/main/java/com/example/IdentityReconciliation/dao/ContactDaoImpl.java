package com.example.IdentityReconciliation.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.IdentityReconciliation.model.Contact;

@Repository
public class ContactDaoImpl implements ContactDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getContact() {
		// TODO Auto-generated method stub
		String hql = "SELECT c FROM Contact c";
		return (List<Contact>) entityManager.createQuery(hql).getResultList();
	}
	
//	@SuppressWarnings("unchecked")
//	public List<Contact> getContact() {
//		String hql = "SELECT c FROM Contact c";
//		return (List<Contact>) entityManager.createQuery(hql).getResultList();
//	}
}
