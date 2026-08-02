package com.res.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContengencyBean  {

	private Long id;

	private Long billId;

	private Integer totalConengencyAmount;
	
	private Integer commulativeExpenditure;
	
	private Integer remainingContengency;
	
    private String expenditureDate;
    
    private Integer expenditureAmount;
    
	private Date createdDate;

	private String createdBy;

	private String remarks;
	
	List<ContengencyBean> oldContengencyBeanList = new ArrayList<ContengencyBean>();
	
	public ContengencyBean() {
	}

	public ContengencyBean(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public String getExpenditureDate() {
		return expenditureDate;
	}

	public void setExpenditureDate(String expenditureDate) {
		this.expenditureDate = expenditureDate;
	}


	public List<ContengencyBean> getOldContengencyBeanList() {
		return oldContengencyBeanList;
	}

	public void setOldContengencyBeanList(
			List<ContengencyBean> oldContengencyBeanList) {
		this.oldContengencyBeanList = oldContengencyBeanList;
	}

	
	public void addOldContengencyBeanList(
			 ContengencyBean contengencyBean) {
		this.oldContengencyBeanList.add(contengencyBean);
	}
	
	public Integer getExpenditureAmount() {
		return expenditureAmount;
	}

	public void setExpenditureAmount(Integer expenditureAmount) {
		this.expenditureAmount = expenditureAmount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getTotalConengencyAmount() {
		return totalConengencyAmount;
	}

	public void setTotalConengencyAmount(Integer totalConengencyAmount) {
		this.totalConengencyAmount = totalConengencyAmount;
	}

	public Integer getCommulativeExpenditure() {
		return commulativeExpenditure;
	}

	public void setCommulativeExpenditure(Integer commulativeExpenditure) {
		this.commulativeExpenditure = commulativeExpenditure;
	}

	public Integer getRemainingContengency() {
		return remainingContengency;
	}

	public void setRemainingContengency(Integer remainingContengency) {
		this.remainingContengency = remainingContengency;
	}

}