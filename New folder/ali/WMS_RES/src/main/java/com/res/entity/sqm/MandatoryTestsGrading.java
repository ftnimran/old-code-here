package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MandatoryTestsGrading {
	
	private String mandatoryTestsGradingGrade;
    private String mandatoryTestsGradingReasons;
    
    public MandatoryTestsGrading() {
    	
    }
    
    public MandatoryTestsGrading(String mandatoryTestsGradingGrade, String mandatoryTestsGradingReasons) {
		super();
		this.mandatoryTestsGradingGrade = mandatoryTestsGradingGrade;
		this.mandatoryTestsGradingReasons = mandatoryTestsGradingReasons;
		
		
	}
    
	public String getMandatoryTestsGradingGrade() {
		return mandatoryTestsGradingGrade;
	}
	public void setMandatoryTestsGradingGrade(String mandatoryTestsGradingGrade) {
		this.mandatoryTestsGradingGrade = mandatoryTestsGradingGrade;
	}
	public String getMandatoryTestsGradingReasons() {
		return mandatoryTestsGradingReasons;
	}
	public void setMandatoryTestsGradingReasons(String mandatoryTestsGradingReasons) {
		this.mandatoryTestsGradingReasons = mandatoryTestsGradingReasons;
	}

}
