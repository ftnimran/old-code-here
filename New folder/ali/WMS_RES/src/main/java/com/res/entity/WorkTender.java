package com.res.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "work_tender")
public class WorkTender extends Auditable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@JoinColumn(name = "WORK_ID", referencedColumnName = "ID")
	@ManyToOne
	private Work work;

	@JoinColumn(name = "administrative_sanction_id", referencedColumnName = "id")
	@OneToOne
	private AdministrationSanction administrationSanction;

	@JoinColumn(name = "status", referencedColumnName = "ID")
	@ManyToOne
	private TenderStatus status;

	@JoinColumn(name = "contractor_id", referencedColumnName = "ID")
	@ManyToOne
	private Contractor contractorId;	

	@Column(name = "tender_cost")
	private BigDecimal tenderCost;

	@Column(name = "tender_number")
	private String tenderNumber;

	@Column(name = "tender_opening_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date tenderOpeningDate;

	@Column(name = "amount_of_contract")
	private BigDecimal amountOfContract;

	@Column(name = "no_of_participants")
	private String noOFparticipants;

	@Column(name = "tendered_rate")
	private String tenderedRate;
	
	@Column(name = "tendered_rate_sign")
	private String tenderedRateSign;
	//Rakesh
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	@ManyToOne
	private WorkTender parentId;
	
	public WorkTender getParentId() {
		return parentId;
	}

	public void setParentId(WorkTender parentId) {
		this.parentId = parentId;
	}

	public String getTenderedRateSign() {
		return tenderedRateSign;
	}

	public void setTenderedRateSign(String tenderedRateSign) {
		this.tenderedRateSign = tenderedRateSign;
	}

	public WorkTender(Long id) {
		this.id = id;
	}

	public WorkTender() {
		// TODO Auto-generated constructor stub
	}

	public String getTenderNumber() {
		return tenderNumber;
	}

	public void setTenderNumber(String tenderNumber) {
		this.tenderNumber = tenderNumber;
	}

	public Date getTenderOpeningDate() {
		return tenderOpeningDate;
	}

	public void setTenderOpeningDate(Date tenderOpeningDate) {
		this.tenderOpeningDate = tenderOpeningDate;
	}

	public BigDecimal getAmountOfContract() {
		return amountOfContract;
	}

	public void setAmountOfContract(BigDecimal amountOfContract) {
		this.amountOfContract = amountOfContract;
	}

	public String getNoOFparticipants() {
		return noOFparticipants;
	}

	public void setNoOFparticipants(String noOFparticipants) {
		this.noOFparticipants = noOFparticipants;
	}

	public Contractor getContractorId() {
		return contractorId;
	}

	public void setContractorId(Contractor contractorId) {
		this.contractorId = contractorId;
	}

	public String getTenderedRate() {
		return tenderedRate;
	}

	public void setTenderedRate(String tenderedRate) {
		this.tenderedRate = tenderedRate;
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

	public TenderStatus getStatus() {
		return status;
	}

	public BigDecimal getTenderCost() {
		return tenderCost;
	}

	public void setTenderCost(BigDecimal tenderCost) {
		this.tenderCost = tenderCost;
	}

	

	public AdministrationSanction getAdministrationSanction() {
		return administrationSanction;
	}

	public void setAdministrationSanction(AdministrationSanction administrationSanction) {
		this.administrationSanction = administrationSanction;
	}
	public void setStatus(TenderStatus status) {
		this.status = status;
	}
}
