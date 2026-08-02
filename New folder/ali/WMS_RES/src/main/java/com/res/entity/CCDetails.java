package com.res.entity;

import java.io.Serializable;
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
@Table(name = "cc_details")
public class CCDetails extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "work_completed_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date workCompletedOn;
	
	@Column(name = "used_mb_no")
	private String usedMBNo;
	
	@Column(name = "taken_over_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date takenOverOn;
	
	@Column(name = "page_no")
	private String pageNo;
	
	@Column(name = "mb_no")
	private String mbNo;
	
	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Column(name = "physical_cc_issued_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date physicalCCIssuedOn;
	
	@Column(name = "financial_cc_issued_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date financialCCIssuedOn;	
		
	@Column(name = "cc_inspection_submitted_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ccInspectionSubmittedOn;
	
	@Column(name = "approved")
	private Boolean approved;	
	
	@JoinColumn(name = "WORK_ID", referencedColumnName = "ID")
	@ManyToOne
	private Work work;	
	
	@Column(name = "remark")
	private String remark;
	
	public CCDetails() {
	 
	}

	public CCDetails(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getWorkCompletedOn() {
		return workCompletedOn;
	}

	public void setWorkCompletedOn(Date workCompletedOn) {
		this.workCompletedOn = workCompletedOn;
	}

	public Date getPhysicalCCIssuedOn() {
		return physicalCCIssuedOn;
	}

	public void setPhysicalCCIssuedOn(Date physicalCCIssuedOn) {
		this.physicalCCIssuedOn = physicalCCIssuedOn;
	}

	public Date getFinancialCCIssuedOn() {
		return financialCCIssuedOn;
	}

	public void setFinancialCCIssuedOn(Date financialCCIssuedOn) {
		this.financialCCIssuedOn = financialCCIssuedOn;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public String getUsedMBNo() {
		return usedMBNo;
	}

	public void setUsedMBNo(String usedMBNo) {
		this.usedMBNo = usedMBNo;
	}

	public Date getTakenOverOn() {
		return takenOverOn;
	}

	public void setTakenOverOn(Date takenOverOn) {
		this.takenOverOn = takenOverOn;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getMbNo() {
		return mbNo;
	}

	public void setMbNo(String mbNo) {
		this.mbNo = mbNo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getCcInspectionSubmittedOn() {
		return ccInspectionSubmittedOn;
	}

	public void setCcInspectionSubmittedOn(Date ccInspectionSubmittedOn) {
		this.ccInspectionSubmittedOn = ccInspectionSubmittedOn;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}	
	
}
