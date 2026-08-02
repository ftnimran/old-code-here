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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "contractor_deposits")
public class ContractorDeposit implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@JoinColumn(name = "tender_id", referencedColumnName = "ID")
	@ManyToOne
	private WorkTender workTender;
	
	@JoinColumn(name = "deposite_cat_id", referencedColumnName = "ID")
	@ManyToOne
	private DepositeCategory depositeCategoryId;

	@JoinColumn(name = "deposite_type_id", referencedColumnName = "ID")
	@ManyToOne
	private DepositeType depositeTypeId;
	
	@JoinColumn(name = "bank_id", referencedColumnName = "bank_id")
	@ManyToOne
	private Bank bankId;
	
	

	@Column(name = "amount")
	private BigDecimal amount;
	
	
	
	@Column(name = "instrument_no")
	private String instrumentNumber;

	

	@Column(name = "expired_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiredOn;
	
	@Column(name = "instrument_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date instrumentDate;

	
	
	
	public Date getInstrumentDate() {
		return instrumentDate;
	}

	public void setInstrumentDate(Date instrumentDate) {
		this.instrumentDate = instrumentDate;
	}

	public ContractorDeposit(Long id) {
		this.id = id;
	}

	public ContractorDeposit() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WorkTender getWorkTender() {
		return workTender;
	}

	public void setWorkTender(WorkTender workTender) {
		this.workTender = workTender;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	

	public Bank getBankId() {
		return bankId;
	}

	public void setBankId(Bank bankId) {
		this.bankId = bankId;
	}

	public String getInstrumentNumber() {
		return instrumentNumber;
	}

	public void setInstrumentNumber(String instrumentNumber) {
		this.instrumentNumber = instrumentNumber;
	}

	public Date getExpiredOn() {
		return expiredOn;
	}

	public void setExpiredOn(Date expiredOn) {
		this.expiredOn = expiredOn;
	}

	
	public DepositeCategory getDepositeCategoryId() {
		return depositeCategoryId;
	}

	public void setDepositeCategoryId(DepositeCategory depositeCategoryId) {
		this.depositeCategoryId = depositeCategoryId;
	}

	public DepositeType getDepositeTypeId() {
		return depositeTypeId;
	}

	public void setDepositeTypeId(DepositeType depositeTypeId) {
		this.depositeTypeId = depositeTypeId;
	}

	
	

}
