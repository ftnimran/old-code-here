package com.res.bean;

import java.util.ArrayList;
import java.util.List;

public class InspectionTypeBean {

    private Integer inspectionTypeId;
    private String inspectionType;
    private List<InspectionQuestionsBean> questions = new ArrayList<InspectionQuestionsBean>();
	
    public Integer getInspectionTypeId() {
		return inspectionTypeId;
	}
	public void setInspectionTypeId(Integer inspectionTypeId) {
		this.inspectionTypeId = inspectionTypeId;
	}
	public String getInspectionType() {
		return inspectionType;
	}
	public void setInspectionType(String inspectionType) {
		this.inspectionType = inspectionType;
	}
	public List<InspectionQuestionsBean> getQuestions() {
		return questions;
	}
	public void setQuestions(List<InspectionQuestionsBean> questions) {
		this.questions = questions;
	}
    
    
    
    
}