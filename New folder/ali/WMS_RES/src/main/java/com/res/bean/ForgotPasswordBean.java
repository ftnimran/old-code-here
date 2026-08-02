package com.res.bean;

import org.hibernate.validator.constraints.NotEmpty;

public class  ForgotPasswordBean {
	
	@NotEmpty
	//@Email
	private String emailId;
	/*@NotEmpty
	private String mobileNumber;*/
	/*@NotEmpty
	private String aadhaarNumber;*/
	/*@NotEmpty
	private String username;*/

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/*public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}*/

	/*public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}*/

	/*public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}*/

	
}



