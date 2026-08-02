package com.res.bean;

public class ExecutiveWorkReportBean {
	
	private Integer id;
	private String officeName;
	
	private Integer officeId;
	
	private Integer totalWorkCount;
	
	private Integer notStartedWorkCount;
	
	private Integer inProgressWorkCount;
	
	private Integer onHoldWorkCount;
	
	private Integer completedWorkCount;
	
	private Integer cancelWorkCount;
	


	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public Integer getTotalWorkCount() {
		return totalWorkCount;
	}

	public void setTotalWorkCount(Integer totalWorkCount) {
		this.totalWorkCount = totalWorkCount;
	}

	public Integer getNotStartedWorkCount() {
		return notStartedWorkCount;
	}

	public void setNotStartedWorkCount(Integer notStartedWorkCount) {
		this.notStartedWorkCount = notStartedWorkCount;
	}

	public Integer getInProgressWorkCount() {
		return inProgressWorkCount;
	}

	public void setInProgressWorkCount(Integer inProgressWorkCount) {
		this.inProgressWorkCount = inProgressWorkCount;
	}

	public Integer getOnHoldWorkCount() {
		return onHoldWorkCount;
	}

	public void setOnHoldWorkCount(Integer onHoldWorkCount) {
		this.onHoldWorkCount = onHoldWorkCount;
	}

	public Integer getCancelWorkCount() {
		return cancelWorkCount;
	}

	public void setCancelWorkCount(Integer cancelWorkCount) {
		this.cancelWorkCount = cancelWorkCount;
	}

	public Integer getCompletedWorkCount() {
		return completedWorkCount;
	}

	public void setCompletedWorkCount(Integer completedWorkCount) {
		this.completedWorkCount = completedWorkCount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}
}
