package com.res.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.res.entity.Bank;

public class ContractorDepositBean {

	private Long tenderId;

	private BigDecimal amount;

	private Long bankId;

	private String instrumentNumber;

	private String expiredOn;

	private Long depositCategoryId;

	private Long depositTypeId;

	private String depositCategoryName;

	private String depositTypeName;
	
	private String bankName;
	
	private String instrumentDate;

	public String getInstrumentDate() {
		return instrumentDate;
	}

	public void setInstrumentDate(String instrumentDate) {
		this.instrumentDate = instrumentDate;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getDepositTypeName() {
		return depositTypeName;
	}

	public void setDepositTypeName(String depositTypeName) {
		this.depositTypeName = depositTypeName;
	}

	public String getDepositCategoryName() {
		return depositCategoryName;
	}

	public void setDepositCategoryName(String depositCategoryName) {
		this.depositCategoryName = depositCategoryName;
	}

	public Long getTenderId() {
		return tenderId;
	}

	public void setTenderId(Long tenderId) {
		this.tenderId = tenderId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	

	
	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public String getInstrumentNumber() {
		return instrumentNumber;
	}

	public void setInstrumentNumber(String instrumentNumber) {
		this.instrumentNumber = instrumentNumber;
	}

	public String getExpiredOn() {
		return expiredOn;
	}

	public void setExpiredOn(String expiredOn) {
		this.expiredOn = expiredOn;
	}

	public Long getDepositCategoryId() {
		return depositCategoryId;
	}

	public void setDepositCategoryId(Long depositCategoryId) {
		this.depositCategoryId = depositCategoryId;
	}

	public Long getDepositTypeId() {
		return depositTypeId;
	}

	public void setDepositTypeId(Long depositTypeId) {
		this.depositTypeId = depositTypeId;
	}

	

}
