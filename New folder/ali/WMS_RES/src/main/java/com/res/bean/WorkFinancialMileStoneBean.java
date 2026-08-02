package com.res.bean;

public class WorkFinancialMileStoneBean   {
	 
	private Long id;
	
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

	private Long financialStageId;
	
	private String financialStageName;

	private Long cumulative;
	
	private String completionDate;
	 
	private Long workAgreementId;
	
	private Short enabled;
	
	private Short order;
	
	private Long workAgreementRevisionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFinancialStageId() {
		return financialStageId;
	}

	public void setFinancialStageId(Long financialStageId) {
		this.financialStageId = financialStageId;
	}	

	public String getFinancialStageName() {
		return financialStageName;
	}

	public void setFinancialStageName(String financialStageName) {
		this.financialStageName = financialStageName;
	}

	public Long getCumulative() {
		return cumulative;
	}

	public void setCumulative(Long cumulative) {
		this.cumulative = cumulative;
	}

	public String getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
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
