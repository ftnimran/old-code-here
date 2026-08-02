package com.res.bean;

import java.util.List;

public class InspectionQuestionsBean {

    private Integer id;
    private String inspectionName;
    private String dataType;
    private List<String> dataValues;
    private Boolean required = true;
    private String note;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getInspectionName() {
		return inspectionName;
	}
	public void setInspectionName(String inspectionName) {
		this.inspectionName = inspectionName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public List<String> getDataValues() {
		return dataValues;
	}
	public void setDataValues(List<String> dataValues) {
		this.dataValues = dataValues;
	}
	public Boolean getRequired() {
		return required;
	}
	public void setRequired(Boolean required) {
		this.required = required;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
    
    
    
    
}
