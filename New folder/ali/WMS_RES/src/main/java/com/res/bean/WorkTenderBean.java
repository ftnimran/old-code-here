package com.res.bean;

import java.math.BigDecimal;
import java.util.List;

import com.res.entity.Contractor;
import com.res.entity.WorkType;

public class WorkTenderBean {

	private Long tenderId;
	private Long workId;
	private Long administrationSanctionId;

	private String tenderNumber;
	private String tenderOpeningDate;
	private BigDecimal amountOfContract;
	private String noOFparticipants;

	private Long contractorId;
	private String tenderedRate;

	private Contractor contractor;

	private String workName;
	private String executionAgency;
	private String bidderName;
	private BigDecimal tenderCost;
	private String status;
	private Long statusId;
	private String tenderStatus;
	private Long tenderStatusId;
	private WorkType workTypeId;

	private String loggedInUserRole;
	private int index;
	private Long agencyTypeId;
	private String workTypeNameE;
	private String lineDepartmentNameE;
	private String workSubTypeNameE;
	private String agencyTypeNameE;
	private String districtName;
	private String gramPanchayatName;
	private String villageName;

	private String contractorTxt;
	private String workRequisitionId;
	private String tsIssuingAuthorityName;
	private String tenderedRateSign;
	public String getTechnicalSanctionType() {
		return technicalSanctionType;
	}

	public void setTechnicalSanctionType(String technicalSanctionType) {
		this.technicalSanctionType = technicalSanctionType;
	}

	public String getAdministrationSanctionType() {
		return administrationSanctionType;
	}

	public void setAdministrationSanctionType(String administrationSanctionType) {
		this.administrationSanctionType = administrationSanctionType;
	}

	private String technicalSanctionType;

	private String administrationSanctionType;

	public String getTenderedRateSign() {
		return tenderedRateSign;
	}

	public void setTenderedRateSign(String tenderedRateSign) {
		this.tenderedRateSign = tenderedRateSign;
	}

	public String getTsIssuingAuthorityName() {
		return tsIssuingAuthorityName;
	}

	public void setTsIssuingAuthorityName(String tsIssuingAuthorityName) {
		this.tsIssuingAuthorityName = tsIssuingAuthorityName;
	}

	private List<ContractorDepositBean> contratorDepositsList;
	private String blockName;

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getAdministrationSanctionDate() {
		return administrationSanctionDate;
	}

	public void setAdministrationSanctionDate(String administrationSanctionDate) {
		this.administrationSanctionDate = administrationSanctionDate;
	}

	public BigDecimal getAdministrationSanctionAmount() {
		return administrationSanctionAmount;
	}

	public void setAdministrationSanctionAmount(BigDecimal administrationSanctionAmount) {
		this.administrationSanctionAmount = administrationSanctionAmount;
	}

	public BigDecimal getTechnicalSanctionAmount() {
		return technicalSanctionAmount;
	}

	public void setTechnicalSanctionAmount(BigDecimal technicalSanctionAmount) {
		this.technicalSanctionAmount = technicalSanctionAmount;
	}

	public String getTechnicalSanctionDate() {
		return technicalSanctionDate;
	}

	public void setTechnicalSanctionDate(String technicalSanctionDate) {
		this.technicalSanctionDate = technicalSanctionDate;
	}

	private String administrationSanctionDate;
	private BigDecimal administrationSanctionAmount;
	private BigDecimal technicalSanctionAmount;
	private String technicalSanctionDate;

	public Long getTenderId() {
		return tenderId;
	}

	public void setTenderId(Long tenderId) {
		this.tenderId = tenderId;
	}

	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	public Long getAdministrationSanctionId() {
		return administrationSanctionId;
	}

	public void setAdministrationSanctionId(Long administrationSanctionId) {
		this.administrationSanctionId = administrationSanctionId;
	}

	public String getTenderNumber() {
		return tenderNumber;
	}

	public void setTenderNumber(String tenderNumber) {
		this.tenderNumber = tenderNumber;
	}

	public String getTenderOpeningDate() {
		return tenderOpeningDate;
	}

	public void setTenderOpeningDate(String tenderOpeningDate) {
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

	public Long getContractorId() {
		return contractorId;
	}

	public void setContractorId(Long contractorId) {
		this.contractorId = contractorId;
	}

	public String getTenderedRate() {
		return tenderedRate;
	}

	public void setTenderedRate(String tenderedRate) {
		this.tenderedRate = tenderedRate;
	}

	public Contractor getContractor() {
		return contractor;
	}

	public void setContractor(Contractor contractor) {
		this.contractor = contractor;
	}

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

	public String getBidderName() {
		return bidderName;
	}

	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}

	public BigDecimal getTenderCost() {
		return tenderCost;
	}

	public void setTenderCost(BigDecimal tenderCost) {
		this.tenderCost = tenderCost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
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

	public WorkType getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(WorkType workTypeId) {
		this.workTypeId = workTypeId;
	}

	public String getLoggedInUserRole() {
		return loggedInUserRole;
	}

	public void setLoggedInUserRole(String loggedInUserRole) {
		this.loggedInUserRole = loggedInUserRole;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Long getAgencyTypeId() {
		return agencyTypeId;
	}

	public void setAgencyTypeId(Long agencyTypeId) {
		this.agencyTypeId = agencyTypeId;
	}

	public String getWorkTypeNameE() {
		return workTypeNameE;
	}

	public void setWorkTypeNameE(String workTypeNameE) {
		this.workTypeNameE = workTypeNameE;
	}

	public String getLineDepartmentNameE() {
		return lineDepartmentNameE;
	}

	public void setLineDepartmentNameE(String lineDepartmentNameE) {
		this.lineDepartmentNameE = lineDepartmentNameE;
	}

	public String getWorkSubTypeNameE() {
		return workSubTypeNameE;
	}

	public void setWorkSubTypeNameE(String workSubTypeNameE) {
		this.workSubTypeNameE = workSubTypeNameE;
	}

	public String getAgencyTypeNameE() {
		return agencyTypeNameE;
	}

	public void setAgencyTypeNameE(String agencyTypeNameE) {
		this.agencyTypeNameE = agencyTypeNameE;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getGramPanchayatName() {
		return gramPanchayatName;
	}

	public void setGramPanchayatName(String gramPanchayatName) {
		this.gramPanchayatName = gramPanchayatName;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public List<ContractorDepositBean> getContratorDepositsList() {
		return contratorDepositsList;
	}

	public void setContratorDepositsList(List<ContractorDepositBean> contratorDepositsList) {
		this.contratorDepositsList = contratorDepositsList;
	}

	public String getContractorTxt() {
		return contractorTxt;
	}

	public void setContractorTxt(String contractorTxt) {
		this.contractorTxt = contractorTxt;
	}

	public String getWorkRequisitionId() {
		return workRequisitionId;
	}

	public void setWorkRequisitionId(String workRequisitionId) {
		this.workRequisitionId = workRequisitionId;
	}

}
