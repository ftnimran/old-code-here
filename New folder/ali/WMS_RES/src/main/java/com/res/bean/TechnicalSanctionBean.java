package com.res.bean;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import com.res.entity.TechnicalStatus;
import com.res.entity.WorkType;

public class TechnicalSanctionBean {

	private Long technicalSanctionId;

	private TechnicalSanctionTypeBean technicalSanctionTypeBean;
	
	private BigDecimal estimatedCost;

	//--------------------Richa
	

	private String technicalSanctionNo;

    private String  technicalSanctionNoOld;

	private String technicalSanctionDate;

	private String tsDispatchNumber;

	private String tsDispatchDate;
	
	//--------------------Richa
	
	private WorkBean workBean;

	private String status;
	
	private String workStatus;

	private MultipartFile technicalSanctionFile;

	private MultipartFile latestDrawingCopyFile;

	private MultipartFile estimateFile;

	private Long tsIssuingAuthorityId;

	private String tsAuthorityName;

	private Long requestStatus;

	private BigDecimal technicalSanctionAmount;

	private Long workRequestStatusId;

	private String workType;

	private Long workTypeId;

	private String workSubType;

	private String workName;

	private String workRequisitionId;

	private String workRequisitionNo;
	private WorkType workTypeId1;
	private String workTypeName;
	private Long workSubTypeId;

	private String workSubTypeName;

	private Long technicalSanctionStatusId;

	private Long administrativeSanctionStatusId;
	private Long lineDepartmentId;

	private LineDepartmentBean lineDepartmentBean;

	private Boolean isCeOfficeName;
	private Boolean isSeOfficeName;
	private Boolean isDistrictName;
	private Boolean isGrampanchayatName;
	private Boolean isLinedepartmentName;
	private Boolean isEeOfficeName;
	private Long workId;
	private String lineDepartmentName;
	private String estimatedCostString;
	private String totalCostString;
	private Long districtId;
	private String districtName;
	private Long blockId;
	private int index;
	private TechnicalStatus technicalStatus;
	private String administrationSanctionType;
	private Long estimationId;
	//-----------------Rakesh
	private Integer revisionNo;
	
	private Long agencyTypeId;
	//Rakesh

	private Long parentId;
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
	

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getAdministrationSanctionType() {
		return administrationSanctionType;
	}

	public void setAdministrationSanctionType(String administrationSanctionType) {
		this.administrationSanctionType = administrationSanctionType;
	}

	public TechnicalStatus getTechnicalStatus() {
		return technicalStatus;
	}

	public void setTechnicalStatus(TechnicalStatus technicalStatus) {
		this.technicalStatus = technicalStatus;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Long getWorkSubTypeId() {
		return workSubTypeId;
	}

	public void setWorkSubTypeId(Long workSubTypeId) {
		this.workSubTypeId = workSubTypeId;
	}

	public WorkType getWorkTypeId1() {
		return workTypeId1;
	}

	public void setWorkTypeId1(WorkType workTypeId1) {
		this.workTypeId1 = workTypeId1;
	}

	public String getWorkTypeName() {
		return workTypeName;
	}

	public void setWorkTypeName(String workTypeName) {
		this.workTypeName = workTypeName;
	}

	public String getWorkRequisitionId() {
		return workRequisitionId;
	}

	public void setWorkRequisitionId(String workRequisitionId) {
		this.workRequisitionId = workRequisitionId;
	}

	public String getWorkRequisitionNo() {
		return workRequisitionNo;
	}

	public void setWorkRequisitionNo(String workRequisitionNo) {
		this.workRequisitionNo = workRequisitionNo;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public Long getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(Long workTypeId) {
		this.workTypeId = workTypeId;
	}

	public String getWorkSubType() {
		return workSubType;
	}

	public void setWorkSubType(String workSubType) {
		this.workSubType = workSubType;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public Long getWorkRequestStatusId() {
		return workRequestStatusId;
	}

	public void setWorkRequestStatusId(Long workRequestStatusId) {
		this.workRequestStatusId = workRequestStatusId;
	}

	public String getTsDispatchNumber() {
		return tsDispatchNumber;
	}

	public void setTsDispatchNumber(String tsDispatchNumber) {
		this.tsDispatchNumber = tsDispatchNumber;
	}

	public Long getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(Long requestStatus) {
		this.requestStatus = requestStatus;
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

	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
	}

	public Boolean getIsEeOfficeName() {
		return isEeOfficeName;
	}

	public void setIsEeOfficeName(Boolean isEeOfficeName) {
		this.isEeOfficeName = isEeOfficeName;
	}

	public TechnicalSanctionBean() {
	}

	public TechnicalSanctionBean(Long technicalSanctionId) {
		this.technicalSanctionId = technicalSanctionId;
	}

	public Long getTechnicalSanctionId() {
		return technicalSanctionId;
	}

	public void setTechnicalSanctionId(Long technicalSanctionId) {
		this.technicalSanctionId = technicalSanctionId;
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

	public TechnicalSanctionTypeBean getTechnicalSanctionTypeBean() {
		return technicalSanctionTypeBean;
	}

	public void setTechnicalSanctionTypeBean(
			TechnicalSanctionTypeBean technicalSanctionTypeBean) {
		this.technicalSanctionTypeBean = technicalSanctionTypeBean;
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

	public String getLineDepartmentName() {
		return lineDepartmentName;
	}

	public void setLineDepartmentName(String lineDepartmentName) {
		this.lineDepartmentName = lineDepartmentName;
	}

	public String getEstimatedCostString() {
		return estimatedCostString;
	}

	public void setEstimatedCostString(String estimatedCostString) {
		this.estimatedCostString = estimatedCostString;
	}

	public String getTotalCostString() {
		return totalCostString;
	}

	public void setTotalCostString(String totalCostString) {
		this.totalCostString = totalCostString;
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

	public Long getBlockId() {
		return blockId;
	}

	public void setBlockId(Long blockId) {
		this.blockId = blockId;
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

	public BigDecimal getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(BigDecimal estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public Long getTechnicalSanctionStatusId() {
		return technicalSanctionStatusId;
	}

	public void setTechnicalSanctionStatusId(Long technicalSanctionStatusId) {
		this.technicalSanctionStatusId = technicalSanctionStatusId;
	}

	public Long getAdministrativeSanctionStatusId() {
		return administrativeSanctionStatusId;
	}

	public void setAdministrativeSanctionStatusId(
			Long administrativeSanctionStatusId) {
		this.administrativeSanctionStatusId = administrativeSanctionStatusId;
	}

	public BigDecimal getTechnicalSanctionAmount() {
		return technicalSanctionAmount;
	}

	public void setTechnicalSanctionAmount(BigDecimal technicalSanctionAmount) {
		this.technicalSanctionAmount = technicalSanctionAmount;
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
	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public Long getEstimationId() {
		return estimationId;
	}

	public void setEstimationId(Long estimationId) {
		this.estimationId = estimationId;
	}

	public Integer getRevisionNo() {
		return revisionNo;
	}

	public void setRevisionNo(Integer revisionNo) {
		this.revisionNo = revisionNo;
	}

	public Long getAgencyTypeId() {
		return agencyTypeId;
	}

	public void setAgencyTypeId(Long agencyTypeId) {
		this.agencyTypeId = agencyTypeId;
	}
	
}
