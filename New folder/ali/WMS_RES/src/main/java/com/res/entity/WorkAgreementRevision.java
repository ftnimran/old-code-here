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
@Table(name = "work_agreement_revision")
public class WorkAgreementRevision extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;	

	@Column(name = "revised_on")
	private Date revisedOn;
	
	@JoinColumn(name = "agreement_id", referencedColumnName = "ID")
	@OneToOne
	private WorkAgreement workAgreement;
	
	public WorkAgreementRevision() {
	 
	}

	public WorkAgreementRevision(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRevisedOn() {
		return revisedOn;
	}

	public void setRevisedOn(Date revisedOn) {
		this.revisedOn = revisedOn;
	}

	public WorkAgreement getWorkAgreement() {
		return workAgreement;
	}

	public void setWorkAgreement(WorkAgreement workAgreement) {
		this.workAgreement = workAgreement;
	}

	
	
	
}
