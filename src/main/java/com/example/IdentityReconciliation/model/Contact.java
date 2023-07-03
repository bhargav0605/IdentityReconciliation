/**
 * Author: Bhargav Parmar
 * Email: bhargavparmar7080@gmail.com
 */
package com.example.IdentityReconciliation.model;

import java.time.LocalDateTime;

import com.example.IdentityReconciliation.enums.LinkPrecedence;

public class Contact {
	
	private int id;
	
	private String phoneNumber;
	
	private String email;
	
	private int linkedId;
	
	// Only "primary" | "secondary"
	private LinkPrecedence linkPrecendence;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	private LocalDateTime deletedAt;

	public Contact() {
		super();
	}

	public Contact(int id, String phoneNumber, String email, int linkedId, LinkPrecedence linkPrecendence,
			LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.linkedId = linkedId;
		this.linkPrecendence = linkPrecendence;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getLinkedId() {
		return linkedId;
	}

	public void setLinkedId(int linkedId) {
		this.linkedId = linkedId;
	}

	public LinkPrecedence getLinkPrecendence() {
		return linkPrecendence;
	}

	public void setLinkPrecendence(LinkPrecedence linkPrecendence) {
		this.linkPrecendence = linkPrecendence;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", phoneNumber=" + phoneNumber + ", email=" + email + ", linkedId=" + linkedId
				+ ", linkPrecendence=" + linkPrecendence + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", deletedAt=" + deletedAt + "]";
	}
}
// end of the class