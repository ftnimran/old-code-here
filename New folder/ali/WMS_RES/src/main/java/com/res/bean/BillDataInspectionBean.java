package com.res.bean;

import java.math.BigDecimal;
import java.util.Date;

public class BillDataInspectionBean {

	private Long id;

	private Long billId;
	private String RegistrationNo;
	
	private String RegistrationDate;
	
	private String billNo;

	private String billDate;

	private BigDecimal billAmount;
	
	private Long randomAllocationId;
	
	private String workName;
	
	private Long workId;

	private Long workTypeId;
	
	private String workTypeName;
	
	private String worksubTypeName;

	private String lineDepartmentName;

	private String inspectionName;

	private BigDecimal costOfBillForm;

	private String accountHeadName;
	
	private String contractorName;

	private String billStatus;
	
	private String workStatus;
	
	private String workSubStatus;
	
	/*private Date inspectedByDateTime;
	
	private Date inspectedByDateTimeEE;
	
private String inspectedByDateTimeString;
	
	private String inspectedByDateTimeEEString;*/
	
	private String inspectionUploadedDate;
	
	

	private String instanceURL = null;
	
	private String templateURL = null;
	
	private String uploadURL = null;
	
	private String templateName = null;

	String imageUploadURL = null;
	
	String fileUploadURL = null;
	
	String longitude ;
	
	String latitude;
	
	private String adhikariNameNPost;
	
	private String billType;
	
	String imageDownLoadURL = null;
	
	private String pacAmount;
	
	private String districtName;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public BigDecimal getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(BigDecimal billAmount) {
		this.billAmount = billAmount;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public Long getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(Long workTypeId) {
		this.workTypeId = workTypeId;
	}

	public String getLineDepartmentName() {
		return lineDepartmentName;
	}

	public void setLineDepartmentName(String lineDepartmentName) {
		this.lineDepartmentName = lineDepartmentName;
	}

	public String getInspectionName() {
		return inspectionName;
	}

	public void setInspectionName(String inspectionName) {
		this.inspectionName = inspectionName;
	}

	public BigDecimal getCostOfBillForm() {
		return costOfBillForm;
	}

	public void setCostOfBillForm(BigDecimal costOfBillForm) {
		this.costOfBillForm = costOfBillForm;
	}

	public String getAccountHeadName() {
		return accountHeadName;
	}

	public void setAccountHeadName(String accountHeadName) {
		this.accountHeadName = accountHeadName;
	}

	public String getInstanceURL() {
		return instanceURL;
	}

	public void setInstanceURL(String instanceURL) {
		this.instanceURL = instanceURL;
	}

	public String getTemplateURL() {
		return templateURL;
	}

	public void setTemplateURL(String templateURL) {
		this.templateURL = templateURL;
	}

	public String getUploadURL() {
		return uploadURL;
	}

	public void setUploadURL(String uploadURL) {
		this.uploadURL = uploadURL;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getRegistrationNo() {
		return RegistrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		RegistrationNo = registrationNo;
	}

	public String getRegistrationDate() {
		return RegistrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		RegistrationDate = registrationDate;
	}

	public String getWorkTypeName() {
		return workTypeName;
	}

	public void setWorkTypeName(String workTypeName) {
		this.workTypeName = workTypeName;
	}

	public String getWorksubTypeName() {
		return worksubTypeName;
	}

	public void setWorksubTypeName(String worksubTypeName) {
		this.worksubTypeName = worksubTypeName;
	}

	public String getContractorName() {
		return contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	public String getWorkSubStatus() {
		return workSubStatus;
	}

	public void setWorkSubStatus(String workSubStatus) {
		this.workSubStatus = workSubStatus;
	}

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getImageUploadURL() {
		return imageUploadURL;
	}

	public void setImageUploadURL(String imageUploadURL) {
		this.imageUploadURL = imageUploadURL;
	}

	public String getFileUploadURL() {
		return fileUploadURL;
	}

	public void setFileUploadURL(String fileUploadURL) {
		this.fileUploadURL = fileUploadURL;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getAdhikariNameNPost() {
		return adhikariNameNPost;
	}

	public void setAdhikariNameNPost(String adhikariNameNPost) {
		this.adhikariNameNPost = adhikariNameNPost;
	}

	public String getImageDownLoadURL() {
		return imageDownLoadURL;
	}

	public void setImageDownLoadURL(String imageDownLoadURL) {
		this.imageDownLoadURL = imageDownLoadURL;
	}

	/*public Date getInspectedByDateTime() {
		return inspectedByDateTime;
	}

	public void setInspectedByDateTime(Date inspectedByDateTime) {
		this.inspectedByDateTime = inspectedByDateTime;
	}

	public Date getInspectedByDateTimeEE() {
		return inspectedByDateTimeEE;
	}

	public void setInspectedByDateTimeEE(Date inspectedByDateTimeEE) {
		this.inspectedByDateTimeEE = inspectedByDateTimeEE;
	}

	public String getInspectedByDateTimeString() {
		return inspectedByDateTimeString;
	}

	public void setInspectedByDateTimeString(String inspectedByDateTimeString) {
		this.inspectedByDateTimeString = inspectedByDateTimeString;
	}

	public String getInspectedByDateTimeEEString() {
		return inspectedByDateTimeEEString;
	}

	public void setInspectedByDateTimeEEString(String inspectedByDateTimeEEString) {
		this.inspectedByDateTimeEEString = inspectedByDateTimeEEString;
	}*/

	public String getInspectionUploadedDate() {
		return inspectionUploadedDate;
	}

	public void setInspectionUploadedDate(String inspectionUploadedDate) {
		this.inspectionUploadedDate = inspectionUploadedDate;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	public String getPacAmount() {
		return pacAmount;
	}

	public void setPacAmount(String pacAmount) {
		this.pacAmount = pacAmount;
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public Long getRandomAllocationId() {
		return randomAllocationId;
	}

	public void setRandomAllocationId(Long randomAllocationId) {
		this.randomAllocationId = randomAllocationId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

 


}
