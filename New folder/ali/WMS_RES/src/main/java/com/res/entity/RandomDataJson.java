package com.res.entity;

public class RandomDataJson {
    private Long inspectionAnswerid;
	
	private Long workId;
	
	private String role;
	
	private Long userId;
	
	private Long billId;
	
	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	private String randomInspectionDateTime;

	public Long getInspectionAnswerid() {
		return inspectionAnswerid;
	}

	public void setInspectionAnswerid(Long inspectionAnswerid) {
		this.inspectionAnswerid = inspectionAnswerid;
	}

	public Long getWorkId() {
		return workId;
	}

	public void setWorkId(Long workId) {
		this.workId = workId;
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

	public String getRandomInspectionDateTime() {
		return randomInspectionDateTime;
	}

	public void setRandomInspectionDateTime(String randomInspectionDateTime) {
		this.randomInspectionDateTime = randomInspectionDateTime;
	}
}
