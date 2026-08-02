package com.res.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class BudgetAllotmentBean  {

	private Long id;

	
	private int index ;
	
	private Long statusId;
	
	private Long accountHeadId;
	
    private String accountHead;

    private String receivedOn;
    
    private BigDecimal amount;
    
    private String myString;

    private List<BudgetAllotmentEEOfficeBean> budgetAllotmentEEOfficeList = new ArrayList<>();
    private List<BudgetAllotmentEEOfficeBean> budgetAllotmentEEOfficeList2 = new ArrayList<>();

    public BudgetAllotmentBean(Long id) {
		super();
		this.id = id;
	}
    
    public BudgetAllotmentBean() {
	
	}
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountHead() {
		return accountHead;
	}

	public void setAccountHead(String accountHead) {
		this.accountHead = accountHead;
	}

	public String getReceivedOn() {
		return receivedOn;
	}

	public void setReceivedOn(String receivedOn) {
		this.receivedOn = receivedOn;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getAccountHeadId() {
		return accountHeadId;
	}

	public void setAccountHeadId(Long accountHeadId) {
		this.accountHeadId = accountHeadId;
	}

	public List<BudgetAllotmentEEOfficeBean> getBudgetAllotmentEEOfficeList() {
		return budgetAllotmentEEOfficeList;
	}

	public void setBudgetAllotmentEEOfficeList(
			List<BudgetAllotmentEEOfficeBean> budgetAllotmentEEOfficeList) {
		this.budgetAllotmentEEOfficeList = budgetAllotmentEEOfficeList;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<BudgetAllotmentEEOfficeBean> getBudgetAllotmentEEOfficeList2() {
		return budgetAllotmentEEOfficeList2;
	}

	public void setBudgetAllotmentEEOfficeList2(List<BudgetAllotmentEEOfficeBean> budgetAllotmentEEOfficeList2) {
		this.budgetAllotmentEEOfficeList2 = budgetAllotmentEEOfficeList2;
	}

	public String getMyString() {
		return myString;
	}

	public void setMyString(String myString) {
		this.myString = myString;
	}

}