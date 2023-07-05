/**
 * Author: Bhargav Parmar
 * Email: bhargavparmar7080@gmail.com
 */
package com.example.IdentityReconciliation.dto;

import java.util.List;

public class ContactResponse {
	
	private int primaryContatctId;
	
	private List<String> emails;
	
	private List<String> phoneNumbers;
	
	private List<Integer> secondaryContactIds;

	public ContactResponse() {
		super();
	}

	public ContactResponse(int primaryContatctId, List<String> emails, List<String> phoneNumbers,
			List<Integer> secondaryContactIds) {
		super();
		this.primaryContatctId = primaryContatctId;
		this.emails = emails;
		this.phoneNumbers = phoneNumbers;
		this.secondaryContactIds = secondaryContactIds;
	}

	public int getPrimaryContatctId() {
		return primaryContatctId;
	}

	public void setPrimaryContatctId(int primaryContatctId) {
		this.primaryContatctId = primaryContatctId;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public List<Integer> getSecondaryContactIds() {
		return secondaryContactIds;
	}

	public void setSecondaryContactIds(List<Integer> secondaryContactIds) {
		this.secondaryContactIds = secondaryContactIds;
	}

	@Override
	public String toString() {
		return "ContactResponse [primaryContatctId=" + primaryContatctId + ", emails=" + emails + ", phoneNumbers="
				+ phoneNumbers + ", secondaryContactIds=" + secondaryContactIds + "]";
	}

	
}
// end of the class