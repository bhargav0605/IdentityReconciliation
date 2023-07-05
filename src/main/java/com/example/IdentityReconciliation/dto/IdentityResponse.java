/**
 * Author: Bhargav Parmar
 * Email: bhargavparmar7080@gmail.com
 */
package com.example.IdentityReconciliation.dto;

public class IdentityResponse {

	private ContactResponse contact;

	public ContactResponse getContact() {
		return contact;
	}

	public void setContact(ContactResponse contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "IdentityResponse [contact=" + contact + "]";
	}
	
	
}
//end of the class