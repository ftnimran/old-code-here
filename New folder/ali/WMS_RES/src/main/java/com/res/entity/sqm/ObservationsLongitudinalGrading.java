package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObservationsLongitudinalGrading {
	
	 private String observationsLongitudinalGrade;
	 private String observationsLongitudinalGradeReasons;
	 
	 
	 public ObservationsLongitudinalGrading() {
		 
	 }
	 
	 public ObservationsLongitudinalGrading(String observationsLongitudinalGrade, String observationsLongitudinalGradeReasons) {
			super();
			this.observationsLongitudinalGrade = observationsLongitudinalGrade;
			this.observationsLongitudinalGradeReasons = observationsLongitudinalGradeReasons;
			
			
		}
	 
	 
	public String getObservationsLongitudinalGrade() {
		return observationsLongitudinalGrade;
	}
	public void setObservationsLongitudinalGrade(String observationsLongitudinalGrade) {
		this.observationsLongitudinalGrade = observationsLongitudinalGrade;
	}
	public String getObservationsLongitudinalGradeReasons() {
		return observationsLongitudinalGradeReasons;
	}
	public void setObservationsLongitudinalGradeReasons(String observationsLongitudinalGradeReasons) {
		this.observationsLongitudinalGradeReasons = observationsLongitudinalGradeReasons;
	}

}
