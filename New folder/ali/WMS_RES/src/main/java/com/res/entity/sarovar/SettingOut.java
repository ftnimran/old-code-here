package com.res.entity.sarovar;

import javax.xml.bind.annotation.XmlRootElement;

import com.res.entity.sqm.SettingOutGrading;
@XmlRootElement
public class SettingOut {
	
	private String whetherBenchMarksEstablished;
	private String exactLocationsBenchMark;
	private String centerLineEstablished;
	private String properlyPreparedAvailable;
	private String referencePillarsEstablished;
	private SettingGrading settingGrading;
	
	public SettingOut() {
		
	}
	
	
	public SettingOut(String whetherBenchMarksEstablished, String exactLocationsBenchMark, String centerLineEstablished,
			String properlyPreparedAvailable,String referencePillarsEstablished ,SettingGrading settingGrading) {
		super();
		this.whetherBenchMarksEstablished = whetherBenchMarksEstablished;
		this.exactLocationsBenchMark = exactLocationsBenchMark;
		this.centerLineEstablished = centerLineEstablished;
		this.properlyPreparedAvailable = properlyPreparedAvailable;
		this.referencePillarsEstablished = referencePillarsEstablished;
		this.settingGrading = settingGrading;
		
	}
	
	
	
	
	
	
	
	public String getWhetherBenchMarksEstablished() {
		return whetherBenchMarksEstablished;
	}
	public void setWhetherBenchMarksEstablished(String whetherBenchMarksEstablished) {
		this.whetherBenchMarksEstablished = whetherBenchMarksEstablished;
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
	public String getReferencePillarsEstablished() {
		return referencePillarsEstablished;
	}
	public void setReferencePillarsEstablished(String referencePillarsEstablished) {
		this.referencePillarsEstablished = referencePillarsEstablished;
	}
	public SettingGrading getSettingGrading() {
		return settingGrading;
	}
	public void setSettingGrading(SettingGrading settingGrading) {
		this.settingGrading = settingGrading;
	}
     

}
