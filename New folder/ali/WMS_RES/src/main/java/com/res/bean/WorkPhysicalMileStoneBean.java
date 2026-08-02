package com.res.bean;

public class WorkPhysicalMileStoneBean {
 
	private Long id;

	private Long physicalStageId;
	
	private String physicalStageName;

	private String completionDate;
	
	private Long workAgreementId;
	
	private Short enabled;
	
	private Short order;
	
	private Long workAgreementRevisionId;
	
	private Long workId;
	
	private String workRequisitionNo;

	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	public String getWorkRequisitionNo() {
		return workRequisitionNo;
	}

	public void setWorkRequisitionNo(String workRequisitionNo) {
		this.workRequisitionNo = workRequisitionNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}

	public Long getPhysicalStageId() {
		return physicalStageId;
	}

	public void setPhysicalStageId(Long physicalStageId) {
		this.physicalStageId = physicalStageId;
	}

	public String getPhysicalStageName() {
		return physicalStageName;
	}

	public void setPhysicalStageName(String physicalStageName) {
		this.physicalStageName = physicalStageName;
	}

	public Long getWorkAgreementId() {
		return workAgreementId;
	}

	public void setWorkAgreementId(Long workAgreementId) {
		this.workAgreementId = workAgreementId;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	public Short getOrder() {
		return order;
	}

	public void setOrder(Short order) {
		this.order = order;
	}

	public Long getWorkAgreementRevisionId() {
		return workAgreementRevisionId;
	}

	public void setWorkAgreementRevisionId(Long workAgreementRevisionId) {
		this.workAgreementRevisionId = workAgreementRevisionId;
	}

	

	
	 

}
