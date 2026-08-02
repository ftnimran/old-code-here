package com.res.entity.singlevillagepipped;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WorkDetails {
 public WorkDetails(String workName, String executionAgency, String workType, String workSubType,
			String workId) {
		super();
		this.workName = workName;
		this.executionAgency = executionAgency;
		this.workType = workType;
		this.workSubType = workSubType;
		
		this.workId=workId;
	}

public String getWorkId() {
	return workId;
}

public void setWorkId(String workId) {
	this.workId = workId;
}

private String workName="";
 private String executionAgency="";
 private String workType="";
 private String workSubType="";

 // for Id 
 private String workId="";


 // Getter Methods 

 public WorkDetails() {
	super();
}

public String getWorkName() {
  return workName;
 }

 public String getExecutionAgency() {
  return executionAgency;
 }

 public String getWorkType() {
  return workType;
 }

 public String getWorkSubType() {
  return workSubType;
 }

 

 // Setter Methods 

 public void setWorkName(String workName) {
  this.workName = workName;
 }

 public void setExecutionAgency(String executionAgency) {
  this.executionAgency = executionAgency;
 }

 public void setWorkType(String workType) {
  this.workType = workType;
 }

 public void setWorkSubType(String workSubType) {
  this.workSubType = workSubType;
 }

 
}