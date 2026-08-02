package com.res.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.web.multipart.MultipartFile;

public class WorkBean2 {

	private String chiefEngineerOfficeName;

	private String superintendingEngineerOfficeName;

	private Long workId;
	
	private String workName;
	
	private String workRequisitionNo;

	private String workTypeName;

	private String workSubTypeName;

	private BigDecimal estimatedCost;

	private BigDecimal totalCost;
	
	private String accountHeadName;

	private String agencyName;

	private BigDecimal totalExpenditureTill31March2018;

	private String physicalStageType;

	private String agreementDate;

	private String agreementNumber;

	private String executiveEngineerOfficeName;
	
	private String assistantEngineerName;
	

	private String subDivisionOfficerName;

	private String subEngineerName;

	private String districtName;

	private String blockName;

	private String gramPanchayatName;

	private String lineDepartmentName;

	private String financialYear;
	
	private String status;

	private String workStatus;

	private String tenderedRateSign;

	private BigDecimal tenderedRatePer;

	private BigDecimal pacAmount;

	private BigDecimal tenderCost;

	private String villageName;

	private BigDecimal probableAmountOfWork;

	private String workRequestStatus;
	
	private String tentativeCompletionDate;
	
	private String actualCompletionDate;
	
	private String physicalCCIssuedDate;
	
	private String financialCCIssuedDate;
	
	private String latitude;
	
	private String longitude;
	
	private String loggedInUserName;


	public String getTentativeCompletionDate() {
		return tentativeCompletionDate;
	}


	public void setTentativeCompletionDate(String tentativeCompletionDate) {
		this.tentativeCompletionDate = tentativeCompletionDate;
	}


	public String getActualCompletionDate() {
		return actualCompletionDate;
	}


	public void setActualCompletionDate(String actualCompletionDate) {
		this.actualCompletionDate = actualCompletionDate;
	}


	public String getPhysicalCCIssuedDate() {
		return physicalCCIssuedDate;
	}


	public void setPhysicalCCIssuedDate(String physicalCCIssuedDate) {
		this.physicalCCIssuedDate = physicalCCIssuedDate;
	}


	public String getFinancialCCIssuedDate() {
		return financialCCIssuedDate;
	}


	public void setFinancialCCIssuedDate(String financialCCIssuedDate) {
		this.financialCCIssuedDate = financialCCIssuedDate;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public WorkBean2() {
		super();
	}


	public String getChiefEngineerOfficeName() {
		return chiefEngineerOfficeName;
	}


	public void setChiefEngineerOfficeName(String chiefEngineerOfficeName) {
		this.chiefEngineerOfficeName = chiefEngineerOfficeName;
	}


	public String getSuperintendingEngineerOfficeName() {
		return superintendingEngineerOfficeName;
	}


	public void setSuperintendingEngineerOfficeName(String superintendingEngineerOfficeName) {
		this.superintendingEngineerOfficeName = superintendingEngineerOfficeName;
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


	public String getWorkRequisitionNo() {
		return workRequisitionNo;
	}


	public void setWorkRequisitionNo(String workRequisitionNo) {
		this.workRequisitionNo = workRequisitionNo;
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


	public String getPhysicalStageType() {
		return physicalStageType;
	}


	public void setPhysicalStageType(String physicalStageType) {
		this.physicalStageType = physicalStageType;
	}


	public String getAgreementDate() {
		return agreementDate;
	}


	public void setAgreementDate(String agreementDate) {
		this.agreementDate = agreementDate;
	}


	public String getAgreementNumber() {
		return agreementNumber;
	}


	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
	}


	public String getExecutiveEngineerOfficeName() {
		return executiveEngineerOfficeName;
	}


	public void setExecutiveEngineerOfficeName(String executiveEngineerOfficeName) {
		this.executiveEngineerOfficeName = executiveEngineerOfficeName;
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


	public String getDistrictName() {
		return districtName;
	}


	public void setDistrictName(String districtName) {
		this.districtName = districtName;
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


	public String getLineDepartmentName() {
		return lineDepartmentName;
	}


	public void setLineDepartmentName(String lineDepartmentName) {
		this.lineDepartmentName = lineDepartmentName;
	}


	public String getFinancialYear() {
		return financialYear;
	}


	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getWorkStatus() {
		return workStatus;
	}


	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
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


	public String getVillageName() {
		return villageName;
	}


	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}


	public BigDecimal getProbableAmountOfWork() {
		return probableAmountOfWork;
	}


	public void setProbableAmountOfWork(BigDecimal probableAmountOfWork) {
		this.probableAmountOfWork = probableAmountOfWork;
	}


	public String getWorkRequestStatus() {
		return workRequestStatus;
	}


	public void setWorkRequestStatus(String workRequestStatus) {
		this.workRequestStatus = workRequestStatus;
	}


	public String getAccountHeadName() {
		return accountHeadName;
	}


	public void setAccountHeadName(String accountHeadName) {
		this.accountHeadName = accountHeadName;
	}


	public String getLoggedInUserName() {
		return loggedInUserName;
	}


	public void setLoggedInUserName(String loggedInUserName) {
		this.loggedInUserName = loggedInUserName;
	}


	public String getSubDivisionOfficerName() {
		return subDivisionOfficerName;
	}


	public void setSubDivisionOfficerName(String subDivisionOfficerName) {
		this.subDivisionOfficerName = subDivisionOfficerName;
	}
	
	

}