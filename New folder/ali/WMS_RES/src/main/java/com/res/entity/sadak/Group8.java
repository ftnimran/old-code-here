package com.res.entity.sadak;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group8 {

	private String suggestion;
	private String error;
	private String lastGrading;
	private String officerName;
	private String officerMobileNo;
	private String physicalStage;
	private String completedLength;
	
	public Group8(String suggestion, String error, String lastGrading,
			String officerName, String officerMobileNo, String physicalStage, String completedLength) {
		super();
		this.suggestion = suggestion;
		this.error = error;
		this.lastGrading = lastGrading;
		this.officerName = officerName;
		this.officerMobileNo = officerMobileNo;
		this.physicalStage = physicalStage;
		this.completedLength = completedLength;
	}

	public Group8() {
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getLastGrading() {
		return lastGrading;
	}

	public void setLastGrading(String lastGrading) {
		this.lastGrading = lastGrading;
	}

	public String getOfficerName() {
		return officerName;
	}

	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}

	public String getOfficerMobileNo() {
		return officerMobileNo;
	}

	public void setOfficerMobileNo(String officerMobileNo) {
		this.officerMobileNo = officerMobileNo;
	}

	public String getPhysicalStage() {
		return physicalStage;
	}

	public void setPhysicalStage(String physicalStage) {
		this.physicalStage = physicalStage;
	}

	public String getCompletedLength() {
		return completedLength;
	}

	public void setCompletedLength(String completedLength) {
		this.completedLength = completedLength;
	}


}
