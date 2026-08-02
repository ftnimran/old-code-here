package com.res.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "inspection_sqm_answer_image")
public class InspectionSqmAnswerImage implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@JoinColumn(name = "DOCUMENT_ID", referencedColumnName = "ID")
	@OneToOne
	private DocumentUpload documentUpload;


	@JoinColumn(name = "work_id", referencedColumnName = "id") 
	@ManyToOne(fetch = FetchType.LAZY)
	private Work work;
	
	@JoinColumn(name = "answer_by", referencedColumnName = "id") 
	@ManyToOne
	private Users answerBy;
	
	@Column(name = "answer_by_role")
	private String answerByRole;
	
	
	
	@Column(name = "created_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
	
	@JoinColumn(name = "sqm_allocation_id", referencedColumnName = "id") 
	@ManyToOne
	private SqmAllocation sqmAllocationId;
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public Users getAnswerBy() {
		return answerBy;
	}

	public void setAnswerBy(Users answerBy) {
		this.answerBy = answerBy;
	}

	public String getAnswerByRole() {
		return answerByRole;
	}

	public void setAnswerByRole(String answerByRole) {
		this.answerByRole = answerByRole;
	}

	public DocumentUpload getDocumentUpload() {
		return documentUpload;
	}

	public void setDocumentUpload(DocumentUpload documentUpload) {
		this.documentUpload = documentUpload;
	}

	public SqmAllocation getSqmAllocationId() {
		return sqmAllocationId;
	}

	public void setSqmAllocationId(SqmAllocation sqmAllocationId) {
		this.sqmAllocationId = sqmAllocationId;
	}

	

	

	

	


	


}
