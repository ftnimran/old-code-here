package com.res.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
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

//@Audited
@Entity
@Table(name = "TECHNICAL_SANCTION")
public class TechnicalSanction extends Auditable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	//--------------------------
	
	@Column(name = "TECHNICAL_SANCTION_NO")
	private String technicalSanctionNo;
	
	@Column(name = "TECHNICAL_SANCTION_NO_OLD")
	private String technicalSanctionNoOld;
	
	@Column(name = "ts_dispatch_number")
	private String tsDispatchNumber;

	@Column(name = "TECHNICAL_SANCTION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date technicalSanctionDate;

	
	@Column(name = "TECHNICAL_SANCTION_DISPATCH_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date tsDispatchDate;

	
	//--------------------------

	@JoinColumn(name = "TECHNICAL_SANCTION_TYPE_ID", referencedColumnName = "TECHNICAL_SANCTION_TYPE_ID")
	@ManyToOne
	private TechnicalSanctionType technicalSanctionType;
	
	@JoinColumn(name="technical_sanction_status", referencedColumnName = "ID")
	@ManyToOne
	private TechnicalStatus technicalStatus;
	
	@JoinColumn(name="work_estimate_id", referencedColumnName = "ID")
	@ManyToOne
	private WorkEstimation workEstimation;
	
	
	@JoinColumn(name = "TECHNICAL_SANCTION_FILE", referencedColumnName = "ID")
	@OneToOne
	private DocumentUpload documentUploadTechnical;
	
	@JoinColumn(name = "LATEST_DRAWING_COPY", referencedColumnName = "ID")
	@OneToOne
	private DocumentUpload documentUploadDrawing;
	
	@JoinColumn(name = "ESTIMATE_FILE", referencedColumnName = "ID")
	@OneToOne
	private DocumentUpload documentUploadEstimate;
	
	@JoinColumn(name = "WORK_ID", referencedColumnName = "ID")
	@OneToOne
	private Work work;
	
	@Column(name="technical_sanction_amount")
	private BigDecimal technicalSanctionAmount;
	
	/*@Column(name = "REQUEST_STATUS")
	private Long requestStatus;
	
	private String status;
	
	@Column(name = "TECHNICAL_SANCTION_OLD_NO")
	private String technicalSanctionOldNo;
	*/
	
	
	@Column(name = "is_districtname")
	private Boolean isDistrictName;
	
	@Column(name = "is_grampanchayatname")
	private Boolean isGrampanchayatName;
	
	@Column(name = "is_linedepartmentname")
	private Boolean isLinedepartmentName;
	
	public String getTsDispatchNumber() {
		return tsDispatchNumber;
	}

	public void setTsDispatchNumber(String tsDispatchNumber) {
		this.tsDispatchNumber = tsDispatchNumber;
	}

	
	@Column(name="revision_no")
	private Integer revisionNo;
	
	@JoinColumn(name="TS_ISSUING_AUTHORITY" , referencedColumnName="ID")
	@OneToOne
	//@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Designation tsIssuingAuthority;
	
	@Column(name = "TS_AUTHORITY_NAME")
	private String tsAuthorityName;
	
	@Column(name = "is_ce_officename")
	private Boolean isCeOfficeName;
	
	@Column(name = "is_se_officename")
	private Boolean isSeOfficeName;
	
	@Column(name="is_ee_officename")
	private Boolean isEeOfficeName;
	
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	@ManyToOne
	private TechnicalSanction parentId;
	
	@Column(name = "competentAuthName")
	private String competentAuthName;
	
	@Column(name = "competent_auth_desig")
	private String competentAuthDesig;

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
	
	
	
	public TechnicalSanction getParentId() {
		return parentId;
	}

	public void setParentId(TechnicalSanction parentId) {
		this.parentId = parentId;
	}

	public Boolean getIsEeOfficeName() {
		return isEeOfficeName;
	}

	public void setIsEeOfficeName(Boolean isEeOfficeName) {
		this.isEeOfficeName = isEeOfficeName;
	}


	public Boolean getIsCeOfficeName() {
		return isCeOfficeName;
	}

	public void setIsCeOfficeName(Boolean isCeOfficeName) {
		this.isCeOfficeName = isCeOfficeName;
	}

	public Boolean getIsSeOfficeName() {
		return isSeOfficeName;
	}

	public void setIsSeOfficeName(Boolean isSeOfficeName) {
		this.isSeOfficeName = isSeOfficeName;
	}

	public Boolean getIsDistrictName() {
		return isDistrictName;
	}

	public void setIsDistrictName(Boolean isDistrictName) {
		this.isDistrictName = isDistrictName;
	}

	public Boolean getIsGrampanchayatName() {
		return isGrampanchayatName;
	}

	public void setIsGrampanchayatName(Boolean isGrampanchayatName) {
		this.isGrampanchayatName = isGrampanchayatName;
	}

	public Boolean getIsLinedepartmentName() {
		return isLinedepartmentName;
	}

	public void setIsLinedepartmentName(Boolean isLinedepartmentName) {
		this.isLinedepartmentName = isLinedepartmentName;
	}

	public TechnicalSanction() {
	}

	public TechnicalSanction(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TechnicalSanctionType getTechnicalSanctionType() {
		return technicalSanctionType;
	}

	public void setTechnicalSanctionType(TechnicalSanctionType technicalSanctionType) {
		this.technicalSanctionType = technicalSanctionType;
	}

	public String getTechnicalSanctionNo() {
		return technicalSanctionNo;
	}

	public void setTechnicalSanctionNo(String technicalSanctionNo) {
		this.technicalSanctionNo = technicalSanctionNo;
	}

	public Date getTechnicalSanctionDate() {
		return technicalSanctionDate;
	}

	public void setTechnicalSanctionDate(Date technicalSanctionDate) {
		this.technicalSanctionDate = technicalSanctionDate;
	}
	
	public DocumentUpload getDocumentUploadTechnical() {
		return documentUploadTechnical;
	}

	public void setDocumentUploadTechnical(DocumentUpload documentUploadTechnical) {
		this.documentUploadTechnical = documentUploadTechnical;
	}

	public DocumentUpload getDocumentUploadDrawing() {
		return documentUploadDrawing;
	}

	public void setDocumentUploadDrawing(DocumentUpload documentUploadDrawing) {
		this.documentUploadDrawing = documentUploadDrawing;
	}

	public DocumentUpload getDocumentUploadEstimate() {
		return documentUploadEstimate;
	}

	public void setDocumentUploadEstimate(DocumentUpload documentUploadEstimate) {
		this.documentUploadEstimate = documentUploadEstimate;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	 

	public BigDecimal getTechnicalSanctionAmount() {
		return technicalSanctionAmount;
	}

	public void setTechnicalSanctionAmount(BigDecimal technicalSanctionAmount) {
		this.technicalSanctionAmount = technicalSanctionAmount;
	}

	public Integer getRevisionNo() {
		return revisionNo;
	}

	public void setRevisionNo(Integer revisionNo) {
		this.revisionNo = revisionNo;
	}

	public Designation getTsIssuingAuthority() {
		return tsIssuingAuthority;
	}

	public void setTsIssuingAuthority(Designation tsIssuingAuthority) {
		this.tsIssuingAuthority = tsIssuingAuthority;
	}

	public String getTsAuthorityName() {
		return tsAuthorityName;
	}

	public void setTsAuthorityName(String tsAuthorityName) {
		this.tsAuthorityName = tsAuthorityName;
	}

	public TechnicalStatus getTechnicalStatus() {
		return technicalStatus;
	}

	public void setTechnicalStatus(TechnicalStatus technicalStatus) {
		this.technicalStatus = technicalStatus;
	}

	public WorkEstimation getWorkEstimation() {
		return workEstimation;
	}

	public void setWorkEstimation(WorkEstimation workEstimation) {
		this.workEstimation = workEstimation;
	}


	public String getTechnicalSanctionNoOld() {
		return technicalSanctionNoOld;
	}

	public void setTechnicalSanctionNoOld(String technicalSanctionNoOld) {
		this.technicalSanctionNoOld = technicalSanctionNoOld;
	}

	public Date getTsDispatchDate() {
		return tsDispatchDate;
	}

	public void setTsDispatchDate(Date tsDispatchDate) {
		this.tsDispatchDate = tsDispatchDate;
	}
	
}
