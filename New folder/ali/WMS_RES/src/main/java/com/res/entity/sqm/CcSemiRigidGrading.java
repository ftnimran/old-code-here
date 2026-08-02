package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CcSemiRigidGrading {
	
	private String ccSemiRigidGrade;
    private String ccSemiRigidGradeReasons;
    
    public CcSemiRigidGrading() {
    	
    }
    public CcSemiRigidGrading(String ccSemiRigidGrade, String ccSemiRigidGradeReasons) {
		super();
		this.ccSemiRigidGrade = ccSemiRigidGrade;
		this.ccSemiRigidGradeReasons = ccSemiRigidGradeReasons;
		
		
	}
	public String getCcSemiRigidGrade() {
		return ccSemiRigidGrade;
	}
	public void setCcSemiRigidGrade(String ccSemiRigidGrade) {
		this.ccSemiRigidGrade = ccSemiRigidGrade;
	}
	public String getCcSemiRigidGradeReasons() {
		return ccSemiRigidGradeReasons;
	}
	public void setCcSemiRigidGradeReasons(String ccSemiRigidGradeReasons) {
		this.ccSemiRigidGradeReasons = ccSemiRigidGradeReasons;
	}

}
