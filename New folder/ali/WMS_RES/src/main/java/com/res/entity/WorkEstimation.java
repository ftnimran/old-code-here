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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the mst_work_status database table.
 * 
 */
@Entity
@Table(name = "work_estimate")
public class WorkEstimation extends Auditable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@JoinColumn(name = "WORK_ID", referencedColumnName = "ID")
	@ManyToOne
	private Work work;

	@Column(name = "enabled")
	private Boolean enabled;

	@Column(name = "total_amount")
	private BigDecimal totalAmount;

	@Column(name = "total_amount_labour")
	private BigDecimal totalAmountLabour;

	@Column(name = "total_amount_material")
	private BigDecimal totalAmountMaterial;

	@Column(name = "grand_total")
	private BigDecimal grandTotal;

	@Column(name = "total_amount_machinery")
	private BigDecimal totalAmountMachinery;

	@Column(name = "total_amount_loosening_of_soil")
	private BigDecimal totalAmountLooseningOfSoil;

	@Column(name = "total_amount_excavation")
	private BigDecimal totalAmountExcavation;

	@Column(name = "estimationType")
	private String estimationType;

	@JoinColumn(name = "status", referencedColumnName = "ID")
	@ManyToOne
	private WorkEstimationStatus status;

	@Column(name = "comments_sub_eng")
	private String commentsSubEng;
	
	@Column(name = "comments_sdo")
	private String commentsSdo;

	@Column(name = "comments_ae")
	private String commentsAe;

	@Column(name = "comments_ee")
	private String commentsEe;

	@Column(name = "comments_se")
	private String commentsSe;

	@Column(name = "comments_ce")
	private String commentsCe;

	@Column(name = "ae_fwd_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date aeFwdDate;

	@Column(name = "sub_eng_fwd_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date subEngFwdDate;
	
	@Column(name = "sdo_fwd_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date sdoFwdDate;

	@Column(name = "ee_fwd_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date eeFwdDate;

	@Column(name = "se_fwd_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date seFwdDate;

	@Column(name = "ce_fwd_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ceFwdDate;

	@Column(name = "expected_tendered_rate_per")
	private BigDecimal expectedTenderedRatePer;
	@Column(name = "expected_tendered_amt")
	private BigDecimal expectedTenderedAmt;

	@Column(name = "overhead_charges_per")
	private BigDecimal overheadChargesPer;
	@Column(name = "overhead_charges_amt")
	private BigDecimal overheadChargesAmt;

	@Column(name = "labour_welfare_component_per")
	private BigDecimal labourWelfareComponentPer;
	@Column(name = "labour_welfare_component_amt")
	private BigDecimal labourWelfareComponentAmt;

	@Column(name = "applicable_gst_per")
	private BigDecimal applicableGstPer;
	@Column(name = "applicable_gst_amt")
	private BigDecimal applicableGstAmt;

	@Column(name = "work_charge_contingency_per")
	private BigDecimal workChargeContingencyPer;
	@Column(name = "work_charge_contingency_amt")
	private BigDecimal workChargeContingencyAmt;

	@Column(name = "administrative_expenditure_per")
	private BigDecimal administrativeExpenditurePer;
	@Column(name = "administrative_expenditure_amt")
	private BigDecimal administrativeExpenditureAmt;

	@Column(name = "others_charges")
	private BigDecimal othersCharges;

	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	@ManyToOne
	private WorkEstimation parentId;

	/*
	 * @JoinColumn(name = "TS_REQUEST_STATUS", referencedColumnName = "ID")
	 * 
	 * @OneToOne // @Audited(targetAuditMode =
	 * RelationTargetAuditMode.NOT_AUDITED) private TechnicalStatus tsStatusId;
	 */

	@Column(name = "estimation_approved_by")
	private String estimationApprovedBy;
	
	@Column(name = "competentAuthName")
	private String competentAuthName;
	
	@Column(name = "competent_auth_desig")
	private String competentAuthDesig;
	
	@Column(name = "revised_letter_no")
	private String revisedLetterNo;
	
	@Column(name = "letter_no_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date letterNoDate;

//	@Transient
	@Column(name = "has_non_sor_items")
	private Boolean hasNonSorItems = false;
	
//	@Transient
	@Column(name = "ten_percent_check")
	private Boolean tenPercentCheck = false;
	 
	public String getCompetentAuthName() {
		return competentAuthName;
	}

	public void setCompetentAuthName(String competentAuthName) {
		this.competentAuthName = competentAuthName;
	}

	public String getCompetentAuthDesig() {
		return competentAuthDesig;
	}

	public void setCompetentAuthDesig(String competentAuthDesig) {
		this.competentAuthDesig = competentAuthDesig;
	}

	public String getEstimationApprovedBy() {
		return estimationApprovedBy;
	}

	public void setEstimationApprovedBy(String estimationApprovedBy) {
		this.estimationApprovedBy = estimationApprovedBy;
	}

	public String getCommentsSubEng() {
		return commentsSubEng;
	}

	public void setCommentsSubEng(String commentsSubEng) {
		this.commentsSubEng = commentsSubEng;
	}

	public Date getAeFwdDate() {
		return aeFwdDate;
	}

	public void setAeFwdDate(Date aeFwdDate) {
		this.aeFwdDate = aeFwdDate;
	}

	public Date getSubEngFwdDate() {
		return subEngFwdDate;
	}

	public void setSubEngFwdDate(Date subEngFwdDate) {
		this.subEngFwdDate = subEngFwdDate;
	}

	public WorkEstimation getParentId() {
		return parentId;
	}

	public void setParentId(WorkEstimation parentId) {
		this.parentId = parentId;
	}

	public BigDecimal getWorkChargeContingencyPer() {
		return workChargeContingencyPer;
	}

	public void setWorkChargeContingencyPer(BigDecimal workChargeContingencyPer) {
		this.workChargeContingencyPer = workChargeContingencyPer;
	}

	public BigDecimal getWorkChargeContingencyAmt() {
		return workChargeContingencyAmt;
	}

	public void setWorkChargeContingencyAmt(BigDecimal workChargeContingencyAmt) {
		this.workChargeContingencyAmt = workChargeContingencyAmt;
	}

	public BigDecimal getAdministrativeExpenditurePer() {
		return administrativeExpenditurePer;
	}

	public void setAdministrativeExpenditurePer(
			BigDecimal administrativeExpenditurePer) {
		this.administrativeExpenditurePer = administrativeExpenditurePer;
	}

	public BigDecimal getAdministrativeExpenditureAmt() {
		return administrativeExpenditureAmt;
	}

	public void setAdministrativeExpenditureAmt(
			BigDecimal administrativeExpenditureAmt) {
		this.administrativeExpenditureAmt = administrativeExpenditureAmt;
	}

	public BigDecimal getOthersCharges() {
		return othersCharges;
	}

	public void setOthersCharges(BigDecimal othersCharges) {
		this.othersCharges = othersCharges;
	}

	public BigDecimal getExpectedTenderedRatePer() {
		return expectedTenderedRatePer;
	}

	public void setExpectedTenderedRatePer(BigDecimal expectedTenderedRatePer) {
		this.expectedTenderedRatePer = expectedTenderedRatePer;
	}

	public BigDecimal getExpectedTenderedAmt() {
		return expectedTenderedAmt;
	}

	public void setExpectedTenderedAmt(BigDecimal expectedTenderedAmt) {
		this.expectedTenderedAmt = expectedTenderedAmt;
	}

	public BigDecimal getOverheadChargesPer() {
		return overheadChargesPer;
	}

	public void setOverheadChargesPer(BigDecimal overheadChargesPer) {
		this.overheadChargesPer = overheadChargesPer;
	}

	public BigDecimal getOverheadChargesAmt() {
		return overheadChargesAmt;
	}

	public void setOverheadChargesAmt(BigDecimal overheadChargesAmt) {
		this.overheadChargesAmt = overheadChargesAmt;
	}

	public BigDecimal getLabourWelfareComponentPer() {
		return labourWelfareComponentPer;
	}

	public void setLabourWelfareComponentPer(
			BigDecimal labourWelfareComponentPer) {
		this.labourWelfareComponentPer = labourWelfareComponentPer;
	}

	public BigDecimal getLabourWelfareComponentAmt() {
		return labourWelfareComponentAmt;
	}

	public void setLabourWelfareComponentAmt(
			BigDecimal labourWelfareComponentAmt) {
		this.labourWelfareComponentAmt = labourWelfareComponentAmt;
	}

	public BigDecimal getApplicableGstPer() {
		return applicableGstPer;
	}

	public void setApplicableGstPer(BigDecimal applicableGstPer) {
		this.applicableGstPer = applicableGstPer;
	}

	public BigDecimal getApplicableGstAmt() {
		return applicableGstAmt;
	}

	public void setApplicableGstAmt(BigDecimal applicableGstAmt) {
		this.applicableGstAmt = applicableGstAmt;
	}

	public WorkEstimationStatus getStatus() {
		return status;
	}

	public void setStatus(WorkEstimationStatus status) {
		this.status = status;
	}

	public String getCommentsAe() {
		return commentsAe;
	}

	public void setCommentsAe(String commentsAe) {
		this.commentsAe = commentsAe;
	}

	public String getCommentsEe() {
		return commentsEe;
	}

	public void setCommentsEe(String commentsEe) {
		this.commentsEe = commentsEe;
	}

	public String getCommentsSe() {
		return commentsSe;
	}

	public void setCommentsSe(String commentsSe) {
		this.commentsSe = commentsSe;
	}

	public String getCommentsCe() {
		return commentsCe;
	}

	public void setCommentsCe(String commentsCe) {
		this.commentsCe = commentsCe;
	}

	public Date getEeFwdDate() {
		return eeFwdDate;
	}

	public void setEeFwdDate(Date eeFwdDate) {
		this.eeFwdDate = eeFwdDate;
	}

	public Date getSeFwdDate() {
		return seFwdDate;
	}

	public void setSeFwdDate(Date seFwdDate) {
		this.seFwdDate = seFwdDate;
	}

	public Date getCeFwdDate() {
		return ceFwdDate;
	}

	public void setCeFwdDate(Date ceFwdDate) {
		this.ceFwdDate = ceFwdDate;
	}

	public WorkEstimation() {
	}

	public WorkEstimation(Long id) {
		this.id = id;
	}

	public String getEstimationType() {
		return estimationType;
	}

	public void setEstimationType(String estimationType) {
		this.estimationType = estimationType;
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getTotalAmountLabour() {
		return totalAmountLabour;
	}

	public void setTotalAmountLabour(BigDecimal totalAmountLabour) {
		this.totalAmountLabour = totalAmountLabour;
	}

	public BigDecimal getTotalAmountMaterial() {
		return totalAmountMaterial;
	}

	public void setTotalAmountMaterial(BigDecimal totalAmountMaterial) {
		this.totalAmountMaterial = totalAmountMaterial;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public BigDecimal getTotalAmountMachinery() {
		return totalAmountMachinery;
	}

	public void setTotalAmountMachinery(BigDecimal totalAmountMachinery) {
		this.totalAmountMachinery = totalAmountMachinery;
	}

	public BigDecimal getTotalAmountLooseningOfSoil() {
		return totalAmountLooseningOfSoil;
	}

	public void setTotalAmountLooseningOfSoil(
			BigDecimal totalAmountLooseningOfSoil) {
		this.totalAmountLooseningOfSoil = totalAmountLooseningOfSoil;
	}

	public BigDecimal getTotalAmountExcavation() {
		return totalAmountExcavation;
	}

	public void setTotalAmountExcavation(BigDecimal totalAmountExcavation) {
		this.totalAmountExcavation = totalAmountExcavation;
	}

	public String getRevisedLetterNo() {
		return revisedLetterNo;
	}

	public void setRevisedLetterNo(String revisedLetterNo) {
		this.revisedLetterNo = revisedLetterNo;
	}

	public Date getLetterNoDate() {
		return letterNoDate;
	}

	public void setLetterNoDate(Date letterNoDate) {
		this.letterNoDate = letterNoDate;
	}

	public String getCommentsSdo() {
		return commentsSdo;
	}

	public void setCommentsSdo(String commentsSdo) {
		this.commentsSdo = commentsSdo;
	}

	public Date getSdoFwdDate() {
		return sdoFwdDate;
	}

	public void setSdoFwdDate(Date sdoFwdDate) {
		this.sdoFwdDate = sdoFwdDate;
	}

	public Boolean getHasNonSorItems() {
		return hasNonSorItems;
	}

	public void setHasNonSorItems(Boolean hasNonSorItems) {
		this.hasNonSorItems = hasNonSorItems;
	}

	public Boolean getTenPercentCheck() {
		return tenPercentCheck;
	}

	public void setTenPercentCheck(Boolean tenPercentCheck) {
		this.tenPercentCheck = tenPercentCheck;
	}


}