package com.res.entity.sarovar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SettingGrading {
	
    
    private String settingOutGradingGrade;
	private String settingOutGradingReasons;
	
	public SettingGrading() {
		
	}
	
	
	public SettingGrading(String settingOutGradingGrade, String settingOutGradingReasons) {
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
