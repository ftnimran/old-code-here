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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "physical_cc_dispatch_details")
public class PhysicalCCDispatchDetails extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@JoinColumn(name = "WORK_ID", referencedColumnName = "ID")
	@OneToOne
	private Work work;
	
	@Column(name = "DISPATCH_NUMBER")
	private String dispatchNumber;	

	@Column(name = "DISPATCH_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dispatchDate;

	@Column(name = "REMARKS")
	private String remarks;	

	@JoinColumn(name = "FILE", referencedColumnName = "ID")
	@OneToOne
	private DocumentUpload ccFile;

	@Column(name = "status")	
	private Short status;
	
	public PhysicalCCDispatchDetails() {
	 
	}

	public PhysicalCCDispatchDetails(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public String getDispatchNumber() {
		return dispatchNumber;
	}

	public void setDispatchNumber(String dispatchNumber) {
		this.dispatchNumber = dispatchNumber;
	}

	public Date getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}	
	
	public DocumentUpload getCcFile() {
		return ccFile;
	}

	public void setCcFile(DocumentUpload ccFile) {
		this.ccFile = ccFile;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	
	
	
}
