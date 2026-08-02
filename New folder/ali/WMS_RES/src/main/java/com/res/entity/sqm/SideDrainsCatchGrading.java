package com.res.entity.sqm;

public class SideDrainsCatchGrading {
	
	private String sideDrainsCatchGrade;
	private String sideDrainsCatchGradeReasons;
	
	public SideDrainsCatchGrading() {
		
	}
	
	public SideDrainsCatchGrading(String sideDrainsCatchGrade, String sideDrainsCatchGradeReasons) {
		super();
		this.sideDrainsCatchGrade = sideDrainsCatchGrade;
		this.sideDrainsCatchGradeReasons = sideDrainsCatchGradeReasons;
		
		
	}

	public String getSideDrainsCatchGrade() {
		return sideDrainsCatchGrade;
	}

	public void setSideDrainsCatchGrade(String sideDrainsCatchGrade) {
		this.sideDrainsCatchGrade = sideDrainsCatchGrade;
	}

	public String getSideDrainsCatchGradeReasons() {
		return sideDrainsCatchGradeReasons;
	}

	public void setSideDrainsCatchGradeReasons(String sideDrainsCatchGradeReasons) {
		this.sideDrainsCatchGradeReasons = sideDrainsCatchGradeReasons;
	}

}
