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

//@Audited
@Entity
@Table(name = "WORK_LOGGING")
public class WorkLogging extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;
	
	     @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "log_id")
	    private Long logId;

	
	@Column(name = "ID")
	private Long id;

	@Column(name = "WORK_NAME")
	private String workName;
	
	@Column(name = "WORK_REQUISITION_NO")
	private String workRequisitionNo;

	@JoinColumn(name = "WORK_TYPE_ID", referencedColumnName = "WORK_TYPE_ID")
	@ManyToOne
	// @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private WorkType workTypeId;

	@JoinColumn(name = "WORK_SUB_TYPE_ID", referencedColumnName = "WORK_SUB_TYPE_ID")
	@ManyToOne
	// @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private WorkSubType workSubTypeId;

	@JoinColumn(name = "LINE_DEPARTMENT_ID", referencedColumnName = "LINE_DEPARTMENT_ID")
	@ManyToOne
	// @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private LineDepartment lineDepartmentId;

	@Column(name = "ESTIMATED_COST")
	private BigDecimal estimatedCost;

	// this is administrative Cost
	@Column(name = "TOTAL_COST")
	private BigDecimal totalCost;

	@JoinColumn(name = "ACCOUNT_HEAD", referencedColumnName = "ID")
	@OneToOne
	// @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private AccountHead accountHead;

	@Column(name = "LETTER_NO")
	private String letterNo;

	@Column(name = "LETTER_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date letterDate;

	@JoinColumn(name = "AGENCY_TYPE_ID", referencedColumnName = "AGENCY_TYPE_ID")
	@ManyToOne
	// @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private AgencyType agencyTypeId;

	@Column(name = "AGENCY_NAME")
	private String agencyName;

	@JoinColumn(name = "CONTRACTOR_ID", referencedColumnName = "ID")
	@ManyToOne
	// @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Contractor contractor;

	@Column(name = "TOTAL_EXPENDITURE_TILL_31_MARCH_2018")
	private BigDecimal totalExpenditureTill31March2018;
	
	
	@Column(name = "total_expenditure_on_contingency_till_31_march_2018")
	private BigDecimal totalExpenditureOnContingencyTill31March2018;

	@JoinColumn(name = "PHYSICAL_STAGE_AS_ON_31_MARCH_2018", referencedColumnName = "PHYSICAL_STAGE_ID")
	@ManyToOne
	// @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private PhysicalStageType physicalStageType;

	@Column(name = "tentative_completion_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date tentativeCompletionDate;

	@Column(name = "total_amount_recieved_till_31march2018")
	private BigDecimal totalAmountRecievedTill31march2018;

	@JoinColumn(name = "AGREEMENT_COPY", referencedColumnName = "ID")
	@OneToOne
	// @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private DocumentUpload agreementCopy;

	@Column(name = "AGREEMENT_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date agreementDate;

	@Column(name = "AGREEMENT_NUMBER")
	private String agreementNumber;


	@JoinColumn(name = "EXECUTIVE_ENGINEER_OFFICE_ID", referencedColumnName = "id")
	@ManyToOne
	private Office executiveEngineerOffice;

	@JoinColumn(name = "SUPERINTENDING_ENGINEER_OFFICE_ID", referencedColumnName = "id")
	@ManyToOne
	private Office superintendingEngineerOffice;

	@JoinColumn(name = "CHIEF_ENGINEER_OFFICE_ID", referencedColumnName = "id")
	@ManyToOne
	private Office chiefEngineerOffice;

	@JoinColumn(name = "ASSISTANT_ENGINEER_ID", referencedColumnName = "ID")
	@ManyToOne
	private Users assistantEngineer;
	
	@JoinColumn(name = "SUB_DIVISIONAL_OFFICER_ID", referencedColumnName = "ID")
	@ManyToOne
	private Users subDivisionalOfficer;

	@JoinColumn(name = "SUB_ENGINEER_ID", referencedColumnName = "ID")
	@ManyToOne
	private Users subEngineer;

	@JoinColumn(name = "DISTRICT_ID", referencedColumnName = "ID")
	@ManyToOne
	// @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private District district;

	@JoinColumn(name = "BLOCK_ID", referencedColumnName = "ID")
	@ManyToOne
	// @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Block block;

	@JoinColumn(name = "GRAM_PANCHAYAT_ID", referencedColumnName = "ID")
	@ManyToOne
	// @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private GramPanchayat gramPanchayat;

	@JoinColumn(name = "VILLAGE_ID", referencedColumnName = "ID")
	@ManyToOne
	// @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Village village;

	@Column(name = "WORK_LOCATION_LATITUDE")
	private String workLocationLatitude;

	@Column(name = "WORK_LOCATION_LONGITUDE")
	private String workLocationLongitude;

	@Column(name = "LOCATION_GEOMETERY")
	private String locationGeometery;

	@Column(name = "LOCATION_ADDRESS")
	private String locationAddress;

	@Column(name = "CLIENT_IP")
	private String clientIp;

	/*
	 * @JoinColumn(name = "LATEST_TECHNICAL_SANCTION_COPY", referencedColumnName
	 * = "DOCUMENT_ID")
	 * 
	 * @ManyToOne private DocumentUploadDetails latestTechnicalSanctionCopy;
	 * 
	 * @JoinColumn(name = "LATEST_ADMINISTRATIVE_SANCTION_COPY",
	 * referencedColumnName = "DOCUMENT_ID")
	 * 
	 * @ManyToOne private DocumentUploadDetails
	 * latestAdministrativeSanctionCopy;
	 * 
	 * @JoinColumn(name = "LATEST_DRAWING_COPY", referencedColumnName =
	 * "DOCUMENT_ID")
	 * 
	 * @ManyToOne private DocumentUploadDetails latestDrawingCopy;
	 */

	@Column(name = "APPROVED_BY")
	private String approvedBy;

	@JoinColumn(name = "WORK_STATUS_ID", referencedColumnName = "ID")
	@ManyToOne
	// @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private WorkStatus workStatusId;

	private String status;

	@Column(name = "TENDERED_RATE_SIGN")
	private String tenderedRateSign;

	@Column(name = "TENDERED_RATE_PER")
	private BigDecimal tenderedRatePer;

	@Column(name = "PAC_AMOUNT")
	private BigDecimal pacAmount;

	@Column(name = "TENDER_COST")
	private BigDecimal tenderCost;

	@JoinColumn(name = "WORK_REQUEST_STATUS_ID", referencedColumnName = "ID")
	@OneToOne
	// @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private RequestStatus workRequestStatusId;

	@Column(name = "IS_LEGACY")
	private Short isLegacy;

	@JoinColumn(name = "LINE_DEPARTMENT_FILE", referencedColumnName = "ID")
	@OneToOne
	// @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private DocumentUpload lineDepartmentFile;

	private String remarks;
	
	@Column(name = "DISTANCE")
	private BigDecimal distance;
	
	@Column(name = "completeddistance")
	private BigDecimal completeddistance;
	
	@Column(name = "financial_year")
	private String financialYear;
	
	@Column(name = "legacy_revise")
	private Short isLegacyRevise;
	
	@Column(name = "technical_id")
	private Long technicalId;
	
	@Column(name = "administrative_id")
	private Long administrativeId;
	
	@Column(name = "row_created_date")
	private Date rowCreatedDate;
	
	/* @OneToOne(mappedBy="work")
    private AdministrationSanction administrationSanction;*/
	
	public Date getRowCreatedDate() {
		return rowCreatedDate;
	}

	public void setRowCreatedDate(Date rowCreatedDate) {
		this.rowCreatedDate = rowCreatedDate;
	}

	public Long getTechnicalId() {
		return technicalId;
	}

	public void setTechnicalId(Long technicalId) {
		this.technicalId = technicalId;
	}

	public Long getAdministrativeId() {
		return administrativeId;
	}

	public void setAdministrativeId(Long administrativeId) {
		this.administrativeId = administrativeId;
	}

	public Short getIsLegacyRevise() {
		return isLegacyRevise;
	}

	public void setIsLegacyRevise(Short isLegacyRevise) {
		this.isLegacyRevise = isLegacyRevise;
	}

	@Column(name = "PROBABLE_AMOUNT_OF_WORK")
	private BigDecimal probableAmountOfWork;
	
	

	public WorkLogging() {
		super();
	}

	

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(BigDecimal estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public BigDecimal getTotalExpenditureTill31March2018() {
		return totalExpenditureTill31March2018;
	}

	public void setTotalExpenditureTill31March2018(
			BigDecimal totalExpenditureTill31March2018) {
		this.totalExpenditureTill31March2018 = totalExpenditureTill31March2018;
	}

	public PhysicalStageType getPhysicalStageType() {
		return physicalStageType;
	}

	public void setPhysicalStageType(PhysicalStageType physicalStageType) {
		this.physicalStageType = physicalStageType;
	}

	public GramPanchayat getGramPanchayat() {
		return gramPanchayat;
	}

	public void setGramPanchayat(GramPanchayat gramPanchayat) {
		this.gramPanchayat = gramPanchayat;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	public WorkStatus getWorkStatusId() {
		return workStatusId;
	}

	public void setWorkStatusId(WorkStatus workStatusId) {
		this.workStatusId = workStatusId;
	}

	public String getWorkLocationLatitude() {
		return workLocationLatitude;
	}

	public void setWorkLocationLatitude(String workLocationLatitude) {
		this.workLocationLatitude = workLocationLatitude;
	}

	public String getWorkLocationLongitude() {
		return workLocationLongitude;
	}

	public void setWorkLocationLongitude(String workLocationLongitude) {
		this.workLocationLongitude = workLocationLongitude;
	}

	public String getLocationGeometery() {
		return locationGeometery;
	}

	public void setLocationGeometery(String locationGeometery) {
		this.locationGeometery = locationGeometery;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public WorkType getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(WorkType workTypeId) {
		this.workTypeId = workTypeId;
	}

	public WorkSubType getWorkSubTypeId() {
		return workSubTypeId;
	}

	public void setWorkSubTypeId(WorkSubType workSubTypeId) {
		this.workSubTypeId = workSubTypeId;
	}

	public LineDepartment getLineDepartmentId() {
		return lineDepartmentId;
	}

	public void setLineDepartmentId(LineDepartment lineDepartmentId) {
		this.lineDepartmentId = lineDepartmentId;
	}

	public AccountHead getAccountHead() {
		return accountHead;
	}

	public void setAccountHead(AccountHead accountHead) {
		this.accountHead = accountHead;
	}

	public String getLetterNo() {
		return letterNo;
	}

	public void setLetterNo(String letterNo) {
		this.letterNo = letterNo;
	}

	public Date getLetterDate() {
		return letterDate;
	}

	public void setLetterDate(Date letterDate) {
		this.letterDate = letterDate;
	}

	public AgencyType getAgencyTypeId() {
		return agencyTypeId;
	}

	public void setAgencyTypeId(AgencyType agencyTypeId) {
		this.agencyTypeId = agencyTypeId;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public Office getExecutiveEngineerOffice() {
		return executiveEngineerOffice;
	}

	public void setExecutiveEngineerOffice(Office executiveEngineerOffice) {
		this.executiveEngineerOffice = executiveEngineerOffice;
	}

	public Users getAssistantEngineer() {
		return assistantEngineer;
	}

	public void setAssistantEngineer(Users assistantEngineer) {
		this.assistantEngineer = assistantEngineer;
	}

	public Users getSubEngineer() {
		return subEngineer;
	}

	public void setSubEngineer(Users subEngineer) {
		this.subEngineer = subEngineer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTentativeCompletionDate() {
		return tentativeCompletionDate;
	}

	public void setTentativeCompletionDate(Date tentativeCompletionDate) {
		this.tentativeCompletionDate = tentativeCompletionDate;
	}

	public BigDecimal getTotalAmountRecievedTill31march2018() {
		return totalAmountRecievedTill31march2018;
	}

	public void setTotalAmountRecievedTill31march2018(
			BigDecimal totalAmountRecievedTill31march2018) {
		this.totalAmountRecievedTill31march2018 = totalAmountRecievedTill31march2018;
	}

	public DocumentUpload getAgreementCopy() {
		return agreementCopy;
	}

	public void setAgreementCopy(DocumentUpload agreementCopy) {
		this.agreementCopy = agreementCopy;
	}

	public Contractor getContractor() {
		return contractor;
	}

	public void setContractor(Contractor contractor) {
		this.contractor = contractor;
	}

	public Date getAgreementDate() {
		return agreementDate;
	}

	public void setAgreementDate(Date agreementDate) {
		this.agreementDate = agreementDate;
	}

	public String getLocationAddress() {
		return locationAddress;
	}

	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	public String getTenderedRateSign() {
		return tenderedRateSign;
	}

	public void setTenderedRateSign(String tenderedRateSign) {
		this.tenderedRateSign = tenderedRateSign;
	}

	public BigDecimal getTenderedRatePer() {
		return tenderedRatePer;
	}

	public void setTenderedRatePer(BigDecimal tenderedRatePer) {
		this.tenderedRatePer = tenderedRatePer;
	}

	public Office getSuperintendingEngineerOffice() {
		return superintendingEngineerOffice;
	}

	public void setSuperintendingEngineerOffice(
			Office superintendingEngineerOffice) {
		this.superintendingEngineerOffice = superintendingEngineerOffice;
	}

	public Office getChiefEngineerOffice() {
		return chiefEngineerOffice;
	}

	public void setChiefEngineerOffice(Office chiefEngineerOffice) {
		this.chiefEngineerOffice = chiefEngineerOffice;
	}

	public String getAgreementNumber() {
		return agreementNumber;
	}

	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
	}

	public BigDecimal getPacAmount() {
		return pacAmount;
	}

	public void setPacAmount(BigDecimal pacAmount) {
		this.pacAmount = pacAmount;
	}

	public BigDecimal getTenderCost() {
		return tenderCost;
	}

	public void setTenderCost(BigDecimal tenderCost) {
		this.tenderCost = tenderCost;
	}

	public RequestStatus getWorkRequestStatusId() {
		return workRequestStatusId;
	}

	public void setWorkRequestStatusId(RequestStatus workRequestStatusId) {
		this.workRequestStatusId = workRequestStatusId;
	}

	public Short getIsLegacy() {
		return isLegacy;
	}

	public void setIsLegacy(Short isLegacy) {
		this.isLegacy = isLegacy;
	}

	public DocumentUpload getLineDepartmentFile() {
		return lineDepartmentFile;
	}

	public void setLineDepartmentFile(DocumentUpload lineDepartmentFile) {
		this.lineDepartmentFile = lineDepartmentFile;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getWorkRequisitionNo() {
		return workRequisitionNo;
	}

	public void setWorkRequisitionNo(String workRequisitionNo) {
		this.workRequisitionNo = workRequisitionNo;
	}

	public BigDecimal getTotalExpenditureOnContingencyTill31March2018() {
		return totalExpenditureOnContingencyTill31March2018;
	}

	public void setTotalExpenditureOnContingencyTill31March2018(
			BigDecimal totalExpenditureOnContingencyTill31March2018) {
		this.totalExpenditureOnContingencyTill31March2018 = totalExpenditureOnContingencyTill31March2018;
	}

	public BigDecimal getDistance() {
		return distance;
	}

	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}

	public BigDecimal getProbableAmountOfWork() {
		return probableAmountOfWork;
	}

	public void setProbableAmountOfWork(BigDecimal probableAmountOfWork) {
		this.probableAmountOfWork = probableAmountOfWork;
	}
	public BigDecimal getCompleteddistance() {
		return completeddistance;
	}

	public void setCompleteddistance(BigDecimal completeddistance) {
		this.completeddistance = completeddistance;
	}

	/*public AdministrationSanction getAdministrationSanction() {
		return administrationSanction;
	}

	public void setAdministrationSanction(
			AdministrationSanction administrationSanction) {
		this.administrationSanction = administrationSanction;
	}*/

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public Users getSubDivisionalOfficer() {
		return subDivisionalOfficer;
	}

	public void setSubDivisionalOfficer(Users subDivisionalOfficer) {
		this.subDivisionalOfficer = subDivisionalOfficer;
	}
	
	
	
}