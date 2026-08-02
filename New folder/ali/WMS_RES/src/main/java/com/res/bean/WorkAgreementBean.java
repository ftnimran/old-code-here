package com.res.bean;

import java.math.BigDecimal;
import java.util.List;

 
public class WorkAgreementBean  {
	
	private Long workAgreementId;
	 
	private Long agreementCopy;

	private String agreementDate;

	private String agreementNumber;
	
	private String tentativeCompletionDate;

	private String writtenOrderDate;
	
	private Long workId;
	
	private String workName;
	
	private String executionAgency;
	
	private String workRequisitionNo;
	
	private Long workStatusId;
	
	private String workStatus;
	
	private Long workAgreementStatusId;
	
	private String workAgreementStatus;
	
	private int index;
	
	private List<WorkFinancialMileStoneBean> workFinancialMileStoneBeanList;
	
	private List<WorkPhysicalMileStoneBean> workPhysicalMileStoneBeanList;
	
	private String revisedOn;
	
	//Work Tender related details
	private Long tenderId;
	private BigDecimal tenderCost;
	private Long contractorId;
	private String contractorName;
	//Parent Id

	private Long parentId;
	
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getAgreementCopy() {
		return agreementCopy;
	}

	public void setAgreementCopy(Long agreementCopy) {
		this.agreementCopy = agreementCopy;
	}

	public String getAgreementDate() {
		return agreementDate;
	}

	public void setAgreementDate(String agreementDate) {
		this.agreementDate = agreementDate;
	}

	public String getAgreementNumber() {
		return agreementNumber;
	}

	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
	}

	public String getTentativeCompletionDate() {
		return tentativeCompletionDate;
	}

	public void setTentativeCompletionDate(String tentativeCompletionDate) {
		this.tentativeCompletionDate = tentativeCompletionDate;
	}

	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	public Long getWorkStatusId() {
		return workStatusId;
	}

	public void setWorkStatusId(Long workStatusId) {
		this.workStatusId = workStatusId;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public Long getWorkAgreementStatusId() {
		return workAgreementStatusId;
	}

	public void setWorkAgreementStatusId(Long workAgreementStatusId) {
		this.workAgreementStatusId = workAgreementStatusId;
	}

	public String getWorkAgreementStatus() {
		return workAgreementStatus;
	}

	public void setWorkAgreementStatus(String workAgreementStatus) {
		this.workAgreementStatus = workAgreementStatus;
	}

	public Long getWorkAgreementId() {
		return workAgreementId;
	}

	public void setWorkAgreementId(Long workAgreementId) {
		this.workAgreementId = workAgreementId;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getWorkRequisitionNo() {
		return workRequisitionNo;
	}

	public void setWorkRequisitionNo(String workRequisitionNo) {
		this.workRequisitionNo = workRequisitionNo;
	}

	public String getExecutionAgency() {
		return executionAgency;
	}

	public void setExecutionAgency(String executionAgency) {
		this.executionAgency = executionAgency;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<WorkFinancialMileStoneBean> getWorkFinancialMileStoneBeanList() {
		return workFinancialMileStoneBeanList;
	}

	public void setWorkFinancialMileStoneBeanList(
			List<WorkFinancialMileStoneBean> workFinancialMileStoneBeanList) {
		this.workFinancialMileStoneBeanList = workFinancialMileStoneBeanList;
	}

	public List<WorkPhysicalMileStoneBean> getWorkPhysicalMileStoneBeanList() {
		return workPhysicalMileStoneBeanList;
	}

	public void setWorkPhysicalMileStoneBeanList(
			List<WorkPhysicalMileStoneBean> workPhysicalMileStoneBeanList) {
		this.workPhysicalMileStoneBeanList = workPhysicalMileStoneBeanList;
	}

	public String getWrittenOrderDate() {
		return writtenOrderDate;
	}

	public void setWrittenOrderDate(String writtenOrderDate) {
		this.writtenOrderDate = writtenOrderDate;
	}

	public BigDecimal getTenderCost() {
		return tenderCost;
	}

	public void setTenderCost(BigDecimal tenderCost) {
		this.tenderCost = tenderCost;
	}

	public Long getContractorId() {
		return contractorId;
	}

	public void setContractorId(Long contractorId) {
		this.contractorId = contractorId;
	}

	public String getContractorName() {
		return contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	public Long getTenderId() {
		return tenderId;
	}

	public void setTenderId(Long tenderId) {
		this.tenderId = tenderId;
	}

	public String getRevisedOn() {
		return revisedOn;
	}

	public void setRevisedOn(String revisedOn) {
		this.revisedOn = revisedOn;
	}

	
	

	
}
