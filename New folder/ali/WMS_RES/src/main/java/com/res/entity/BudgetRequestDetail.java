package com.res.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="budget_request_detail")
public class BudgetRequestDetail extends Auditable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Approved_Amount")
    private BigDecimal approvedAmount;

    @Column(name = "Requested_Amount")
    private BigDecimal requestedAmount;
    
    @Column(name = "surrender_amount")
    private BigDecimal surrenderAmount;
    
    
    @JoinColumn(name = "BUDGET_REQUEST_ID", referencedColumnName = "id")
    @ManyToOne
    private BudgetRequest budgetRequest;
    
    @JoinColumn(name = "WORK_ID", referencedColumnName = "id")
    @ManyToOne
    private Work work;

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

	public BudgetRequest getBudgetRequest() {
		return budgetRequest;
	}

	public void setBudgetRequest(BudgetRequest budgetRequest) {
		this.budgetRequest = budgetRequest;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public BigDecimal getSurrenderAmount() {
		return surrenderAmount;
	}

	public void setSurrenderAmount(BigDecimal surrenderAmount) {
		this.surrenderAmount = surrenderAmount;
	}

    
	
}
