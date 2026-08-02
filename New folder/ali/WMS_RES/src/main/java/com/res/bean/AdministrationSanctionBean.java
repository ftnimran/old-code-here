package com.res.bean;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

public class AdministrationSanctionBean {
	
	private Long administrationSanctionId;
	
	private AdministrationSanctionTypeBean administrationSanctionTypeBean;

	private String administrationSanctionNo;
	
	private String administrationSanctionDate;
	
	private IssuingAuthorityBean issuingAuthorityBean;
	
	private Long work_id;
	
	private WorkBean workBean;
	
	private String status;
	
	private MultipartFile administrationSanctionFile;
	
	private String asAuthorityName;
	
	private BigDecimal asAmount;
	
	private Long issuingAuthorityId;
	
	private long workRequestStatusId;
	
	private Long requestStatus;
	
	private String requestStatusName;
	
	private Long workId;
	private String workRequisitionId;
	private String workName;
	private String executionAgency;
	private BigDecimal tenderCost;
	private String bidderName;
	private Long statusId;
	private String status1;
	private String loggedInUserRole;
	private int index;
	private String tenderStatus;
	private Long tenderStatusId;
	private BigDecimal proposeddistance;
	private Long technicalSanctionId;
	
	private BigDecimal administrationSanctionAmount;
	
	private String competentAuthName;
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
	
	public BigDecimal getProposeddistance() {
		return proposeddistance;
	}

	public void setProposeddistance(BigDecimal proposeddistance) {
		this.proposeddistance = proposeddistance;
	}

	public Long getTenderStatusId() {
		return tenderStatusId;
	}

	public void setTenderStatusId(Long tenderStatusId) {
		this.tenderStatusId = tenderStatusId;
	}

	public String getLoggedInUserRole() {
		return loggedInUserRole;
	}

	public String getTenderStatus() {
		return tenderStatus;
	}

	public void setTenderStatus(String tenderStatus) {
		this.tenderStatus = tenderStatus;
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

	public String getWorkRequisitionId() {
		return workRequisitionId;
	}

	public void setWorkRequisitionId(String workRequisitionId) {
		this.workRequisitionId = workRequisitionId;
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

	public BigDecimal getTenderCost() {
		return tenderCost;
	}

	public void setTenderCost(BigDecimal tenderCost) {
		this.tenderCost = tenderCost;
	}

	public String getBidderName() {
		return bidderName;
	}

	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	public String getRequestStatusName() {
		return requestStatusName;
	}

	public void setRequestStatusName(String requestStatusName) {
		this.requestStatusName = requestStatusName;
	}

	public AdministrationSanctionBean() {
	}

	public AdministrationSanctionBean(Long administrationSanctionId) {
		this.administrationSanctionId = administrationSanctionId;
	}

	public Long getAdministrationSanctionId() {
		return administrationSanctionId;
	}

	public void setAdministrationSanctionId(Long administrationSanctionId) {
		this.administrationSanctionId = administrationSanctionId;
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

	public AdministrationSanctionTypeBean getAdministrationSanctionTypeBean() {
		return administrationSanctionTypeBean;
	}

	public void setAdministrationSanctionTypeBean(
			AdministrationSanctionTypeBean administrationSanctionTypeBean) {
		this.administrationSanctionTypeBean = administrationSanctionTypeBean;
	}

	public IssuingAuthorityBean getIssuingAuthorityBean() {
		return issuingAuthorityBean;
	}

	public void setIssuingAuthorityBean(IssuingAuthorityBean issuingAuthorityBean) {
		this.issuingAuthorityBean = issuingAuthorityBean;
	}

	public WorkBean getWorkBean() {
		return workBean;
	}

	public void setWorkBean(WorkBean workBean) {
		this.workBean = workBean;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public MultipartFile getAdministrationSanctionFile() {
		return administrationSanctionFile;
	}

	public void setAdministrationSanctionFile(
			MultipartFile administrationSanctionFile) {
		this.administrationSanctionFile = administrationSanctionFile;
	}

	public String getAsAuthorityName() {
		return asAuthorityName;
	}

	public void setAsAuthorityName(String asAuthorityName) {
		this.asAuthorityName = asAuthorityName;
	}

	public BigDecimal getAsAmount() {
		return asAmount;
	}

	public void setAsAmount(BigDecimal asAmount) {
		this.asAmount = asAmount;
	}

	public Long getIssuingAuthorityId() {
		return issuingAuthorityId;
	}

	public void setIssuingAuthorityId(Long issuingAuthorityId) {
		this.issuingAuthorityId = issuingAuthorityId;
	}

	public Long getWork_id() {
		return work_id;
	}

	public void setWork_id(Long work_id) {
		this.work_id = work_id;
	}
	
	public long getWorkRequestStatusId() {
		return workRequestStatusId;
	}

	public void setWorkRequestStatusId(long workRequestStatusId) {
		this.workRequestStatusId = workRequestStatusId;
	}

	public Long getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(Long requestStatus) {
		this.requestStatus = requestStatus;
	}

	public Long getTechnicalSanctionId() {
		return technicalSanctionId;
	}

	public void setTechnicalSanctionId(Long technicalSanctionId) {
		this.technicalSanctionId = technicalSanctionId;
	}

	public BigDecimal getAdministrationSanctionAmount() {
		return administrationSanctionAmount;
	}

	public void setAdministrationSanctionAmount(
			BigDecimal administrationSanctionAmount) {
		this.administrationSanctionAmount = administrationSanctionAmount;
	}

}
