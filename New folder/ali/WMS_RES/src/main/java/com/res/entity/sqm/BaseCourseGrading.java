package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BaseCourseGrading {
	
	private String baseCourseGrade;
    private String baseCourseGradeReasons;
    
    public BaseCourseGrading() {
    	
    }
    
    public BaseCourseGrading(String baseCourseGrade, String baseCourseGradeReasons) {
		super();
		this.baseCourseGrade = baseCourseGrade;
		this.baseCourseGradeReasons = baseCourseGradeReasons;
		
		
	}

	public String getBaseCourseGrade() {
		return baseCourseGrade;
	}

	public void setBaseCourseGrade(String baseCourseGrade) {
		this.baseCourseGrade = baseCourseGrade;
	}

	public String getBaseCourseGradeReasons() {
		return baseCourseGradeReasons;
	}

	public void setBaseCourseGradeReasons(String baseCourseGradeReasons) {
		this.baseCourseGradeReasons = baseCourseGradeReasons;
	}

}
