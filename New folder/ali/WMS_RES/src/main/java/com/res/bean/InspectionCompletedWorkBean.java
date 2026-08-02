package com.res.bean;

import java.util.List;

public class InspectionCompletedWorkBean {
	
	List<InspectionAnswerBean> inspectionAnswerBean;
	List<ImageBean> imageBean;
	List<FileBean> fileBean;
	public List<InspectionAnswerBean> getInspectionAnswerBean() {
		return inspectionAnswerBean;
	}
	public void setInspectionAnswerBean(List<InspectionAnswerBean> inspectionAnswerBean) {
		this.inspectionAnswerBean = inspectionAnswerBean;
	}
	public List<ImageBean> getImageBean() {
		return imageBean;
	}
	public void setImageBean(List<ImageBean> imageBean) {
		this.imageBean = imageBean;
	}
	public List<FileBean> getFileBean() {
		return fileBean;
	}
	public void setFileBean(List<FileBean> fileBean) {
		this.fileBean = fileBean;
	}
	
	
	
	
	

}
