package com.res.bean;

import java.util.List;

public class WorkAgreementRevisionBean  {
	
	private Long workAgreementRevisionId;
	 
	private String revisedOn;

	private Long agreementWorkId;

	private List<WorkFinancialMileStoneBean> workFinancialMileStoneBeanList;
	
	private List<WorkPhysicalMileStoneBean> workPhysicalMileStoneBeanList;
	
	public Long getWorkAgreementRevisionId() {
		return workAgreementRevisionId;
	}

	public void setWorkAgreementRevisionId(Long workAgreementRevisionId) {
		this.workAgreementRevisionId = workAgreementRevisionId;
	}

	public String getRevisedOn() {
		return revisedOn;
	}

	public void setRevisedOn(String revisedOn) {
		this.revisedOn = revisedOn;
	}

	public Long getAgreementWorkId() {
		return agreementWorkId;
	}

	public void setAgreementWorkId(Long agreementWorkId) {
		this.agreementWorkId = agreementWorkId;
	}

	public List<WorkFinancialMileStoneBean> getWorkFinancialMileStoneBeanList() {
		return workFinancialMileStoneBeanList;
	}

	public void setWorkFinancialMileStoneBeanList(List<WorkFinancialMileStoneBean> workFinancialMileStoneBeanList) {
		this.workFinancialMileStoneBeanList = workFinancialMileStoneBeanList;
	}

	public List<WorkPhysicalMileStoneBean> getWorkPhysicalMileStoneBeanList() {
		return workPhysicalMileStoneBeanList;
	}

	public void setWorkPhysicalMileStoneBeanList(List<WorkPhysicalMileStoneBean> workPhysicalMileStoneBeanList) {
		this.workPhysicalMileStoneBeanList = workPhysicalMileStoneBeanList;
	}

	
	
}
