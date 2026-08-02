package com.res.bean;

public class SqmAllocationBean  {
	 private Long id;
	 private Long officeId;
	 private Long workId;
	 private Long inspectionId;
	 private String workName;
	 private String officeName;
	 private String inspectedBy;
	 private String createdDate;
	 private String workRequisitionNo;
	 private String overallGrading;
	 private String overallGradingRemarks;
	 
	 public String getOverallGradingRemarks() {
		return overallGradingRemarks;
	}
	public void setOverallGradingRemarks(String overallGradingRemarks) {
		this.overallGradingRemarks = overallGradingRemarks;
	}
	public String getOverallGrading() {
		return overallGrading;
	}
	public void setOverallGrading(String overallGrading) {
		this.overallGrading = overallGrading;
	}
	private int index;
		private Long usersId;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getOfficeId() {
			return officeId;
		}
		public void setOfficeId(Long officeId) {
			this.officeId = officeId;
		}
		public Long getWorkId() {
			return workId;
		}
		public void setWorkId(Long workId) {
			this.workId = workId;
		}
		public Long getUsersId() {
			return usersId;
		}
		public void setUsersId(Long usersId) {
			this.usersId = usersId;
		}
		public String getWorkName() {
			return workName;
		}
		public void setWorkName(String workName) {
			this.workName = workName;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public String getOfficeName() {
			return officeName;
		}
		public void setOfficeName(String officeName) {
			this.officeName = officeName;
		}
		public String getInspectedBy() {
			return inspectedBy;
		}
		public void setInspectedBy(String inspectedBy) {
			this.inspectedBy = inspectedBy;
		}
		public String getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(String createdDate) {
			this.createdDate = createdDate;
		}
		public String getWorkRequisitionNo() {
			return workRequisitionNo;
		}
		public void setWorkRequisitionNo(String workRequisitionNo) {
			this.workRequisitionNo = workRequisitionNo;
		}
		public Long getInspectionId() {
			return inspectionId;
		}
		public void setInspectionId(Long inspectionId) {
			this.inspectionId = inspectionId;
		}
	
		
	//List<OfficeType> findByEnabled(Short isEnabled);
    
}
