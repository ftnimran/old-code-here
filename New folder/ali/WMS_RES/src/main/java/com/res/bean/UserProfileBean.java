package com.res.bean;

public class UserProfileBean {
		private Long userId;

		private String name;
	
		private String designationName;
		
		private String username;

		private String emailId;

		private String mobileNo;

		private Short isOIC;

		private String officeName;

		private String loggedInUserRole;

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDesignationName() {
			return designationName;
		}

		public void setDesignationName(String designationName) {
			this.designationName = designationName;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		public String getMobileNo() {
			return mobileNo;
		}

		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
		}

		public Short getIsOIC() {
			return isOIC;
		}

		public void setIsOIC(Short isOIC) {
			this.isOIC = isOIC;
		}

		public String getOfficeName() {
			return officeName;
		}

		public void setOfficeName(String officeName) {
			this.officeName = officeName;
		}


		public String getLoggedInUserRole() {
			return loggedInUserRole;
		}

		public void setLoggedInUserRole(String loggedInUserRole) {
			this.loggedInUserRole = loggedInUserRole;
		}
		
		
}
