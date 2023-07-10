/**
 * Author: Bhargav Parmar
 * Email: bhargavparmar7080@gmail.com
 */
package com.example.IdentityReconciliation.dto;

import javax.validation.constraints.Email;

public class IdentityRequest {
	
	@Email(message="Not an email.")
	private String email;
	
	private long phoneNumber;

	public IdentityRequest() {
		super();
	}

	public IdentityRequest(String email, long phoneNumber) {
		super();
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "IdentityRequest [email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}
}
//end of the class