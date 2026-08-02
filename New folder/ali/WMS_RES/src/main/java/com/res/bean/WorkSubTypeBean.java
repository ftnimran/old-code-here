package com.res.bean;

public class WorkSubTypeBean {
	
	private Long workSubTypeId;

	private String workSubTypeNameE;

	private String workSubTypeNameH;
	
	private Short enabled;

	private WorkTypeBean workType;

	public WorkSubTypeBean() {
	}

	public WorkSubTypeBean(Long workSubTypeId) {
		this.workSubTypeId = workSubTypeId;
	}

	public Long getWorkSubTypeId() {
		return workSubTypeId;
	}

	public void setWorkSubTypeId(Long workSubTypeId) {
		this.workSubTypeId = workSubTypeId;
	}

	public String getWorkSubTypeNameE() {
		return workSubTypeNameE;
	}

	public void setWorkSubTypeNameE(String workSubTypeNameE) {
		this.workSubTypeNameE = workSubTypeNameE;
	}

	public String getWorkSubTypeNameH() {
		return workSubTypeNameH;
	}

	public void setWorkSubTypeNameH(String workSubTypeNameH) {
		this.workSubTypeNameH = workSubTypeNameH;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	public WorkTypeBean getWorkType() {
		return workType;
	}

	public void setWorkType(WorkTypeBean workType) {
		this.workType = workType;
	}
}
