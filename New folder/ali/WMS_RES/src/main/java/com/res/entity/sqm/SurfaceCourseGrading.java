package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SurfaceCourseGrading {
	
	private String surfaceCourseGrade;
    private String surfaceCourseGradeReasons;
    
    public SurfaceCourseGrading() {
    	
    }
    
    public SurfaceCourseGrading(String surfaceCourseGrade, String surfaceCourseGradeReasons) {
		super();
		this.surfaceCourseGrade = surfaceCourseGrade;
		this.surfaceCourseGradeReasons = surfaceCourseGradeReasons;
		
		
	}

	public String getSurfaceCourseGrade() {
		return surfaceCourseGrade;
	}

	public void setSurfaceCourseGrade(String surfaceCourseGrade) {
		this.surfaceCourseGrade = surfaceCourseGrade;
	}

	public String getSurfaceCourseGradeReasons() {
		return surfaceCourseGradeReasons;
	}

	public void setSurfaceCourseGradeReasons(String surfaceCourseGradeReasons) {
		this.surfaceCourseGradeReasons = surfaceCourseGradeReasons;
	}

}
