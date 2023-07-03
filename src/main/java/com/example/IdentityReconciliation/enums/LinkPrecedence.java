/**
 * Author: Bhargav Parmar
 * Email: bhargavparmar7080@gmail.com
 */
package com.example.IdentityReconciliation.enums;

public enum LinkPrecedence {
	 PRIMARY("primary"),
     SECONDARY("secondary");
	
	private final String value;

	private LinkPrecedence(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
//end of the class