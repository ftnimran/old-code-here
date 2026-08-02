package com.res.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BudgetAllotmentEEOfficeBean{

    private Long id;

    private String amount;
    
    private String receivedAmount;

    private int totalAmountInHead;
    
    private Long officeBeanId;
 
    private String officeName;
    
    private Long budgetAllotmentId;
    
    private int index;
    
    private Long accountHeadId;
    
    private String accountHead;
    
    private Date receivedOn;
    
    private String eeOfficeAmountReceivedOn;
    
    private Map<Long,String> Idlist;
    
    private List<String> amountAndDate;
    
    private String amountAndDateString;
    
    private String amountAndCreatedDateString;
    
    private String fomatedStringForOffices; 
    
    private List<BudgetAllotmentEEOfficeBean> budgetAllotmentEEOfficeList = new ArrayList<>();
    
    public String getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(String receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public String getEeOfficeAmountReceivedOn() {
		return eeOfficeAmountReceivedOn;
	}

	public void setEeOfficeAmountReceivedOn(String eeOfficeAmountReceivedOn) {
		this.eeOfficeAmountReceivedOn = eeOfficeAmountReceivedOn;
	}

	public Date getReceivedOn() {
		return receivedOn;
	}

	public void setReceivedOn(Date receivedOn) {
		this.receivedOn = receivedOn;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public BudgetAllotmentEEOfficeBean(Long id) {
		super();
		this.id = id;
	}
    
    public BudgetAllotmentEEOfficeBean() {
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Long getBudgetAllotmentId() {
		return budgetAllotmentId;
	}

	public void setBudgetAllotmentId(Long budgetAllotmentId) {
		this.budgetAllotmentId = budgetAllotmentId;
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

	public Long getAccountHeadId() {
		return accountHeadId;
	}

	public void setAccountHeadId(Long accountHeadId) {
		this.accountHeadId = accountHeadId;
	}

	public String getAccountHead() {
		return accountHead;
	}

	public void setAccountHead(String accountHead) {
		this.accountHead = accountHead;
	}

	public int getTotalAmountInHead() {
		return totalAmountInHead;
	}

	public void setTotalAmountInHead(int totalAmountInHead) {
		this.totalAmountInHead = totalAmountInHead;
	}

	public List<BudgetAllotmentEEOfficeBean> getBudgetAllotmentEEOfficeList() {
		return budgetAllotmentEEOfficeList;
	}

	public void setBudgetAllotmentEEOfficeList(List<BudgetAllotmentEEOfficeBean> budgetAllotmentEEOfficeList) {
		this.budgetAllotmentEEOfficeList = budgetAllotmentEEOfficeList;
	}

	public String getAmountAndDateString() {
		return amountAndDateString;
	}

	public void setAmountAndDateString(String amountAndDateString) {
		this.amountAndDateString = amountAndDateString;
	}

	public List<String> getAmountAndDate() {
		return amountAndDate;
	}

	public void setAmountAndDate(List<String> amountAndDate) {
		this.amountAndDate = amountAndDate;
	}

	public Map<Long, String> getIdlist() {
		return Idlist;
	}

	public void setIdlist(Map<Long, String> idlist) {
		Idlist = idlist;
	}

	public String getAmountAndCreatedDateString() {
		return amountAndCreatedDateString;
	}

	public void setAmountAndCreatedDateString(String amountAndCreatedDateString) {
		this.amountAndCreatedDateString = amountAndCreatedDateString;
	}

	public String getFomatedStringForOffices() {
		return fomatedStringForOffices;
	}

	public void setFomatedStringForOffices(String fomatedStringForOffices) {
		this.fomatedStringForOffices = fomatedStringForOffices;
	}


    

}