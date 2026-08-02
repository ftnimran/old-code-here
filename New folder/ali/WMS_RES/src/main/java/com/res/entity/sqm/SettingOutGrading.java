package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SettingOutGrading {
	
	private String settingOutGradingGrade;
	private String settingOutGradingReasons;
	
	public SettingOutGrading() {
		
	}
	
	
	public SettingOutGrading(String settingOutGradingGrade, String settingOutGradingReasons) {
		super();
		this.settingOutGradingGrade = settingOutGradingGrade;
		this.settingOutGradingReasons = settingOutGradingReasons;
		
		
	}
	
	public String getSettingOutGradingGrade() {
		return settingOutGradingGrade;
	}


	public void setSettingOutGradingGrade(String settingOutGradingGrade) {
		this.settingOutGradingGrade = settingOutGradingGrade;
	}


	public String getSettingOutGradingReasons() {
		return settingOutGradingReasons;
	}
	public void setSettingOutGradingReasons(String settingOutGradingReasons) {
		this.settingOutGradingReasons = settingOutGradingReasons;
	}

}
