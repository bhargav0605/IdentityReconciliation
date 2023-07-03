package com.example.IdentityReconciliation.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDaoImpl {
	
	@Autowired
	NamedParameterJdbcTemplate template;
	
	public void getContact() {
		
	}

}
