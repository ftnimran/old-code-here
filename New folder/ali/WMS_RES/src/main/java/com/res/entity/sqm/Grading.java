package com.res.entity.sqm;

public class Grading {
	private String grade;
	private String reasons;
	public Grading() {
		
	}
	
	
	public Grading(String grade, String reasons) {
		super();
		this.grade = grade;
		this.reasons = reasons;
		
	}
	
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getReasons() {
		return reasons;
	}
	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

}
