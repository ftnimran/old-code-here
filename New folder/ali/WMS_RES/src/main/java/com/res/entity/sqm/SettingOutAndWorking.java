package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SettingOutAndWorking {
	
	private String benchMarkEstablished;
	private String exactLocationsBenchMark;
	private String centerLineEstablished;
	private String properlyPreparedAvailable;
	private SettingOutGrading settingOutGrading;
	
	public SettingOutAndWorking() {
		
	}
	
	public SettingOutAndWorking(String benchMarkEstablished, String exactLocationsBenchMark, String centerLineEstablished,
			String properlyPreparedAvailable, SettingOutGrading settingOutGrading) {
		super();
		this.benchMarkEstablished = benchMarkEstablished;
		this.exactLocationsBenchMark = exactLocationsBenchMark;
		this.centerLineEstablished = centerLineEstablished;
		this.properlyPreparedAvailable = properlyPreparedAvailable;
		this.settingOutGrading = settingOutGrading;
		
	}
	

	public String getBenchMarkEstablished() {
		return benchMarkEstablished;
	}

	public void setBenchMarkEstablished(String benchMarkEstablished) {
		this.benchMarkEstablished = benchMarkEstablished;
	}

	public String getExactLocationsBenchMark() {
		return exactLocationsBenchMark;
	}

	public void setExactLocationsBenchMark(String exactLocationsBenchMark) {
		this.exactLocationsBenchMark = exactLocationsBenchMark;
	}

	public String getCenterLineEstablished() {
		return centerLineEstablished;
	}

	public void setCenterLineEstablished(String centerLineEstablished) {
		this.centerLineEstablished = centerLineEstablished;
	}

	public String getProperlyPreparedAvailable() {
		return properlyPreparedAvailable;
	}

	public void setProperlyPreparedAvailable(String properlyPreparedAvailable) {
		this.properlyPreparedAvailable = properlyPreparedAvailable;
	}

	public SettingOutGrading getSettingOutGrading() {
		return settingOutGrading;
	}

	public void setSettingOutGrading(SettingOutGrading settingOutGrading) {
		this.settingOutGrading = settingOutGrading;
	}

	
	
	
	
	

}
