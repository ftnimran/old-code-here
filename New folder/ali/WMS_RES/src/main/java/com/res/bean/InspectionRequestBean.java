package com.res.bean;

import java.util.List;

public class InspectionRequestBean {

	private InspectionDetailsBean inspectionDetail;
    private List<InspectionAnswerNewBean> inspectionAnswersList;
	public InspectionDetailsBean getInspectionDetail() {
		return inspectionDetail;
	}
	public void setInspectionDetail(InspectionDetailsBean inspectionDetail) {
		this.inspectionDetail = inspectionDetail;
	}
	public List<InspectionAnswerNewBean> getInspectionAnswersList() {
		return inspectionAnswersList;
	}
	public void setInspectionAnswersList(List<InspectionAnswerNewBean> inspectionAnswersList) {
		this.inspectionAnswersList = inspectionAnswersList;
	}
    
    
}
