package com.res.bean;

import com.res.entity.Status;

public class SORBean {

	private Long id;
	
	private Long refSorId;
	
    private String referenceName;
	
    private YearBean year;
    
	private String dateOfAdoption;
	
	private Integer index;
	
	private Status status;
	
    private String actionPerformedBy;
	
	public String getActionPerformedBy() {
		return actionPerformedBy;
	}

	public void setActionPerformedBy(String actionPerformedBy) {
		this.actionPerformedBy = actionPerformedBy;
	}
	
	public SORBean() {
	}
	
	
	public SORBean(Long id) {
		super();
		this.id = id;
	}

	private String remarks;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SORBean(YearBean year) {
		super();
		this.year = year;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getDateOfAdoption() {
		return dateOfAdoption;
	}

	public void setDateOfAdoption(String dateOfAdoption) {
		this.dateOfAdoption = dateOfAdoption;
	}


	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setYear(YearBean year) {
		this.year = year;
	}

	public YearBean getYear() {
		return year;
	}

	public Long getRefSorId() {
		return refSorId;
	}

	public void setRefSorId(Long refSorId) {
		this.refSorId = refSorId;
	}


	
	
}