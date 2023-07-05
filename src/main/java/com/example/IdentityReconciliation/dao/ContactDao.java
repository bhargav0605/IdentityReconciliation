/**
 * Author: Bhargav Parmar
 * Email: bhargavparmar7080@gmail.com
 */
package com.example.IdentityReconciliation.dao;

import java.util.List;

import com.example.IdentityReconciliation.dto.IdentityRequest;
import com.example.IdentityReconciliation.dto.IdentityResponse;
import com.example.IdentityReconciliation.model.Contact;

public interface ContactDao {
	
	List<Contact> getContact(IdentityRequest identityRequest);

}
