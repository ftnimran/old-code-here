package com.res.entity.sarovar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SiteClearanceGrading {
	private String siteClearanceGradingGrade;
	private String siteClearanceGradingReasons;
	
public SiteClearanceGrading() {
		
	}
	
	
	public SiteClearanceGrading(String siteClearanceGradingGrade, String siteClearanceGradingReasons) {
		super();
		this.siteClearanceGradingGrade = siteClearanceGradingGrade;
		this.siteClearanceGradingReasons = siteClearanceGradingReasons;
		
		
	}
	
	public String getSiteClearanceGradingGrade() {
		return siteClearanceGradingGrade;
	}
	public void setSiteClearanceGradingGrade(String siteClearanceGradingGrade) {
		this.siteClearanceGradingGrade = siteClearanceGradingGrade;
	}
	public String getSiteClearanceGradingReasons() {
		return siteClearanceGradingReasons;
	}
	public void setSiteClearanceGradingReasons(String siteClearanceGradingReasons) {
		this.siteClearanceGradingReasons = siteClearanceGradingReasons;
	}

}
