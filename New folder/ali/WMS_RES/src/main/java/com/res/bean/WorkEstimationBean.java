package com.res.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.web.multipart.MultipartFile;

import com.res.entity.WorkEstimationStatus;
import com.res.entity.WorkType;

public class WorkEstimationBean {
	
	private WorkEstimationStatus status1;
	private Long workEstimationId;

	private String workName;
	private String executionAgency;
	private String workType;
	private Long workTypeId;
	private String workSubType;
	private String estimatedAmount;
	private String status;
	private Long workId;
	private Long statusId;
	private List<WorkTemplateBean> workTemplateItems;
	private String totalLabourComponent;
	private String totalAmountMaterial;
	private String totalAmountMachinery;
	private String totalAmountLooseningOfSoil;
	private String totalAmountExcavation;
	private String grandTotal;
	private String estimationType;
	private String loggedInUserRole;
	private String comments;
	private String estimationStatus;
	private Long estimationStatusId;
	private Long estimationId;
	private Long parentId;
	private String expectedTenderedRatePer;
	private String expectedTenderedAmt;
	private String overheadChargesPer;
	private String overheadChargesAmt;
	private String labourWelfareComponentPer;
	private String labourWelfareComponentAmt;
	private String applicableGstPer;
	private String applicableGstAmt;
	private String workChargeContingencyPer;
	private String administrativeExpenditurePer;
	private String workChargeContingencyAmt;
	private String administrativeExpenditureAmt;
	private String othersCharges;

	private String subEngComments;
	private String sdoComments;
	private String aeComments;
	private String eeComments;
	private String seComments;
	private String ceComments;

	private String subEngFwdDate;
	private String sdoFwdDate;
	private String aeFwdDate;
	private String eeFwdDate;
	private String seFwdDate;
	private String ceFwdDate;

	private String estimationSubmissionDate;
	private String estimationApprovedBy;
	
	private String workRequisitionId;
	private String workRequisitionNo;
	private WorkType  workTypeId1;
	
	private Long revert;
	
	//Rakesh
	private Integer revisionNo;
	private String competentAuthName;
	private String competentAuthDesig;
	
	private String revisedLetterNo;
	private String letterNoDate;
	
	private Boolean hasNonSorItems;
	
	private Boolean tenPercentCheck;
	
	private Long technicalSanctionStatusId;
	
	
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

	public Integer getRevisionNo() {
		return revisionNo;
	}

	public void setRevisionNo(Integer revisionNo) {
		this.revisionNo = revisionNo;
	}

	public WorkTypeBean getWorkTypeBean() {
		return workTypeBean;
	}

	public void setWorkTypeBean(WorkTypeBean workTypeBean) {
		this.workTypeBean = workTypeBean;
	}

	public String getWorkTypeName() {
		return workTypeName;
	}

	public void setWorkTypeName(String workTypeName) {
		this.workTypeName = workTypeName;
	}

	public Long getWorkSubTypeId() {
		return workSubTypeId;
	}

	public void setWorkSubTypeId(Long workSubTypeId) {
		this.workSubTypeId = workSubTypeId;
	}

	public WorkSubTypeBean getWorkSubTypeBean() {
		return workSubTypeBean;
	}

	public void setWorkSubTypeBean(WorkSubTypeBean workSubTypeBean) {
		this.workSubTypeBean = workSubTypeBean;
	}

	public String getWorkSubTypeName() {
		return workSubTypeName;
	}

	public void setWorkSubTypeName(String workSubTypeName) {
		this.workSubTypeName = workSubTypeName;
	}

	public Long getLineDepartmentId() {
		return lineDepartmentId;
	}

	public void setLineDepartmentId(Long lineDepartmentId) {
		this.lineDepartmentId = lineDepartmentId;
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

	public Long getAgencyTypeId() {
		return agencyTypeId;
	}

	public void setAgencyTypeId(Long agencyTypeId) {
		this.agencyTypeId = agencyTypeId;
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

	public void setTotalExpenditureTill31March2018(BigDecimal totalExpenditureTill31March2018) {
		this.totalExpenditureTill31March2018 = totalExpenditureTill31March2018;
	}

	public String getTotalExpenditureTill31March2018String() {
		return totalExpenditureTill31March2018String;
	}

	public void setTotalExpenditureTill31March2018String(String totalExpenditureTill31March2018String) {
		this.totalExpenditureTill31March2018String = totalExpenditureTill31March2018String;
	}

	public Long getPhysicalStageId() {
		return physicalStageId;
	}

	public void setPhysicalStageId(Long physicalStageId) {
		this.physicalStageId = physicalStageId;
	}

	public String getPhysicalStageType() {
		return physicalStageType;
	}

	public void setPhysicalStageType(String physicalStageType) {
		this.physicalStageType = physicalStageType;
	}

	public PhysicalStageTypeBean getPhysicalStageTypeBean() {
		return physicalStageTypeBean;
	}

	public void setPhysicalStageTypeBean(PhysicalStageTypeBean physicalStageTypeBean) {
		this.physicalStageTypeBean = physicalStageTypeBean;
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

	public String getAgreementNumber() {
		return agreementNumber;
	}

	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
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

	public void setExecutiveEngineerOfficeName(String executiveEngineerOfficeName) {
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

	public String getAssistantEngineerName() {
		return assistantEngineerName;
	}

	public void setAssistantEngineerName(String assistantEngineerName) {
		this.assistantEngineerName = assistantEngineerName;
	}

	public UserBean getAssistantEngineer() {
		return assistantEngineer;
	}

	public void setAssistantEngineer(UserBean assistantEngineer) {
		this.assistantEngineer = assistantEngineer;
	}

	public Long getSubEngineerId() {
		return subEngineerId;
	}

	public void setSubEngineerId(Long subEngineerId) {
		this.subEngineerId = subEngineerId;
	}

	public String getSubEngineerName() {
		return subEngineerName;
	}

	public void setSubEngineerName(String subEngineerName) {
		this.subEngineerName = subEngineerName;
	}

	public UserBean getSubEngineer() {
		return subEngineer;
	}

	public void setSubEngineer(UserBean subEngineer) {
		this.subEngineer = subEngineer;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public DistrictBean getDistrict() {
		return district;
	}

	public void setDistrict(DistrictBean district) {
		this.district = district;
	}

	public Long getBlockId() {
		return blockId;
	}

	public void setBlockId(Long blockId) {
		this.blockId = blockId;
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

	public String getLocationAddress() {
		return locationAddress;
	}

	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getLineDepartmentName() {
		return lineDepartmentName;
	}

	public void setLineDepartmentName(String lineDepartmentName) {
		this.lineDepartmentName = lineDepartmentName;
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

	public Long getAdministrationSanctionTypeId() {
		return administrationSanctionTypeId;
	}

	public void setAdministrationSanctionTypeId(Long administrationSanctionTypeId) {
		this.administrationSanctionTypeId = administrationSanctionTypeId;
	}

	public String getAdministrationSanctionType() {
		return administrationSanctionType;
	}

	public void setAdministrationSanctionType(String administrationSanctionType) {
		this.administrationSanctionType = administrationSanctionType;
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

	public Long getIssuingAuthorityId() {
		return issuingAuthorityId;
	}

	public void setIssuingAuthorityId(Long issuingAuthorityId) {
		this.issuingAuthorityId = issuingAuthorityId;
	}

	public AdministrationSanctionBean getAdministrationSanctionBean() {
		return administrationSanctionBean;
	}

	public void setAdministrationSanctionBean(AdministrationSanctionBean administrationSanctionBean) {
		this.administrationSanctionBean = administrationSanctionBean;
	}

	public Long getTechnicalSanctionTypeId() {
		return technicalSanctionTypeId;
	}

	public void setTechnicalSanctionTypeId(Long technicalSanctionTypeId) {
		this.technicalSanctionTypeId = technicalSanctionTypeId;
	}

	public String getTechnicalSanctionType() {
		return technicalSanctionType;
	}

	public void setTechnicalSanctionType(String technicalSanctionType) {
		this.technicalSanctionType = technicalSanctionType;
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

	public TechnicalSanctionBean getTechnicalSanctionBean() {
		return technicalSanctionBean;
	}

	public void setTechnicalSanctionBean(TechnicalSanctionBean technicalSanctionBean) {
		this.technicalSanctionBean = technicalSanctionBean;
	}

	public Long getWorkStatusId() {
		return workStatusId;
	}

	public void setWorkStatusId(Long workStatusId) {
		this.workStatusId = workStatusId;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public WorkStatusBean getWorkStatusBean() {
		return workStatusBean;
	}

	public void setWorkStatusBean(WorkStatusBean workStatusBean) {
		this.workStatusBean = workStatusBean;
	}

	public String getTentativeCompletionDateString() {
		return tentativeCompletionDateString;
	}

	public void setTentativeCompletionDateString(String tentativeCompletionDateString) {
		this.tentativeCompletionDateString = tentativeCompletionDateString;
	}

	public BigDecimal getTotalAmountRecievedTill31March2018() {
		return totalAmountRecievedTill31March2018;
	}

	public void setTotalAmountRecievedTill31March2018(BigDecimal totalAmountRecievedTill31March2018) {
		this.totalAmountRecievedTill31March2018 = totalAmountRecievedTill31March2018;
	}

	public String getTotalAmountRecievedTill31March2018String() {
		return totalAmountRecievedTill31March2018String;
	}

	public void setTotalAmountRecievedTill31March2018String(String totalAmountRecievedTill31March2018String) {
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

	public MultipartFile getAdministrationSanctionFile() {
		return administrationSanctionFile;
	}

	public void setAdministrationSanctionFile(MultipartFile administrationSanctionFile) {
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

	public void setAdministrationSanctionFileId(Long administrationSanctionFileId) {
		this.administrationSanctionFileId = administrationSanctionFileId;
	}

	public Long getTechnicalSanctionFileId() {
		return technicalSanctionFileId;
	}

	public void setTechnicalSanctionFileId(Long technicalSanctionFileId) {
		this.technicalSanctionFileId = technicalSanctionFileId;
	}

	public Long getAgreementCopyId() {
		return agreementCopyId;
	}

	public void setAgreementCopyId(Long agreementCopyId) {
		this.agreementCopyId = agreementCopyId;
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

	public void setIssuingAuthorityBean(IssuingAuthorityBean issuingAuthorityBean) {
		this.issuingAuthorityBean = issuingAuthorityBean;
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

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getGramPanchayatName() {
		return gramPanchayatName;
	}

	public void setGramPanchayatName(String gramPanchayatName) {
		this.gramPanchayatName = gramPanchayatName;
	}

	public Long getWorkRequestStatusId() {
		return workRequestStatusId;
	}

	public void setWorkRequestStatusId(Long workRequestStatusId) {
		this.workRequestStatusId = workRequestStatusId;
	}

	public String getWorkRequestStatus() {
		return workRequestStatus;
	}

	public void setWorkRequestStatus(String workRequestStatus) {
		this.workRequestStatus = workRequestStatus;
	}

	public MultipartFile getLineDepartmentFile() {
		return lineDepartmentFile;
	}

	public void setLineDepartmentFile(MultipartFile lineDepartmentFile) {
		this.lineDepartmentFile = lineDepartmentFile;
	}

	public long getLineDepartmentFileId() {
		return lineDepartmentFileId;
	}

	public void setLineDepartmentFileId(long lineDepartmentFileId) {
		this.lineDepartmentFileId = lineDepartmentFileId;
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

	public void setTotalExpenditureOnContingencyTill31March2018(BigDecimal totalExpenditureOnContingencyTill31March2018) {
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

	public String getYantriName() {
		return yantriName;
	}

	public void setYantriName(String yantriName) {
		this.yantriName = yantriName;
	}

	private WorkTypeBean workTypeBean;
	
	private String workTypeName;
	
	private Long workSubTypeId;
	
	private WorkSubTypeBean workSubTypeBean;
	
	private String workSubTypeName;
	
	private Long lineDepartmentId;
	
	private LineDepartmentBean lineDepartmentBean;
	
	private BigDecimal estimatedCost;
	private BigDecimal estimatedCost1;
	
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
	private String  physicalStageType;
	private PhysicalStageTypeBean physicalStageTypeBean;
	
	private String agreementDateString;
	
	private Date agreementDate;
	
	private String agreementNumber;
	
	private Long executiveEngineerOfficeId;

	private String executiveEngineerOfficeName;
	
	private OfficeBean executiveEngineerOffice;
	
	private Long assistantEngineerId;
	
	private String assistantEngineerName;
	
	private UserBean assistantEngineer;
	
	private Long subEngineerId;
	
	private String subEngineerName;
	
	private UserBean subEngineer;
	
	private Long subDivisionOfficerId;

	private String subDivisionOfficerName;
	
	private UserBean subDivisionOfficer;
	
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
	
	private UserBean approvedByUser;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String createdDateString;
	
	private String modifiedBy;
	
	private Date modifiedDate;
	
	private String modifiedDateString;
	
	private UserBean userBean;
	
	private Long administrationSanctionTypeId;
	
	private String administrationSanctionType;
	
	private String administrationSanctionNo;
	
	private String administrationSanctionDate;
	
	private Long issuingAuthorityId;
	
	private AdministrationSanctionBean administrationSanctionBean;
	
	private Long technicalSanctionTypeId;
	
	private String technicalSanctionType;
	
	private String technicalSanctionNo;
	
	private String tsDispatchNumber;
	
	

	private String technicalSanctionDate;
	
	private TechnicalSanctionBean technicalSanctionBean;
	
;
	
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
	
	
	private String contractorTxt;
	
	private Long accountHeadId;

    private String accountHeadName;
    
    private String remarks;
    
    
    
    private String divisionAgency;
    
    private Integer generateId;
    
    private BigDecimal totalExpenditureOnContingencyTill31March2018;
    
    private BigDecimal contingencyAmount;
    
    private String asIssuingAuthorityName;
    
	private Short isLegacy;
	
	private String villageName;
	
	private BigDecimal distance;
	
	private String anukramNo;
	
	private String yantriName;

	public String getWorkRequisitionNo() {
		return workRequisitionNo;
	}

	public WorkType getWorkTypeId1() {
		return workTypeId1;
	}

	public void setWorkTypeId1(WorkType workTypeId1) {
		this.workTypeId1 = workTypeId1;
	}

	public void setWorkRequisitionNo(String workRequisitionNo) {
		this.workRequisitionNo = workRequisitionNo;
	}

	public String getWorkRequisitionId() {
		return workRequisitionId;
	}

	public void setWorkRequisitionId(String workRequisitionId) {
		this.workRequisitionId = workRequisitionId;
	}

	public String getEstimationSubmissionDate() {
		return estimationSubmissionDate;
	}

	public void setEstimationSubmissionDate(String estimationSubmissionDate) {
		this.estimationSubmissionDate = estimationSubmissionDate;
	}

	public String getSubEngComments() {
		return subEngComments;
	}

	public void setSubEngComments(String subEngComments) {
		this.subEngComments = subEngComments;
	}

	public String getAeComments() {
		return aeComments;
	}

	public void setAeComments(String aeComments) {
		this.aeComments = aeComments;
	}

	public String getSubEngFwdDate() {
		return subEngFwdDate;
	}

	public void setSubEngFwdDate(String subEngFwdDate) {
		this.subEngFwdDate = subEngFwdDate;
	}

	public String getAeFwdDate() {
		return aeFwdDate;
	}

	public void setAeFwdDate(String aeFwdDate) {
		this.aeFwdDate = aeFwdDate;
	}

	public String getEeFwdDate() {
		return eeFwdDate;
	}

	public void setEeFwdDate(String eeFwdDate) {
		this.eeFwdDate = eeFwdDate;
	}

	public String getSeFwdDate() {
		return seFwdDate;
	}

	public void setSeFwdDate(String seFwdDate) {
		this.seFwdDate = seFwdDate;
	}

	public String getCeFwdDate() {
		return ceFwdDate;
	}

	public void setCeFwdDate(String ceFwdDate) {
		this.ceFwdDate = ceFwdDate;
	}

	public String getEeComments() {
		return eeComments;
	}

	public void setEeComments(String eeComments) {
		this.eeComments = eeComments;
	}

	public String getSeComments() {
		return seComments;
	}

	public void setSeComments(String seComments) {
		this.seComments = seComments;
	}

	public String getCeComments() {
		return ceComments;
	}

	public void setCeComments(String ceComments) {
		this.ceComments = ceComments;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getWorkChargeContingencyPer() {
		return workChargeContingencyPer;
	}

	public void setWorkChargeContingencyPer(String workChargeContingencyPer) {
		this.workChargeContingencyPer = workChargeContingencyPer;
	}

	public String getAdministrativeExpenditurePer() {
		return administrativeExpenditurePer;
	}

	public void setAdministrativeExpenditurePer(
			String administrativeExpenditurePer) {
		this.administrativeExpenditurePer = administrativeExpenditurePer;
	}

	public String getWorkChargeContingencyAmt() {
		return workChargeContingencyAmt;
	}

	public void setWorkChargeContingencyAmt(String workChargeContingencyAmt) {
		this.workChargeContingencyAmt = workChargeContingencyAmt;
	}

	public String getAdministrativeExpenditureAmt() {
		return administrativeExpenditureAmt;
	}

	public void setAdministrativeExpenditureAmt(
			String administrativeExpenditureAmt) {
		this.administrativeExpenditureAmt = administrativeExpenditureAmt;
	}

	public String getOthersCharges() {
		return othersCharges;
	}

	public void setOthersCharges(String othersCharges) {
		this.othersCharges = othersCharges;
	}

	public String getExpectedTenderedRatePer() {
		return expectedTenderedRatePer;
	}

	public void setExpectedTenderedRatePer(String expectedTenderedRatePer) {
		this.expectedTenderedRatePer = expectedTenderedRatePer;
	}

	public String getOverheadChargesPer() {
		return overheadChargesPer;
	}

	public void setOverheadChargesPer(String overheadChargesPer) {
		this.overheadChargesPer = overheadChargesPer;
	}

	public String getLabourWelfareComponentPer() {
		return labourWelfareComponentPer;
	}

	public void setLabourWelfareComponentPer(String labourWelfareComponentPer) {
		this.labourWelfareComponentPer = labourWelfareComponentPer;
	}

	public String getApplicableGstPer() {
		return applicableGstPer;
	}

	public void setApplicableGstPer(String applicableGstPer) {
		this.applicableGstPer = applicableGstPer;
	}

	public String getExpectedTenderedAmt() {
		return expectedTenderedAmt;
	}

	public void setExpectedTenderedAmt(String expectedTenderedAmt) {
		this.expectedTenderedAmt = expectedTenderedAmt;
	}

	public String getOverheadChargesAmt() {
		return overheadChargesAmt;
	}

	public void setOverheadChargesAmt(String overheadChargesAmt) {
		this.overheadChargesAmt = overheadChargesAmt;
	}

	public String getLabourWelfareComponentAmt() {
		return labourWelfareComponentAmt;
	}

	public void setLabourWelfareComponentAmt(String labourWelfareComponentAmt) {
		this.labourWelfareComponentAmt = labourWelfareComponentAmt;
	}

	public String getApplicableGstAmt() {
		return applicableGstAmt;
	}

	public void setApplicableGstAmt(String applicableGstAmt) {
		this.applicableGstAmt = applicableGstAmt;
	}

	public Long getEstimationId() {
		return estimationId;
	}

	public void setEstimationId(Long estimationId) {
		this.estimationId = estimationId;
	}

	public Long getEstimationStatusId() {
		return estimationStatusId;
	}

	public void setEstimationStatusId(Long estimationStatusId) {
		this.estimationStatusId = estimationStatusId;
	}

	public String getEstimationStatus() {
		return estimationStatus;
	}

	public void setEstimationStatus(String estimationStatus) {
		this.estimationStatus = estimationStatus;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getLoggedInUserRole() {
		return loggedInUserRole;
	}

	public void setLoggedInUserRole(String loggedInUserRole) {
		this.loggedInUserRole = loggedInUserRole;
	}

	public String getEstimationType() {
		return estimationType;
	}

	public void setEstimationType(String estimationType) {
		this.estimationType = estimationType;
	}

	public String getTotalAmountMaterial() {
		return totalAmountMaterial;
	}

	public void setTotalAmountMaterial(String totalAmountMaterial) {
		this.totalAmountMaterial = totalAmountMaterial;
	}

	public String getTotalAmountMachinery() {
		return totalAmountMachinery;
	}

	public void setTotalAmountMachinery(String totalAmountMachinery) {
		this.totalAmountMachinery = totalAmountMachinery;
	}

	public String getTotalAmountLooseningOfSoil() {
		return totalAmountLooseningOfSoil;
	}

	public void setTotalAmountLooseningOfSoil(String totalAmountLooseningOfSoil) {
		this.totalAmountLooseningOfSoil = totalAmountLooseningOfSoil;
	}

	public String getTotalAmountExcavation() {
		return totalAmountExcavation;
	}

	public void setTotalAmountExcavation(String totalAmountExcavation) {
		this.totalAmountExcavation = totalAmountExcavation;
	}

	public String getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(String grandTotal) {
		this.grandTotal = grandTotal;
	}

	public Long getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(Long workTypeId) {
		this.workTypeId = workTypeId;
	}

	public String getTotalLabourComponent() {
		return totalLabourComponent;
	}

	public void setTotalLabourComponent(String totalLabourComponent) {
		this.totalLabourComponent = totalLabourComponent;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	private int index;

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getExecutionAgency() {
		return executionAgency;
	}

	public void setExecutionAgency(String executionAgency) {
		this.executionAgency = executionAgency;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getWorkSubType() {
		return workSubType;
	}

	public void setWorkSubType(String workSubType) {
		this.workSubType = workSubType;
	}

	public String getEstimatedAmount() {
		return estimatedAmount;
	}

	public void setEstimatedAmount(String estimatedAmount) {
		this.estimatedAmount = estimatedAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<WorkTemplateBean> getWorkTemplateItems() {
		return workTemplateItems;
	}

	public void setWorkTemplateItems(List<WorkTemplateBean> workTemplateItems) {
		this.workTemplateItems = workTemplateItems;
	}

	
	public WorkEstimationStatus getStatus1() {
		return status1;
	}

	public void setStatus1(WorkEstimationStatus status1) {
		this.status1 = status1;
	}

	public String getTsDispatchNumber() {
		return tsDispatchNumber;
	}

	public void setTsDispatchNumber(String tsDispatchNumber) {
		this.tsDispatchNumber = tsDispatchNumber;
	}
	
	public Long getWorkEstimationId() {
		return workEstimationId;
	}

	public void setWorkEstimationId(Long workEstimationId) {
		this.workEstimationId = workEstimationId;
	}

	public Long getTechnicalSanctionStatusId() {
		return technicalSanctionStatusId;
	}

	public void setTechnicalSanctionStatusId(Long technicalSanctionStatusId) {
		this.technicalSanctionStatusId = technicalSanctionStatusId;
	}

	public UserBean getApprovedByUser() {
		return approvedByUser;
	}

	public void setApprovedByUser(UserBean approvedByUser) {
		this.approvedByUser = approvedByUser;
	}
	public BigDecimal getEstimatedCost1() {
		return estimatedCost1;
	}

	public void setEstimatedCost1(BigDecimal estimatedCost1) {
		this.estimatedCost1 = estimatedCost1;
	}

	public String getEstimationApprovedBy() {
		return estimationApprovedBy;
	}

	public void setEstimationApprovedBy(String estimationApprovedBy) {
		this.estimationApprovedBy = estimationApprovedBy;
	}

	public String getRevisedLetterNo() {
		return revisedLetterNo;
	}

	public void setRevisedLetterNo(String revisedLetterNo) {
		this.revisedLetterNo = revisedLetterNo;
	}

	public String getLetterNoDate() {
		return letterNoDate;
	}

	public void setLetterNoDate(String letterNoDate) {
		this.letterNoDate = letterNoDate;
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

	public String getSdoComments() {
		return sdoComments;
	}

	public void setSdoComments(String sdoComments) {
		this.sdoComments = sdoComments;
	}

	public String getSdoFwdDate() {
		return sdoFwdDate;
	}

	public void setSdoFwdDate(String sdoFwdDate) {
		this.sdoFwdDate = sdoFwdDate;
	}

	public Boolean getHasNonSorItems() {
		return hasNonSorItems;
	}

	public void setHasNonSorItems(Boolean hasNonSorItems) {
		this.hasNonSorItems = hasNonSorItems;
	}

	public Long getRevert() {
		return revert;
	}

	public void setRevert(Long revert) {
		this.revert = revert;
	}

	public Boolean getTenPercentCheck() {
		return tenPercentCheck;
	}

	public void setTenPercentCheck(Boolean tenPercentCheck) {
		this.tenPercentCheck = tenPercentCheck;
	}

	
	

}
