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
@Table(name = "work_agreement")
public class WorkAgreement extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	 
	@JoinColumn(name = "AGREEMENT_COPY", referencedColumnName = "ID")
	@OneToOne
	private DocumentUpload agreementCopy;

	@Column(name = "AGREEMENT_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date agreementDate;

	@Column(name = "AGREEMENT_NUMBER")
	private String agreementNumber;	

	@Column(name = "tentative_completion_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date tentativeCompletionDate;

	@Column(name = "written_order_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date written_order_date;
	
	@JoinColumn(name = "WORK_ID", referencedColumnName = "ID")
	@OneToOne
	private Work work;
	
	@JoinColumn(name = "WORK_TENDER_ID", referencedColumnName = "ID")
	@OneToOne
	private WorkTender workTender;
	
	@JoinColumn(name = "status", referencedColumnName = "id")
	@ManyToOne
	private MasterWorkAgreementStatus status;
	
	//Rakesh
		@JoinColumn(name = "parent_id", referencedColumnName = "id")
		@ManyToOne
		private WorkAgreement parentId;
		
	public WorkAgreement getParentId() {
			return parentId;
		}

		public void setParentId(WorkAgreement parentId) {
			this.parentId = parentId;
		}

	public WorkAgreement() {
	 
	}

	public WorkAgreement(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DocumentUpload getAgreementCopy() {
		return agreementCopy;
	}

	public void setAgreementCopy(DocumentUpload agreementCopy) {
		this.agreementCopy = agreementCopy;
	}

	public Date getAgreementDate() {
		return agreementDate;
	}

	public void setAgreementDate(Date agreementDate) {
		this.agreementDate = agreementDate;
	}

	public String getAgreementNumber() {
		return agreementNumber;
	}

	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
	}

	public Date getTentativeCompletionDate() {
		return tentativeCompletionDate;
	}

	public void setTentativeCompletionDate(Date tentativeCompletionDate) {
		this.tentativeCompletionDate = tentativeCompletionDate;
	}

	public Date getWritten_order_date() {
		return written_order_date;
	}

	public void setWritten_order_date(Date written_order_date) {
		this.written_order_date = written_order_date;
	}

	public MasterWorkAgreementStatus getStatus() {
		return status;
	}

	public void setStatus(MasterWorkAgreementStatus status) {
		this.status = status;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public WorkTender getWorkTender() {
		return workTender;
	}

	public void setWorkTender(WorkTender workTender) {
		this.workTender = workTender;
	}

	
	
}
