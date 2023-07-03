/**
 * Author: Bhargav Parmar
 * Email: bhargavparmar7080@gmail.com
 */
package com.example.IdentityReconciliation.dto;

import java.util.Arrays;

public class ContactResponse {
	
	private int primaryContatctId;
	
	private String[] emails;
	
	private String[] phoneNumbers;
	
	private int[] secondaryContactIds;

	public ContactResponse(int primaryContatctId, String[] emails, String[] phoneNumbers, int[] secondaryContactIds) {
		super();
		this.primaryContatctId = primaryContatctId;
		this.emails = emails;
		this.phoneNumbers = phoneNumbers;
		this.secondaryContactIds = secondaryContactIds;
	}

	public ContactResponse() {
		super();
	}

	public int getPrimaryContatctId() {
		return primaryContatctId;
	}

	public void setPrimaryContatctId(int primaryContatctId) {
		this.primaryContatctId = primaryContatctId;
	}

	public String[] getEmails() {
		return emails;
	}

	public void setEmails(String[] emails) {
		this.emails = emails;
	}

	public String[] getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(String[] phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public int[] getSecondaryContactIds() {
		return secondaryContactIds;
	}

	public void setSecondaryContactIds(int[] secondaryContactIds) {
		this.secondaryContactIds = secondaryContactIds;
	}

	@Override
	public String toString() {
		return "ContactResponse [primaryContatctId=" + primaryContatctId + ", emails=" + Arrays.toString(emails)
				+ ", phoneNumbers=" + Arrays.toString(phoneNumbers) + ", secondaryContactIds="
				+ Arrays.toString(secondaryContactIds) + "]";
	}
}
// end of the class