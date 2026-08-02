package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CrossDrainageWorksGrading {
	
	private String crossDrainageWorksGrade;
	private String crossDrainageWorksGradeReasons;
	
	public CrossDrainageWorksGrading() {
		
	}
	
	public CrossDrainageWorksGrading(String crossDrainageWorksGrade, String crossDrainageWorksGradeReasons) {
		super();
		this.crossDrainageWorksGrade = crossDrainageWorksGrade;
		this.crossDrainageWorksGradeReasons = crossDrainageWorksGradeReasons;
		
		
	}

	public String getCrossDrainageWorksGrade() {
		return crossDrainageWorksGrade;
	}

	public void setCrossDrainageWorksGrade(String crossDrainageWorksGrade) {
		this.crossDrainageWorksGrade = crossDrainageWorksGrade;
	}

	public String getCrossDrainageWorksGradeReasons() {
		return crossDrainageWorksGradeReasons;
	}

	public void setCrossDrainageWorksGradeReasons(String crossDrainageWorksGradeReasons) {
		this.crossDrainageWorksGradeReasons = crossDrainageWorksGradeReasons;
	}

}
