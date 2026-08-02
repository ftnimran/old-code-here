package com.res.bean;

import java.util.ArrayList;
import java.util.List;

public class InspectionChecklistBean {
	
	private Integer workTypeId;
    private String workType;
    private List<InspectionTypeBean> inspectionTypes = new ArrayList<>();
	
    
    public Integer getWorkTypeId() {
		return workTypeId;
	}
	public void setWorkTypeId(Integer workTypeId) {
		this.workTypeId = workTypeId;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public List<InspectionTypeBean> getInspectionTypes() {
		return inspectionTypes;
	}
	public void setInspectionTypes(List<InspectionTypeBean> inspectionTypes) {
		this.inspectionTypes = inspectionTypes;
	}
    
    

}
