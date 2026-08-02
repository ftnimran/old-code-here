package com.res.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

public class WorkLoggingBean {


	private OfficeBean chiefEngineerOffice;

	private String chiefEngineerOfficeName;

	private OfficeBean superintendingEngineerOffice;

	private String superintendingEngineerOfficeName;

	private Long workId;

	private Integer index;

	private String workName;

	private Long workTypeId;

	private WorkTypeBean workTypeBean;

	private String workTypeName;

	private Long workSubTypeId;

	private WorkSubTypeBean workSubTypeBean;

	private String workSubTypeName;

	private Long lineDepartmentId;

	private LineDepartmentBean lineDepartmentBean;

	private BigDecimal estimatedCost;

	private String estimatedCostString;

	private BigDecimal totalCost;

	private String totalCostString;

	private String accountHead;

	private String letterNo;

	private String letterDateString;

	private Date letterDate;

	private Long agencyTypeId;

	private AgencyTypeBean agencyTypeBean;

	private String agencyName;

	private BigDecimal totalExpenditureTill31March2018;

	private String totalExpenditureTill31March2018String;

	private Long physicalStageId;

	private String physicalStageType;

	private PhysicalStageTypeBean physicalStageTypeBean;

	private String agreementDateString;

	private Date agreementDate;

	private String agreementNumber;

	private Long executiveEngineerOfficeId;

	private String executiveEngineerOfficeName;
	
	private String officeAddress;

	private OfficeBean executiveEngineerOffice;

	private Long assistantEngineerId;

	private String assistantEngineerName;

	private UserBean assistantEngineer;
	
	private Long subDivisionOfficerId;

	private String subDivisionOfficerName;

	private UserBean subDivisionOfficer;

	private Long subEngineerId;

	private String subEngineerName;

	private UserBean subEngineer;

	private Long districtId;

	private String districtName;

	private DistrictBean district;

	private Long blockId;

	private BlockBean block;

	private Long gramPanchayatId;

	private GramPanchayatBean gramPanchayatBean;

	private Long villageId;

	private VillageBean villageBean;

	private String workLocationLatitude;

	private String workLocationLongitude;

	private String locationGeometery;

	private String locationAddress;

	private String clientIp;

	private String lineDepartmentName;

	private String approvedBy;
	
	private UserBean approvedByUser;

	private String createdBy;

	private Date createdDate;

	private String createdDateString;

	private String modifiedBy;

	private Date modifiedDate;

	private String modifiedDateString;

	private UserBean userBean;

	private Long administrationSanctionTypeId;
	
	private Long technicalSanctionTypeId;	

	private String administrationSanctionNo;

	private String administrationSanctionDate;

	private Long issuingAuthorityId;

	private AdministrationSanctionBean administrationSanctionBean;	

	private String technicalSanctionType;
	
	private String administrationSanctionType;

	private String technicalSanctionNoOld;
	
	private String technicalSanctionNo;

	private String tsDispatchNumber;

	private String tsDispatchDate;
	
	private String technicalSanctionDate;	
	
	private Long financialYearId;

	private String financialYear;
	

	private TechnicalSanctionBean technicalSanctionBean;

	private String status;

	private Long workStatusId;

	private String workStatus;

	private WorkStatusBean workStatusBean;

	private String tentativeCompletionDateString;

	private BigDecimal totalAmountRecievedTill31March2018;

	private String totalAmountRecievedTill31March2018String;

	private Long technicalSanctionId;

	private Long administrationSanctionId;
	
	private MultipartFile agreementCopyFile;

	private MultipartFile administrationSanctionFile;

	private MultipartFile technicalSanctionFile;

	private MultipartFile latestDrawingCopyFile;

	private MultipartFile estimateFile;

	private Long administrationSanctionFileId;

	private Long technicalSanctionFileId;

	private Long agreementCopyId;

	private Long latestDrawingCopyId;

	private Long estimateFileId;

	private ContractorBean contractorBean;

	private Long contractorId;

	private String contractorName;

	private IssuingAuthorityBean issuingAuthorityBean;

	private String tenderedRateSign;

	private BigDecimal tenderedRatePer;

	private BigDecimal pacAmount;

	private BigDecimal tenderCost;

	private String tsAuthorityName;

	private Long tsIssuingAuthorityId;

	private String asAuthorityName;

	private String blockName;

	private String gramPanchayatName;

	private Long workRequestStatusId;

	private String workRequestStatus;

	private MultipartFile lineDepartmentFile;

	private long lineDepartmentFileId;

	private Long officeId;
	private Long parentOfficeId;
	private Long chiefOfficeId;
	private String loggedInUserRole;

	private String contractorTxt;

	private Long accountHeadId;

	private String accountHeadName;

	private String remarks;

	private String workRequisitionNo;
	
	private String workRequisitionDate;

	private String divisionAgency;

	private Integer generateId;

	private BigDecimal totalExpenditureOnContingencyTill31March2018;

	private BigDecimal contingencyAmount;

	private String asIssuingAuthorityName;

	private Short isLegacy;

	private String villageName;

	private BigDecimal distance;
	
	private BigDecimal completeddistance;

	private String anukramNo;

	private String yantriName;

	private BigDecimal probableAmountOfWork;

	private Long workEstimateId;

	private Long technicalSanctionStatusId;

	private BigDecimal technicalSanctionAmount;

	private String tsIssuingAuthorityName;

	private BigDecimal administrationSanctionAmount;

	private Long administrationSanctionStatusId;

	private BigDecimal grandTotal;

	private String technicalSanctionGeneratedOldNo;

	private String technicalSanctionLatestDate;

	private Boolean isEeOfficeName;
	private Boolean isCeOfficeName;
	private Boolean isSeOfficeName;
	private Boolean isDistrictName;
	private Boolean isGrampanchayatName;
	private Boolean isLinedepartmentName;

	private Long externalEstimationId;
	
	private WorkTenderBean workTenderBean;
	
	private WorkAgreementBean workAgreementBean;
	
	private WorkEstimationBean workEstimationBean;
	
	private BigDecimal proposeddistance;

	private String writtenOrderDate;
	
	private String executionAgency;

	// Budget Management	
	private BigDecimal  expenditureAmountTotal;	
	private BigDecimal  contingencyAmountTotal; 
	private BigDecimal  remainingAmountTotal;
	// Budget Management
	
	//cc
	private String workCompletedOn;	
	private String usedMBNo;	
	private String takenOverOn;	
	private String pageNo;	
	private String mbNo;	
	private String date;
	private String physicalCCIssuedOn;
	private String financialCCIssuedOn;	
	private Short physicalCCDispatchStatus;
	private Short financialCCDispatchStatus;	

	private String ccInitiatedOn;	
	private String ccInitiatedBy;	
	private String ccInspectionSubmittedOn;
	private UserBean ccInspectionInspectedBy;
	
	private Boolean addNewBillShow;
	private Boolean showIssueFinancialCC;
	private Short isLegacyRevise;
	
	

	//Tender
	private String tenderStatus;
	private Long tenderStatusId;
	private String bidderName;
	private Long tenderParentId;
	
	private Long tsParentId;
	
	private Long asParentId;
	
	private Long workLoggingId;
	
   private Long technicalId;
   
   private String rowCreatedDateString;
private Date rowCreatedDate;
	
 	public String getRowCreatedDateString() {
	return rowCreatedDateString;
}

public void setRowCreatedDateString(String rowCreatedDateString) {
	this.rowCreatedDateString = rowCreatedDateString;
}

public Date getRowCreatedDate() {
	return rowCreatedDate;
}

public void setRowCreatedDate(Date rowCreatedDate) {
	this.rowCreatedDate = rowCreatedDate;
}

	public Long getWorkLoggingId() {
	return workLoggingId;
}

public void setWorkLoggingId(Long workLoggingId) {
	this.workLoggingId = workLoggingId;
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

	private Long administrativeId;
	
	

	
	
	public Long getTsParentId() {
		return tsParentId;
	}

	public void setTsParentId(Long tsParentId) {
		this.tsParentId = tsParentId;
	}

	public Long getAsParentId() {
		return asParentId;
	}

	public void setAsParentId(Long asParentId) {
		this.asParentId = asParentId;
	}

	List<AdministrationSanctionBean> administrationSanctionBeanList;
	List<TechnicalSanctionBean> technicalSanctionBeanList;
	
	//Rakesh
	private Integer isSqmChecked;
	
	public Integer getIsSqmChecked() {
		return isSqmChecked;
	}

	public void setIsSqmChecked(Integer isSqmChecked) {
		this.isSqmChecked = isSqmChecked;
	}

	public Long getTenderParentId() {
		return tenderParentId;
	}

	public void setTenderParentId(Long tenderParentId) {
		this.tenderParentId = tenderParentId;
	}

	public String getWrittenOrderDate() {
		return writtenOrderDate;
	}

	public void setWrittenOrderDate(String writtenOrderDate) {
		this.writtenOrderDate = writtenOrderDate;
	}

	public BigDecimal getProposeddistance() {
		return proposeddistance;
	}

	public void setProposeddistance(BigDecimal proposeddistance) {
		this.proposeddistance = proposeddistance;
	}

	public String getLoggedInUserRole() {
		return loggedInUserRole;
	}

	public void setLoggedInUserRole(String loggedInUserRole) {
		this.loggedInUserRole = loggedInUserRole;
	}

	public String getGramPanchayatName() {
		return gramPanchayatName;
	}

	public String getYantriName() {
		return yantriName;
	}

	public void setYantriName(String yantriName) {
		this.yantriName = yantriName;
	}

	public void setGramPanchayatName(String gramPanchayatName) {
		this.gramPanchayatName = gramPanchayatName;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public WorkLoggingBean() {
		super();
	}

	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public WorkTypeBean getWorkTypeBean() {
		return workTypeBean;
	}

	public void setWorkTypeBean(WorkTypeBean workTypeBean) {
		this.workTypeBean = workTypeBean;
	}

	public WorkSubTypeBean getWorkSubTypeBean() {
		return workSubTypeBean;
	}

	public void setWorkSubTypeBean(WorkSubTypeBean workSubTypeBean) {
		this.workSubTypeBean = workSubTypeBean;
	}

	public LineDepartmentBean getLineDepartmentBean() {
		return lineDepartmentBean;
	}

	public void setLineDepartmentBean(LineDepartmentBean lineDepartmentBean) {
		this.lineDepartmentBean = lineDepartmentBean;
	}

	public BigDecimal getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(BigDecimal estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public String getEstimatedCostString() {
		return estimatedCostString;
	}

	public void setEstimatedCostString(String estimatedCostString) {
		this.estimatedCostString = estimatedCostString;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public String getTotalCostString() {
		return totalCostString;
	}

	public void setTotalCostString(String totalCostString) {
		this.totalCostString = totalCostString;
	}

	public String getAccountHead() {
		return accountHead;
	}

	public void setAccountHead(String accountHead) {
		this.accountHead = accountHead;
	}

	public String getLetterNo() {
		return letterNo;
	}

	public void setLetterNo(String letterNo) {
		this.letterNo = letterNo;
	}

	public String getLetterDateString() {
		return letterDateString;
	}

	public void setLetterDateString(String letterDateString) {
		this.letterDateString = letterDateString;
	}

	public Date getLetterDate() {
		return letterDate;
	}

	public void setLetterDate(Date letterDate) {
		this.letterDate = letterDate;
	}

	public AgencyTypeBean getAgencyTypeBean() {
		return agencyTypeBean;
	}

	public void setAgencyTypeBean(AgencyTypeBean agencyTypeBean) {
		this.agencyTypeBean = agencyTypeBean;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public BigDecimal getTotalExpenditureTill31March2018() {
		return totalExpenditureTill31March2018;
	}

	public void setTotalExpenditureTill31March2018(
			BigDecimal totalExpenditureTill31March2018) {
		this.totalExpenditureTill31March2018 = totalExpenditureTill31March2018;
	}

	public String getTotalExpenditureTill31March2018String() {
		return totalExpenditureTill31March2018String;
	}

	public void setTotalExpenditureTill31March2018String(
			String totalExpenditureTill31March2018String) {
		this.totalExpenditureTill31March2018String = totalExpenditureTill31March2018String;
	}

	public PhysicalStageTypeBean getPhysicalStageTypeBean() {
		return physicalStageTypeBean;
	}

	public void setPhysicalStageTypeBean(
			PhysicalStageTypeBean physicalStageTypeBean) {
		this.physicalStageTypeBean = physicalStageTypeBean;
	}

	public UserBean getAssistantEngineer() {
		return assistantEngineer;
	}

	public void setAssistantEngineer(UserBean assistantEngineer) {
		this.assistantEngineer = assistantEngineer;
	}

	public UserBean getSubEngineer() {
		return subEngineer;
	}

	public void setSubEngineer(UserBean subEngineer) {
		this.subEngineer = subEngineer;
	}

	public DistrictBean getDistrict() {
		return district;
	}

	public void setDistrict(DistrictBean district) {
		this.district = district;
	}

	public BlockBean getBlock() {
		return block;
	}

	public void setBlock(BlockBean block) {
		this.block = block;
	}

	public Long getGramPanchayatId() {
		return gramPanchayatId;
	}

	public void setGramPanchayatId(Long gramPanchayatId) {
		this.gramPanchayatId = gramPanchayatId;
	}

	public GramPanchayatBean getGramPanchayatBean() {
		return gramPanchayatBean;
	}

	public void setGramPanchayatBean(GramPanchayatBean gramPanchayatBean) {
		this.gramPanchayatBean = gramPanchayatBean;
	}

	public Long getVillageId() {
		return villageId;
	}

	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}

	public VillageBean getVillageBean() {
		return villageBean;
	}

	public void setVillageBean(VillageBean villageBean) {
		this.villageBean = villageBean;
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

	public Long getLatestDrawingCopyId() {
		return latestDrawingCopyId;
	}

	public void setLatestDrawingCopyId(Long latestDrawingCopyId) {
		this.latestDrawingCopyId = latestDrawingCopyId;
	}

	public Long getEstimateFileId() {
		return estimateFileId;
	}

	public void setEstimateFileId(Long estimateFileId) {
		this.estimateFileId = estimateFileId;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedDateString() {
		return createdDateString;
	}

	public void setCreatedDateString(String createdDateString) {
		this.createdDateString = createdDateString;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedDateString() {
		return modifiedDateString;
	}

	public void setModifiedDateString(String modifiedDateString) {
		this.modifiedDateString = modifiedDateString;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public AdministrationSanctionBean getAdministrationSanctionBean() {
		return administrationSanctionBean;
	}

	public void setAdministrationSanctionBean(
			AdministrationSanctionBean administrationSanctionBean) {
		this.administrationSanctionBean = administrationSanctionBean;
	}

	public TechnicalSanctionBean getTechnicalSanctionBean() {
		return technicalSanctionBean;
	}

	public void setTechnicalSanctionBean(
			TechnicalSanctionBean technicalSanctionBean) {
		this.technicalSanctionBean = technicalSanctionBean;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(Long workTypeId) {
		this.workTypeId = workTypeId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getBlockId() {
		return blockId;
	}

	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}

	public Long getWorkSubTypeId() {
		return workSubTypeId;
	}

	public void setWorkSubTypeId(Long workSubTypeId) {
		this.workSubTypeId = workSubTypeId;
	}

	public Long getLineDepartmentId() {
		return lineDepartmentId;
	}

	public void setLineDepartmentId(Long lineDepartmentId) {
		this.lineDepartmentId = lineDepartmentId;
	}

	public Long getAgencyTypeId() {
		return agencyTypeId;
	}

	public void setAgencyTypeId(Long agencyTypeId) {
		this.agencyTypeId = agencyTypeId;
	}

	public Long getPhysicalStageId() {
		return physicalStageId;
	}

	public void setPhysicalStageId(Long physicalStageId) {
		this.physicalStageId = physicalStageId;
	}

	public Long getExecutiveEngineerOfficeId() {
		return executiveEngineerOfficeId;
	}

	public void setExecutiveEngineerOfficeId(Long executiveEngineerOfficeId) {
		this.executiveEngineerOfficeId = executiveEngineerOfficeId;
	}

	public String getExecutiveEngineerOfficeName() {
		return executiveEngineerOfficeName;
	}

	public void setExecutiveEngineerOfficeName(
			String executiveEngineerOfficeName) {
		this.executiveEngineerOfficeName = executiveEngineerOfficeName;
	}

	public OfficeBean getExecutiveEngineerOffice() {
		return executiveEngineerOffice;
	}

	public void setExecutiveEngineerOffice(OfficeBean executiveEngineerOffice) {
		this.executiveEngineerOffice = executiveEngineerOffice;
	}

	public Long getAssistantEngineerId() {
		return assistantEngineerId;
	}

	public void setAssistantEngineerId(Long assistantEngineerId) {
		this.assistantEngineerId = assistantEngineerId;
	}

	public Long getSubEngineerId() {
		return subEngineerId;
	}

	public void setSubEngineerId(Long subEngineerId) {
		this.subEngineerId = subEngineerId;
	}

	public Long getAdministrationSanctionTypeId() {
		return administrationSanctionTypeId;
	}

	public void setAdministrationSanctionTypeId(
			Long administrationSanctionTypeId) {
		this.administrationSanctionTypeId = administrationSanctionTypeId;
	}

	public String getAdministrationSanctionNo() {
		return administrationSanctionNo;
	}

	public void setAdministrationSanctionNo(String administrationSanctionNo) {
		this.administrationSanctionNo = administrationSanctionNo;
	}

	public String getAdministrationSanctionDate() {
		return administrationSanctionDate;
	}

	public void setAdministrationSanctionDate(String administrationSanctionDate) {
		this.administrationSanctionDate = administrationSanctionDate;
	}

	public Long getTechnicalSanctionTypeId() {
		return technicalSanctionTypeId;
	}

	public void setTechnicalSanctionTypeId(Long technicalSanctionTypeId) {
		this.technicalSanctionTypeId = technicalSanctionTypeId;
	}

	public String getTechnicalSanctionNo() {
		return technicalSanctionNo;
	}

	public void setTechnicalSanctionNo(String technicalSanctionNo) {
		this.technicalSanctionNo = technicalSanctionNo;
	}

	public String getTechnicalSanctionDate() {
		return technicalSanctionDate;
	}

	public void setTechnicalSanctionDate(String technicalSanctionDate) {
		this.technicalSanctionDate = technicalSanctionDate;
	}

	public Long getIssuingAuthorityId() {
		return issuingAuthorityId;
	}

	public void setIssuingAuthorityId(Long issuingAuthorityId) {
		this.issuingAuthorityId = issuingAuthorityId;
	}

	public WorkStatusBean getWorkStatusBean() {
		return workStatusBean;
	}

	public void setWorkStatusBean(WorkStatusBean workStatusBean) {
		this.workStatusBean = workStatusBean;
	}

	public Long getWorkStatusId() {
		return workStatusId;
	}

	public void setWorkStatusId(Long workStatusId) {
		this.workStatusId = workStatusId;
	}

	public String getTentativeCompletionDateString() {
		return tentativeCompletionDateString;
	}

	public void setTentativeCompletionDateString(
			String tentativeCompletionDateString) {
		this.tentativeCompletionDateString = tentativeCompletionDateString;
	}

	public BigDecimal getTotalAmountRecievedTill31March2018() {
		return totalAmountRecievedTill31March2018;
	}

	public void setTotalAmountRecievedTill31March2018(
			BigDecimal totalAmountRecievedTill31March2018) {
		this.totalAmountRecievedTill31March2018 = totalAmountRecievedTill31March2018;
	}

	public String getTotalAmountRecievedTill31March2018String() {
		return totalAmountRecievedTill31March2018String;
	}

	public void setTotalAmountRecievedTill31March2018String(
			String totalAmountRecievedTill31March2018String) {
		this.totalAmountRecievedTill31March2018String = totalAmountRecievedTill31March2018String;
	}

	public Long getTechnicalSanctionId() {
		return technicalSanctionId;
	}

	public void setTechnicalSanctionId(Long technicalSanctionId) {
		this.technicalSanctionId = technicalSanctionId;
	}

	public Long getAdministrationSanctionId() {
		return administrationSanctionId;
	}

	public void setAdministrationSanctionId(Long administrationSanctionId) {
		this.administrationSanctionId = administrationSanctionId;
	}

	public MultipartFile getAgreementCopyFile() {
		return agreementCopyFile;
	}

	public void setAgreementCopyFile(MultipartFile agreementCopyFile) {
		this.agreementCopyFile = agreementCopyFile;
	}

	public Long getAgreementCopyId() {
		return agreementCopyId;
	}

	public void setAgreementCopyId(Long agreementCopyId) {
		this.agreementCopyId = agreementCopyId;
	}

	public ContractorBean getContractorBean() {
		return contractorBean;
	}

	public void setContractorBean(ContractorBean contractorBean) {
		this.contractorBean = contractorBean;
	}

	public Long getContractorId() {
		return contractorId;
	}

	public void setContractorId(Long contractorId) {
		this.contractorId = contractorId;
	}

	public String getContractorName() {
		return contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	public IssuingAuthorityBean getIssuingAuthorityBean() {
		return issuingAuthorityBean;
	}

	public void setIssuingAuthorityBean(
			IssuingAuthorityBean issuingAuthorityBean) {
		this.issuingAuthorityBean = issuingAuthorityBean;
	}

	public String getAgreementDateString() {
		return agreementDateString;
	}

	public void setAgreementDateString(String agreementDateString) {
		this.agreementDateString = agreementDateString;
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

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public MultipartFile getAdministrationSanctionFile() {
		return administrationSanctionFile;
	}

	public void setAdministrationSanctionFile(
			MultipartFile administrationSanctionFile) {
		this.administrationSanctionFile = administrationSanctionFile;
	}

	public MultipartFile getTechnicalSanctionFile() {
		return technicalSanctionFile;
	}

	public void setTechnicalSanctionFile(MultipartFile technicalSanctionFile) {
		this.technicalSanctionFile = technicalSanctionFile;
	}

	public MultipartFile getLatestDrawingCopyFile() {
		return latestDrawingCopyFile;
	}

	public void setLatestDrawingCopyFile(MultipartFile latestDrawingCopyFile) {
		this.latestDrawingCopyFile = latestDrawingCopyFile;
	}

	public MultipartFile getEstimateFile() {
		return estimateFile;
	}

	public void setEstimateFile(MultipartFile estimateFile) {
		this.estimateFile = estimateFile;
	}

	public Long getAdministrationSanctionFileId() {
		return administrationSanctionFileId;
	}

	public void setAdministrationSanctionFileId(
			Long administrationSanctionFileId) {
		this.administrationSanctionFileId = administrationSanctionFileId;
	}

	public Long getTechnicalSanctionFileId() {
		return technicalSanctionFileId;
	}

	public void setTechnicalSanctionFileId(Long technicalSanctionFileId) {
		this.technicalSanctionFileId = technicalSanctionFileId;
	}

	public String getWorkTypeName() {
		return workTypeName;
	}

	public void setWorkTypeName(String workTypeName) {
		this.workTypeName = workTypeName;
	}

	public String getWorkSubTypeName() {
		return workSubTypeName;
	}

	public void setWorkSubTypeName(String workSubTypeName) {
		this.workSubTypeName = workSubTypeName;
	}

	public String getLineDepartmentName() {
		return lineDepartmentName;
	}

	public void setLineDepartmentName(String lineDepartmentName) {
		this.lineDepartmentName = lineDepartmentName;
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

	public String getTsAuthorityName() {
		return tsAuthorityName;
	}

	public void setTsAuthorityName(String tsAuthorityName) {
		this.tsAuthorityName = tsAuthorityName;
	}

	public Long getTsIssuingAuthorityId() {
		return tsIssuingAuthorityId;
	}

	public void setTsIssuingAuthorityId(Long tsIssuingAuthorityId) {
		this.tsIssuingAuthorityId = tsIssuingAuthorityId;
	}

	public String getAsAuthorityName() {
		return asAuthorityName;
	}

	public void setAsAuthorityName(String asAuthorityName) {
		this.asAuthorityName = asAuthorityName;
	}

	public Long getWorkRequestStatusId() {
		return workRequestStatusId;
	}

	public void setWorkRequestStatusId(Long workRequestStatusId) {
		this.workRequestStatusId = workRequestStatusId;
	}

	public MultipartFile getLineDepartmentFile() {
		return lineDepartmentFile;
	}

	public void setLineDepartmentFile(MultipartFile lineDepartmentFile) {
		this.lineDepartmentFile = lineDepartmentFile;
	}

	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}

	public Long getParentOfficeId() {
		return parentOfficeId;
	}

	public void setParentOfficeId(Long parentOfficeId) {
		this.parentOfficeId = parentOfficeId;
	}

	public Long getChiefOfficeId() {
		return chiefOfficeId;
	}

	public void setChiefOfficeId(Long chiefOfficeId) {
		this.chiefOfficeId = chiefOfficeId;
	}

	public long getLineDepartmentFileId() {
		return lineDepartmentFileId;
	}

	public void setLineDepartmentFileId(long lineDepartmentFileId) {
		this.lineDepartmentFileId = lineDepartmentFileId;
	}

	public String getContractorTxt() {
		return contractorTxt;
	}

	public void setContractorTxt(String contractorTxt) {
		this.contractorTxt = contractorTxt;
	}

	public Long getAccountHeadId() {
		return accountHeadId;
	}

	public void setAccountHeadId(Long accountHeadId) {
		this.accountHeadId = accountHeadId;
	}

	public String getAccountHeadName() {
		return accountHeadName;
	}

	public void setAccountHeadName(String accountHeadName) {
		this.accountHeadName = accountHeadName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getWorkRequestStatus() {
		return workRequestStatus;
	}

	public void setWorkRequestStatus(String workRequestStatus) {
		this.workRequestStatus = workRequestStatus;
	}

	public String getPhysicalStageType() {
		return physicalStageType;
	}

	public void setPhysicalStageType(String physicalStageType) {
		this.physicalStageType = physicalStageType;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getAssistantEngineerName() {
		return assistantEngineerName;
	}

	public void setAssistantEngineerName(String assistantEngineerName) {
		this.assistantEngineerName = assistantEngineerName;
	}

	public String getSubEngineerName() {
		return subEngineerName;
	}

	public void setSubEngineerName(String subEngineerName) {
		this.subEngineerName = subEngineerName;
	}

	public String getAdministrationSanctionType() {
		return administrationSanctionType;
	}

	public void setAdministrationSanctionType(String administrationSanctionType) {
		this.administrationSanctionType = administrationSanctionType;
	}

	public String getTechnicalSanctionType() {
		return technicalSanctionType;
	}

	public void setTechnicalSanctionType(String technicalSanctionType) {
		this.technicalSanctionType = technicalSanctionType;
	}

	public String getWorkRequisitionNo() {
		return workRequisitionNo;
	}

	public void setWorkRequisitionNo(String workRequisitionNo) {
		this.workRequisitionNo = workRequisitionNo;
	}

	public String getWorkRequisitionDate() {
		return workRequisitionDate;
	}

	public void setWorkRequisitionDate(String workRequisitionDate) {
		this.workRequisitionDate = workRequisitionDate;
	}

	public String getDivisionAgency() {
		return divisionAgency;
	}

	public void setDivisionAgency(String divisionAgency) {
		this.divisionAgency = divisionAgency;
	}

	public Integer getGenerateId() {
		return generateId;
	}

	public void setGenerateId(Integer generateId) {
		this.generateId = generateId;
	}

	public BigDecimal getTotalExpenditureOnContingencyTill31March2018() {
		return totalExpenditureOnContingencyTill31March2018;
	}

	public void setTotalExpenditureOnContingencyTill31March2018(
			BigDecimal totalExpenditureOnContingencyTill31March2018) {
		this.totalExpenditureOnContingencyTill31March2018 = totalExpenditureOnContingencyTill31March2018;
	}

	public BigDecimal getContingencyAmount() {
		return contingencyAmount;
	}

	public void setContingencyAmount(BigDecimal contingencyAmount) {
		this.contingencyAmount = contingencyAmount;
	}

	public String getAsIssuingAuthorityName() {
		return asIssuingAuthorityName;
	}

	public void setAsIssuingAuthorityName(String asIssuingAuthorityName) {
		this.asIssuingAuthorityName = asIssuingAuthorityName;
	}

	public Short getIsLegacy() {
		return isLegacy;
	}

	public void setIsLegacy(Short isLegacy) {
		this.isLegacy = isLegacy;
	}

	public OfficeBean getChiefEngineerOffice() {
		return chiefEngineerOffice;
	}

	public void setChiefEngineerOffice(OfficeBean chiefEngineerOffice) {
		this.chiefEngineerOffice = chiefEngineerOffice;
	}

	public String getChiefEngineerOfficeName() {
		return chiefEngineerOfficeName;
	}

	public void setChiefEngineerOfficeName(String chiefEngineerOfficeName) {
		this.chiefEngineerOfficeName = chiefEngineerOfficeName;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public BigDecimal getDistance() {
		return distance;
	}

	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}

	public String getAnukramNo() {
		return anukramNo;
	}

	public void setAnukramNo(String anukramNo) {
		this.anukramNo = anukramNo;
	}

	public Long getFinancialYearId() {
		return financialYearId;
	}

	public void setFinancialYearId(Long financialYearId) {
		this.financialYearId = financialYearId;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public BigDecimal getProbableAmountOfWork() {
		return probableAmountOfWork;
	}

	public void setProbableAmountOfWork(BigDecimal probableAmountOfWork) {
		this.probableAmountOfWork = probableAmountOfWork;
	}

	public Long getWorkEstimateId() {
		return workEstimateId;
	}

	public void setWorkEstimateId(Long workEstimateId) {
		this.workEstimateId = workEstimateId;
	}

	public Long getTechnicalSanctionStatusId() {
		return technicalSanctionStatusId;
	}

	public void setTechnicalSanctionStatusId(Long technicalSanctionStatusId) {
		this.technicalSanctionStatusId = technicalSanctionStatusId;
	}

	public BigDecimal getTechnicalSanctionAmount() {
		return technicalSanctionAmount;
	}

	public void setTechnicalSanctionAmount(BigDecimal technicalSanctionAmount) {
		this.technicalSanctionAmount = technicalSanctionAmount;
	}

	public String getTsIssuingAuthorityName() {
		return tsIssuingAuthorityName;
	}

	public void setTsIssuingAuthorityName(String tsIssuingAuthorityName) {
		this.tsIssuingAuthorityName = tsIssuingAuthorityName;
	}

	public BigDecimal getAdministrationSanctionAmount() {
		return administrationSanctionAmount;
	}

	public void setAdministrationSanctionAmount(
			BigDecimal administrationSanctionAmount) {
		this.administrationSanctionAmount = administrationSanctionAmount;
	}

	public Long getAdministrationSanctionStatusId() {
		return administrationSanctionStatusId;
	}

	public void setAdministrationSanctionStatusId(
			Long administrationSanctionStatusId) {
		this.administrationSanctionStatusId = administrationSanctionStatusId;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public String getTechnicalSanctionGeneratedOldNo() {
		return technicalSanctionGeneratedOldNo;
	}

	public void setTechnicalSanctionGeneratedOldNo(
			String technicalSanctionGeneratedOldNo) {
		this.technicalSanctionGeneratedOldNo = technicalSanctionGeneratedOldNo;
	}

	public String getTechnicalSanctionLatestDate() {
		return technicalSanctionLatestDate;
	}

	public void setTechnicalSanctionLatestDate(
			String technicalSanctionLatestDate) {
		this.technicalSanctionLatestDate = technicalSanctionLatestDate;
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

	public Long getExternalEstimationId() {
		return externalEstimationId;
	}

	public void setExternalEstimationId(Long externalEstimationId) {
		this.externalEstimationId = externalEstimationId;
	}

	public OfficeBean getSuperintendingEngineerOffice() {
		return superintendingEngineerOffice;
	}

	public void setSuperintendingEngineerOffice(
			OfficeBean superintendingEngineerOffice) {
		this.superintendingEngineerOffice = superintendingEngineerOffice;
	}

	public String getSuperintendingEngineerOfficeName() {
		return superintendingEngineerOfficeName;
	}

	public void setSuperintendingEngineerOfficeName(
			String superintendingEngineerOfficeName) {
		this.superintendingEngineerOfficeName = superintendingEngineerOfficeName;
	}

	public String getTsDispatchNumber() {
		return tsDispatchNumber;
	}

	public void setTsDispatchNumber(String tsDispatchNumber) {
		this.tsDispatchNumber = tsDispatchNumber;
	}

	public WorkEstimationBean getWorkEstimationBean() {
		return workEstimationBean;
	}

	public void setWorkEstimationBean(WorkEstimationBean workEstimationBean) {
		this.workEstimationBean = workEstimationBean;
	}


	public String getTechnicalSanctionNoOld() {
		return technicalSanctionNoOld;
	}

	public void setTechnicalSanctionNoOld(String technicalSanctionNoOld) {
		this.technicalSanctionNoOld = technicalSanctionNoOld;
	}

	public String getTsDispatchDate() {
		return tsDispatchDate;
	}

	public void setTsDispatchDate(String tsDispatchDate) {
		this.tsDispatchDate = tsDispatchDate;
	}

	public UserBean getApprovedByUser() {
		return approvedByUser;
	}

	public void setApprovedByUser(UserBean approvedByUser) {
		this.approvedByUser = approvedByUser;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public WorkAgreementBean getWorkAgreementBean() {
		return workAgreementBean;
	}

	public void setWorkAgreementBean(WorkAgreementBean workAgreementBean) {
		this.workAgreementBean = workAgreementBean;
	}

	public WorkTenderBean getWorkTenderBean() {
		return workTenderBean;
	}

	public void setWorkTenderBean(WorkTenderBean workTenderBean) {
		this.workTenderBean = workTenderBean;
	}
	public BigDecimal getCompleteddistance() {
		return completeddistance;
	}

	public void setCompleteddistance(BigDecimal completeddistance) {
		this.completeddistance = completeddistance;
	}

	public BigDecimal getExpenditureAmountTotal() {
		return expenditureAmountTotal;
	}

	public void setExpenditureAmountTotal(BigDecimal expenditureAmountTotal) {
		this.expenditureAmountTotal = expenditureAmountTotal;
	}

	public BigDecimal getContingencyAmountTotal() {
		return contingencyAmountTotal;
	}

	public void setContingencyAmountTotal(BigDecimal contingencyAmountTotal) {
		this.contingencyAmountTotal = contingencyAmountTotal;
	}

	public BigDecimal getRemainingAmountTotal() {
		return remainingAmountTotal;
	}

	public void setRemainingAmountTotal(BigDecimal remainingAmountTotal) {
		this.remainingAmountTotal = remainingAmountTotal;
	}

	public String getExecutionAgency() {
		return executionAgency;
	}

	public void setExecutionAgency(String executionAgency) {
		this.executionAgency = executionAgency;
	}

	public Short getPhysicalCCDispatchStatus() {
		return physicalCCDispatchStatus;
	}

	public void setPhysicalCCDispatchStatus(Short physicalCCDispatchStatus) {
		this.physicalCCDispatchStatus = physicalCCDispatchStatus;
	}

	public Short getFinancialCCDispatchStatus() {
		return financialCCDispatchStatus;
	}

	public void setFinancialCCDispatchStatus(Short financialCCDispatchStatus) {
		this.financialCCDispatchStatus = financialCCDispatchStatus;
	}

	public String getPhysicalCCIssuedOn() {
		return physicalCCIssuedOn;
	}

	public void setPhysicalCCIssuedOn(String physicalCCIssuedOn) {
		this.physicalCCIssuedOn = physicalCCIssuedOn;
	}

	public String getFinancialCCIssuedOn() {
		return financialCCIssuedOn;
	}

	public void setFinancialCCIssuedOn(String financialCCIssuedOn) {
		this.financialCCIssuedOn = financialCCIssuedOn;
	}

	public String getWorkCompletedOn() {
		return workCompletedOn;
	}

	public void setWorkCompletedOn(String workCompletedOn) {
		this.workCompletedOn = workCompletedOn;
	}

	public String getUsedMBNo() {
		return usedMBNo;
	}

	public void setUsedMBNo(String usedMBNo) {
		this.usedMBNo = usedMBNo;
	}

	public String getTakenOverOn() {
		return takenOverOn;
	}

	public void setTakenOverOn(String takenOverOn) {
		this.takenOverOn = takenOverOn;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getMbNo() {
		return mbNo;
	}

	public void setMbNo(String mbNo) {
		this.mbNo = mbNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

 
	public String getCcInitiatedOn() {
		return ccInitiatedOn;
	}

	public void setCcInitiatedOn(String ccInitiatedOn) {
		this.ccInitiatedOn = ccInitiatedOn;
	}

	public String getCcInitiatedBy() {
		return ccInitiatedBy;
	}

	public void setCcInitiatedBy(String ccInitiatedBy) {
		this.ccInitiatedBy = ccInitiatedBy;
	}

 
	public String getCcInspectionSubmittedOn() {
		return ccInspectionSubmittedOn;
	}

	public void setCcInspectionSubmittedOn(String ccInspectionSubmittedOn) {
		this.ccInspectionSubmittedOn = ccInspectionSubmittedOn;
	}

	public Boolean getAddNewBillShow() {
		return addNewBillShow;
	}

	public void setAddNewBillShow(Boolean addNewBillShow) {
		this.addNewBillShow = addNewBillShow;
	}

	public Boolean getShowIssueFinancialCC() {
		return showIssueFinancialCC;
	}

	public void setShowIssueFinancialCC(Boolean showIssueFinancialCC) {
		this.showIssueFinancialCC = showIssueFinancialCC;
	}

	public UserBean getCcInspectionInspectedBy() {
		return ccInspectionInspectedBy;
	}

	public void setCcInspectionInspectedBy(UserBean ccInspectionInspectedBy) {
		this.ccInspectionInspectedBy = ccInspectionInspectedBy;
	}

	
	


	public String getTenderStatus() {
		return tenderStatus;
	}

	public void setTenderStatus(String tenderStatus) {
		this.tenderStatus = tenderStatus;
	}

	public Long getTenderStatusId() {
		return tenderStatusId;
	}

	public void setTenderStatusId(Long tenderStatusId) {
		this.tenderStatusId = tenderStatusId;
	}

	public String getBidderName() {
		return bidderName;
	}

	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}

	public List<AdministrationSanctionBean> getAdministrationSanctionBeanList() {
		return administrationSanctionBeanList;
	}

	public void setAdministrationSanctionBeanList(
			List<AdministrationSanctionBean> administrationSanctionBeanList) {
		this.administrationSanctionBeanList = administrationSanctionBeanList;
	}

	public List<TechnicalSanctionBean> getTechnicalSanctionBeanList() {
		return technicalSanctionBeanList;
	}

	public void setTechnicalSanctionBeanList(
			List<TechnicalSanctionBean> technicalSanctionBeanList) {
		this.technicalSanctionBeanList = technicalSanctionBeanList;
	}
	
	public Short getIsLegacyRevise() {
		return isLegacyRevise;
	}

	public void setIsLegacyRevise(Short isLegacyRevise) {
		this.isLegacyRevise = isLegacyRevise;
	}

	public Long getSubDivisionOfficerId() {
		return subDivisionOfficerId;
	}

	public void setSubDivisionOfficerId(Long subDivisionOfficerId) {
		this.subDivisionOfficerId = subDivisionOfficerId;
	}

	public String getSubDivisionOfficerName() {
		return subDivisionOfficerName;
	}

	public void setSubDivisionOfficerName(String subDivisionOfficerName) {
		this.subDivisionOfficerName = subDivisionOfficerName;
	}

	public UserBean getSubDivisionOfficer() {
		return subDivisionOfficer;
	}

	public void setSubDivisionOfficer(UserBean subDivisionOfficer) {
		this.subDivisionOfficer = subDivisionOfficer;
	}
	


}
