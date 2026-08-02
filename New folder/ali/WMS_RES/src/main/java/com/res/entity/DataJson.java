package com.res.entity;

import com.res.bean.InspectionRequestBean;

public class DataJson {

	
	private String id;
	
	private String workId;
	
	private String role;
	
	private Long userId;
	
	private String xml;
	
	private InspectionRequestBean inspectionBean;
	
	private Long sqmAllocationId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getSqmAllocationId() {
		return sqmAllocationId;
	}

	public void setSqmAllocationId(Long sqmAllocationId) {
		this.sqmAllocationId = sqmAllocationId;
	}

	public String toString() {
		return "id: "+ id + ", xml: "+ xml +", role: " +role+", userId: "+userId;

	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public InspectionRequestBean getInspectionBean() {
		return inspectionBean;
	}

	public void setInspectionBean(InspectionRequestBean inspectionBean) {
		this.inspectionBean = inspectionBean;
	}
	
	
	
}
