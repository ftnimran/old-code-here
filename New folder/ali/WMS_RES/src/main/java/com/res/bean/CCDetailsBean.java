package com.res.bean;


public class CCDetailsBean  {
	
	private Long Id;
	
	private Long workId;
	 
	private String workCompletedOn;
	
	private String usedMBNo;
	
	private String takenOverOn;
	
	private String pageNo;
	
	private String mbNo;
	
	private String date;	

	private String physicalCCIssuedOn;

	private String financialCCIssuedOn;

	private String ccInspectionSubmittedOn;
	
	private Boolean approved;
	
	private String ccInitiatedOn;
	
	private String status;
	
	private String remark;
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	public String getWorkCompletedOn() {
		return workCompletedOn;
	}

	public void setWorkCompletedOn(String workCompletedOn) {
		this.workCompletedOn = workCompletedOn;
	}

	public String getPhysicalCCIssuedOn() {
		return physicalCCIssuedOn;
	}

	public void setPhysicalCCIssuedOn(String physicalCCIssuedOn) {
		this.physicalCCIssuedOn = physicalCCIssuedOn;
	}

	public String getFinancialCCIssuedOn() {
		return financialCCIssuedOn;
	}

	public void setFinancialCCIssuedOn(String financialCCIssuedOn) {
		this.financialCCIssuedOn = financialCCIssuedOn;
	}

	public String getUsedMBNo() {
		return usedMBNo;
	}

	public void setUsedMBNo(String usedMBNo) {
		this.usedMBNo = usedMBNo;
	}

	public String getTakenOverOn() {
		return takenOverOn;
	}

	public void setTakenOverOn(String takenOverOn) {
		this.takenOverOn = takenOverOn;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getMbNo() {
		return mbNo;
	}

	public void setMbNo(String mbNo) {
		this.mbNo = mbNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCcInspectionSubmittedOn() {
		return ccInspectionSubmittedOn;
	}

	public void setCcInspectionSubmittedOn(String ccInspectionSubmittedOn) {
		this.ccInspectionSubmittedOn = ccInspectionSubmittedOn;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public String getCcInitiatedOn() {
		return ccInitiatedOn;
	}

	public void setCcInitiatedOn(String ccInitiatedOn) {
		this.ccInitiatedOn = ccInitiatedOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
