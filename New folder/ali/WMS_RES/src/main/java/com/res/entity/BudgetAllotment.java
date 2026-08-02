package com.res.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="budget_allotment")
public class BudgetAllotment extends Auditable implements Serializable {


	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @JoinColumn(name = "ACCOUNT_HEAD", referencedColumnName = "ID")
	@OneToOne

	private AccountHead accountHead;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Received_On")
    private Date receivedOn;
    
    @Column(name = "Amount")
    private BigDecimal amount;
    
    //statusId with cloumn change name is status_id
    
    @Column(name = "status_id")
    private Long statusId;
    

    public BudgetAllotment(Long id) {
		super();
		this.id = id;
	}
    
    public BudgetAllotment() {
	
	}
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getReceivedOn() {
		return receivedOn;
	}

	public void setReceivedOn(Date receivedOn) {
		this.receivedOn = receivedOn;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public AccountHead getAccountHead() {
		return accountHead;
	}

	public void setAccountHead(AccountHead accountHead) {
		this.accountHead = accountHead;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

}