package com.res.bean;

import java.math.BigDecimal;
import java.util.List;

public class BudgetRequestBean {

    private Long id;
    
    private Integer index;

    private String requestNumber;
    
    private Long noOfWork;
    
    private String requestDate;
    
    private Long officeBeanId;
    
    private String officeName;
    
    private BigDecimal requestedAmount;

    private BigDecimal requestedContingency;
    
    private BigDecimal approvedAmount;
    
    private BigDecimal surrendedAmount;
    
    private BigDecimal approvedContingency;
    
   	private Long statusId;
   	
   	private String status;
   	private String accountHeadNameE;
   	
    private String remark;
   	
   	public String getAccountHeadNameE() {
		return accountHeadNameE;
	}

	public void setAccountHeadNameE(String accountHeadNameE) {
		this.accountHeadNameE = accountHeadNameE;
	}

	private List<BudgetRequestDetailBean> budgetRequestDetailBeanList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public Long getNoOfWork() {
		return noOfWork;
	}

	public void setNoOfWork(Long noOfWork) {
		this.noOfWork = noOfWork;
	}

	public BigDecimal getRequestedAmount() {
		return requestedAmount;
	}

	public void setRequestedAmount(BigDecimal requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

	public BigDecimal getApprovedAmount() {
		return approvedAmount;
	}

	public void setApprovedAmount(BigDecimal approvedAmount) {
		this.approvedAmount = approvedAmount;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<BudgetRequestDetailBean> getBudgetRequestDetailBeanList() {
		return budgetRequestDetailBeanList;
	}

	public void setBudgetRequestDetailBeanList(
			List<BudgetRequestDetailBean> budgetRequestDetailBeanList) {
		this.budgetRequestDetailBeanList = budgetRequestDetailBeanList;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public BigDecimal getRequestedContingency() {
		return requestedContingency;
	}

	public void setRequestedContingency(BigDecimal requestedContingency) {
		this.requestedContingency = requestedContingency;
	}

	public BigDecimal getApprovedContingency() {
		return approvedContingency;
	}

	public void setApprovedContingency(BigDecimal approvedContingency) {
		this.approvedContingency = approvedContingency;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public Long getOfficeBeanId() {
		return officeBeanId;
	}

	public void setOfficeBeanId(Long officeBeanId) {
		this.officeBeanId = officeBeanId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getSurrendedAmount() {
		return surrendedAmount;
	}

	public void setSurrendedAmount(BigDecimal surrendedAmount) {
		this.surrendedAmount = surrendedAmount;
	}

}
