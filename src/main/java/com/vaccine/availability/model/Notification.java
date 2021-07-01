package com.vaccine.availability.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notification")
public class Notification {

	@Transient
	public static final String SEQUENCE_NAME = "notification_sequence";

	@Id
	private String id;
	@Indexed(unique = true, direction = IndexDirection.DESCENDING)
//	@Indexed(unique = true, direction = IndexDirection.DESCENDING)

	private String email;
	private int pincode;
	private int ageGroup;

	boolean emailSent = false;

	int emailCount = 0;

	String emailSendDate = "";

	@DBRef
	private User user;

	public Notification() {

	}

	public Notification(int pincode, int ageGroup) {

		this.pincode = pincode;
		this.ageGroup = ageGroup;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public int getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(int ageGroup) {
		this.ageGroup = ageGroup;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	public boolean isEmailSent() {
		return emailSent;
	}

	public void setEmailSent(boolean emailSent) {
		this.emailSent = emailSent;
	}

	public int getEmailCount() {
		return emailCount;
	}

	public void setEmailCount(int emailCount) {
		this.emailCount = emailCount;
	}

	public String getEmailSendDate() {
		return emailSendDate;
	}

	public void setEmailSendDate(String emailSendDate) {
		this.emailSendDate = emailSendDate;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", email=" + email + ", pincode=" + pincode + ", ageGroup=" + ageGroup + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ageGroup;
		result = prime * result + ((email == null) ? 0 : email.hashCode());

		result = prime * result + pincode;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notification other = (Notification) obj;
		if (ageGroup != other.ageGroup)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (pincode != other.pincode)
			return false;
		return true;
	}

}
