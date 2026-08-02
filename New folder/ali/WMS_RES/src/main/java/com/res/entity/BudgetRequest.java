package com.res.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="budget_request")
public class BudgetRequest extends Auditable implements Serializable {



	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Request_number")
    private String requestNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Request_Date")
    private Date RequestDate;
    
    @Column(name = "No_Of_Work")
    private Long noOfWork;
    
    @Column(name = "Requested_Amount")
    private BigDecimal requestedAmount;
    
    @Column(name = "Approved_Amount")
    private BigDecimal approvedAmount;
    
   /* @Column(name = "Requested_Contingency")
    private BigDecimal requestedContingency;*/
    
   /* @Column(name = "Approved_Contingency")
    private BigDecimal approvedContingency;*/
    
    @JoinColumn(name = "mst_offices_id", referencedColumnName = "id")
    @ManyToOne
    private Office office;
    
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
   	@ManyToOne
   	private MasterBudgetStatus status;
    
    @Column(name = "remark")
    private String remark;
    
    @OneToMany(mappedBy = "budgetRequest")
	private Collection<BudgetRequestDetail> budgetRequestDetails;
    
    public Collection<BudgetRequestDetail> getBudgetRequestDetails() {
		return budgetRequestDetails;
	}

	public void setBudgetRequestDetails(Collection<BudgetRequestDetail> budgetRequestDetails) {
		this.budgetRequestDetails = budgetRequestDetails;
	}

	public BudgetRequest(Long id) {
		super();
		this.id = id;
	}
    
    public BudgetRequest() {
	
	}
    
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

	public MasterBudgetStatus getStatus() {
		return status;
	}

	public void setStatus(MasterBudgetStatus status) {
		this.status = status;
	}

	public Date getRequestDate() {
		return RequestDate;
	}

	public void setRequestDate(Date requestDate) {
		RequestDate = requestDate;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}