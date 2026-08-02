package com.res.bean;

public class AuthResponseBean {
	private String userId;
	private Long statusCode;
	private String statusDesc;
	private String tokenId;
	private String roles;
	private String loggedInUserRole;
	private Integer officeId;
	private Integer userIdInt;
	private Short isOIC;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Long statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getLoggedInUserRole() {
		return loggedInUserRole;
	}

	public void setLoggedInUserRole(String loggedInUserRole) {
		this.loggedInUserRole = loggedInUserRole;
	}

	public Integer getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}

	public Integer getUserIdInt() {
		return userIdInt;
	}

	public void setUserIdInt(Integer userIdInt) {
		this.userIdInt = userIdInt;
	}

	public Short getIsOIC() {
		return isOIC;
	}

	public void setIsOIC(Short isOIC) {
		this.isOIC = isOIC;
	}

	
}
