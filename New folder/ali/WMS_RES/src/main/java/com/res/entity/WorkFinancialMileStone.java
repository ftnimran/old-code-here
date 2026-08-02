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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "work_financial_mile_stone")
public class WorkFinancialMileStone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="financial_stage",referencedColumnName="Financial_STAGE_ID")
	private FinancialStageType financialStage;

	@Column(name = "cumulative")
	private Long cumulative;
	
	@Column(name = "completion_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date completionDate;
	
	@JoinColumn(name = "`work_agreement_Id`", referencedColumnName = "id") 
	@ManyToOne(fetch = FetchType.LAZY)
	private WorkAgreement workAgreement;
	
	@JoinColumn(name = "`revision_Id`", referencedColumnName = "id") 
	@ManyToOne(fetch = FetchType.LAZY)
	private WorkAgreementRevision workAgreementRevision;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public WorkAgreement getWorkAgreement() {
		return workAgreement;
	}

	public void setWorkAgreement(WorkAgreement workAgreement) {
		this.workAgreement = workAgreement;
	}
	
	public FinancialStageType getFinancialStage() {
		return financialStage;
	}

	public void setFinancialStage(FinancialStageType financialStage) {
		this.financialStage = financialStage;
	}

	public Long getCumulative() {
		return cumulative;
	}

	public void setCumulative(Long cumulative) {
		this.cumulative = cumulative;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public WorkAgreementRevision getWorkAgreementRevision() {
		return workAgreementRevision;
	}

	public void setWorkAgreementRevision(WorkAgreementRevision workAgreementRevision) {
		this.workAgreementRevision = workAgreementRevision;
	}

	

}
