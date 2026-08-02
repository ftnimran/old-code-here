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

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
//@Audited
@Entity
@Table(name = "ADMINISTRATIVE_SANCTION")
public class AdministrationSanction extends Auditable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "ADMINISTRATION_SANCTION_TYPE_ID", referencedColumnName = "ADMINISTRATION_SANCTION_TYPE_ID")
	@ManyToOne
	//@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private AdministrationSanctionType administrationSanctionType;
	
	@Column(name = "ADMINISTRATIVE_SANCTION_NO")
	private String administrativeSanctionNo;
	
	@Column(name = "proposeddistance")
	private BigDecimal proposeddistance;
	
	@Column(name = "ADMINISTRATIVE_SANCTION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date administrativeSanctionDate;
	
	@JoinColumn(name = "ISSUING_AUTHORITY_ID", referencedColumnName = "ID")
	@OneToOne
	//@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private IssuingAuthority issuingAuthority;
	
	@JoinColumn(name = "ADMINISTRATION_SANCTION_FILE", referencedColumnName = "ID")
	@OneToOne
	//@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private DocumentUpload documentUpload;
	
	@JoinColumn(name = "WORK_ID", referencedColumnName = "ID")
	@ManyToOne
	private Work work;
	
	@Column(name="administrative_sanction_amount")
	private BigDecimal administrativeSanctionAmount;
	
	
	private String status;
	
	@Column(name = "REQUEST_STATUS")
	private Long requestStatus;
	

	@Column(name="revision_no")
	private Integer revisionNo;
	
	@Column(name = "AS_AUTHORITY_NAME")
	private String asAuthorityName;
	
	
	@Column(name="contingency_amount")
	private BigDecimal contingencyAmount;
	
	//Rakesh
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	@ManyToOne
	private AdministrationSanction parentId;
	
	public AdministrationSanction getParentId() {
		return parentId;
	}

	public void setParentId(AdministrationSanction parentId) {
		this.parentId = parentId;
	}

	public TechnicalSanction getTechnicalSanction() {
		return technicalSanction;
	}

	public void setTechnicalSanction(TechnicalSanction technicalSanction) {
		this.technicalSanction = technicalSanction;
	}

	public AdministrativeStatus getAdministrativeStatus() {
		return administrativeStatus;
	}

	public void setAdministrativeStatus(AdministrativeStatus administrativeStatus) {
		this.administrativeStatus = administrativeStatus;
	}

	@JoinColumn(name="technical_sanction_id", referencedColumnName = "ID")
	@ManyToOne
	private TechnicalSanction technicalSanction;
	
	
	@JoinColumn(name="administrative_sanction_status", referencedColumnName = "ID")
	@ManyToOne
	private AdministrativeStatus administrativeStatus;
	
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

	
	public AdministrationSanction() {
	}

	public AdministrationSanction(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AdministrationSanctionType getAdministrationSanctionType() {
		return administrationSanctionType;
	}

	public void setAdministrationSanctionType(
			AdministrationSanctionType administrationSanctionType) {
		this.administrationSanctionType = administrationSanctionType;
	}

	public String getAdministrativeSanctionNo() {
		return administrativeSanctionNo;
	}

	public void setAdministrativeSanctionNo(String administrativeSanctionNo) {
		this.administrativeSanctionNo = administrativeSanctionNo;
	}

	public Date getAdministrativeSanctionDate() {
		return administrativeSanctionDate;
	}

	public void setAdministrativeSanctionDate(Date administrativeSanctionDate) {
		this.administrativeSanctionDate = administrativeSanctionDate;
	}

	public DocumentUpload getDocumentUpload() {
		return documentUpload;
	}

	public void setDocumentUpload(DocumentUpload documentUpload) {
		this.documentUpload = documentUpload;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public IssuingAuthority getIssuingAuthority() {
		return issuingAuthority;
	}

	public void setIssuingAuthority(IssuingAuthority issuingAuthority) {
		this.issuingAuthority = issuingAuthority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getAdministrativeSanctionAmount() {
		return administrativeSanctionAmount;
	}

	public void setAdministrativeSanctionAmount(
			BigDecimal administrativeSanctionAmount) {
		this.administrativeSanctionAmount = administrativeSanctionAmount;
	}

	public Integer getRevisionNo() {
		return revisionNo;
	}

	public void setRevisionNo(Integer revisionNo) {
		this.revisionNo = revisionNo;
	}

	public String getAsAuthorityName() {
		return asAuthorityName;
	}

	public void setAsAuthorityName(String asAuthorityName) {
		this.asAuthorityName = asAuthorityName;
	}

	public BigDecimal getContingencyAmount() {
		return contingencyAmount;
	}

	public void setContingencyAmount(BigDecimal contingencyAmount) {
		this.contingencyAmount = contingencyAmount;
	}

	public Long getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(Long requestStatus) {
		this.requestStatus = requestStatus;
	}
	public BigDecimal getProposeddistance() {
		return proposeddistance;
	}

	public void setProposeddistance(BigDecimal proposeddistance) {
		this.proposeddistance = proposeddistance;
	}

	
	
	
	
	
	
}
