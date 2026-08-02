package com.res.bean;

import java.math.BigDecimal;

public class BudgetRequestDetailBean {

    private Long id;

    private BigDecimal approvedAmount = new BigDecimal(0);
    
    private BigDecimal requestedAmount;
    
    private BigDecimal surrendedAmount;
    
    
 
    private BigDecimal remainingAmountTotal;
    
    private Long budgetRequestId;
    
    private int index;
    
    private WorkBean workBean;
    
	private Long workId;
	
	 private String accountHeadNameE;
	 
	 private Long accountHeadId;

	public String getAccountHeadNameE() {
		return accountHeadNameE;
	}

	public void setAccountHeadNameE(String accountHeadNameE) {
		this.accountHeadNameE = accountHeadNameE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getApprovedAmount() {
		return approvedAmount;
	}

	public void setApprovedAmount(BigDecimal approvedAmount) {
		this.approvedAmount = approvedAmount;
	}

	public BigDecimal getRequestedAmount() {
		return requestedAmount;
	}

	public void setRequestedAmount(BigDecimal requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

	public Long getBudgetRequestId() {
		return budgetRequestId;
	}

	public void setBudgetRequestId(Long budgetRequestId) {
		this.budgetRequestId = budgetRequestId;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public WorkBean getWorkBean() {
		return workBean;
	}

	public void setWorkBean(WorkBean workBean) {
		this.workBean = workBean;
	}

	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	public BigDecimal getRemainingAmountTotal() {
		return remainingAmountTotal;
	}

	public void setRemainingAmountTotal(BigDecimal remainingAmountTotal) {
		this.remainingAmountTotal = remainingAmountTotal;
	}

	public Long getAccountHeadId() {
		return accountHeadId;
	}

	public void setAccountHeadId(Long accountHeadId) {
		this.accountHeadId = accountHeadId;
	}

	public BigDecimal getSurrendedAmount() {
		return surrendedAmount;
	}

	public void setSurrendedAmount(BigDecimal surrendedAmount) {
		this.surrendedAmount = surrendedAmount;
	}

}
