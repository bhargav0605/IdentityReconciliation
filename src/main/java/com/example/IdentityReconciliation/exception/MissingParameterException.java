/**
 * Author: Bhargav Parmar
 * Email: bhargavparmar7080@gmail.com
 */
package com.example.IdentityReconciliation.exception;

public class MissingParameterException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public MissingParameterException(String message) {
        super(message);
    }

}
