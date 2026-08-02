package com.res.bean;

import java.util.List;

import com.res.entity.Office;

public class UserBean {

	private Long id;
	
	private Long userId;

	private Integer index;

	private String name;
	
	private String measureByName;

	private Long designationId;

	private DesignationBean designationBean;
	
	private Long office;
	
	private String username;

	private String emailId;

	private String mobileNo;

	private String password;

	private String confirmPassword;

	private Short isOIC;

	private String isOICString;

	private Long officeTypeId;
	
	private Long officerOfficeId; 
	
	private Long subDivionOfficeId;

	private OfficeTypeBean officeTypeBean;

	private Long officeId;

	private OfficeBean officeBean;

	private String captchaText;

	private String status;

	private String oldStatus;
	
	private String role;

	private List<String> roles;

	private DistrictBean districtBean;

	private String loggedInUserRole;
//Rakesh
	private String worksIds;
//Rakesh'
	
	private String modifiedBy;
	
	private String nameAndUserName;
	
	private String nameAndDesig;
	
	public String getWorksIds() {
		return worksIds;
	}

	public void setWorksIds(String worksIds) {
		this.worksIds = worksIds;
	}

	public String getLoggedInUserRole() {
		return loggedInUserRole;
	}

	public void setLoggedInUserRole(String loggedInUserRole) {
		this.loggedInUserRole = loggedInUserRole;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
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

	public Long getOfficeTypeId() {
		return officeTypeId;
	}

	public void setOfficeTypeId(Long officeTypeId) {
		this.officeTypeId = officeTypeId;
	}

	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}

	public String getCaptchaText() {
		return captchaText;
	}

	public void setCaptchaText(String captchaText) {
		this.captchaText = captchaText;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public DesignationBean getDesignationBean() {
		return designationBean;
	}

	public void setDesignationBean(DesignationBean designationBean) {
		this.designationBean = designationBean;
	}

	public OfficeTypeBean getOfficeTypeBean() {
		return officeTypeBean;
	}

	public void setOfficeTypeBean(OfficeTypeBean officeTypeBean) {
		this.officeTypeBean = officeTypeBean;
	}

	public OfficeBean getOfficeBean() {
		return officeBean;
	}

	public void setOfficeBean(OfficeBean officeBean) {
		this.officeBean = officeBean;
	}

	public String getIsOICString() {
		return isOICString;
	}

	public void setIsOICString(String isOICString) {
		this.isOICString = isOICString;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}

	public DistrictBean getDistrictBean() {
		return districtBean;
	}

	public void setDistrictBean(DistrictBean districtBean) {
		this.districtBean = districtBean;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNameAndUserName() {
		return nameAndUserName;
	}

	public void setNameAndUserName(String nameAndUserName) {
		this.nameAndUserName = nameAndUserName;
	}

	public String getMeasureByName() {
		return measureByName;
	}

	public void setMeasureByName(String measureByName) {
		this.measureByName = measureByName;
	}

	public Long getOfficerOfficeId() {
		return officerOfficeId;
	}

	public void setOfficerOfficeId(Long officerOfficeId) {
		this.officerOfficeId = officerOfficeId;
	}

	public Long getOffice() {
		return office;
	}

	public void setOffice(Long office) {
		this.office = office;
	}

	public String getNameAndDesig() {
		return nameAndDesig;
	}

	public void setNameAndDesig(String nameAndDesig) {
		this.nameAndDesig = nameAndDesig;
	}

	public Long getSubDivionOfficeId() {
		return subDivionOfficeId;
	}

	public void setSubDivionOfficeId(Long subDivionOfficeId) {
		this.subDivionOfficeId = subDivionOfficeId;
	}



}