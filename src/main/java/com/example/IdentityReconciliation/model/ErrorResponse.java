/**
 * Author: Bhargav Parmar
 * Email: bhargavparmar7080@gmail.com
 */
package com.example.IdentityReconciliation.model;

import java.time.Instant;

public class ErrorResponse {
	
	private int statusCode;
	private String message;
	private Instant time;
	
	
	public ErrorResponse() {
		super();
	}


	public ErrorResponse(int statusCode, String message, Instant time) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.time = time;
	}


	public int getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(int i) {
		this.statusCode = i;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Instant getTime() {
		return time;
	}


	public void setTime(Instant instant) {
		this.time = instant;
	}


	@Override
	public String toString() {
		return "ErrorResponse [statusCode=" + statusCode + ", message=" + message + ", time=" + time + "]";
	}
	
}
