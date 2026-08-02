package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObservationsWorkmanshipGrading {
	
	private String observationsWorkmanshipGradingGrade;
	private String observationsWorkmanshipGradeRemarks;
	
	
	public ObservationsWorkmanshipGrading() {
		
	}
	
	public ObservationsWorkmanshipGrading(String observationsWorkmanshipGradingGrade, String observationsWorkmanshipGradeRemarks) {
		super();
		this.observationsWorkmanshipGradingGrade = observationsWorkmanshipGradingGrade;
		this.observationsWorkmanshipGradeRemarks = observationsWorkmanshipGradeRemarks;
		
		
	}
	
	public String getObservationsWorkmanshipGradingGrade() {
		return observationsWorkmanshipGradingGrade;
	}
	public void setObservationsWorkmanshipGradingGrade(String observationsWorkmanshipGradingGrade) {
		this.observationsWorkmanshipGradingGrade = observationsWorkmanshipGradingGrade;
	}
	public String getObservationsWorkmanshipGradeRemarks() {
		return observationsWorkmanshipGradeRemarks;
	}
	public void setObservationsWorkmanshipGradeRemarks(String observationsWorkmanshipGradeRemarks) {
		this.observationsWorkmanshipGradeRemarks = observationsWorkmanshipGradeRemarks;
	}

}
