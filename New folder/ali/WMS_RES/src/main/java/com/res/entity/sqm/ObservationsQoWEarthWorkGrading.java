package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObservationsQoWEarthWorkGrading {
	private String observationsQoWEarthWorkGradingGrade;
	private String observationsQoWEarthWorkGradingRemarks;
	
	public ObservationsQoWEarthWorkGrading() {
		
	} 
	
	public ObservationsQoWEarthWorkGrading(String observationsQoWEarthWorkGradingGrade, String observationsQoWEarthWorkGradingRemarks) {
		super();
		this.observationsQoWEarthWorkGradingGrade = observationsQoWEarthWorkGradingGrade;
		this.observationsQoWEarthWorkGradingRemarks = observationsQoWEarthWorkGradingRemarks;
		
		
	}
	
	
	public String getObservationsQoWEarthWorkGradingGrade() {
		return observationsQoWEarthWorkGradingGrade;
	}
	public void setObservationsQoWEarthWorkGradingGrade(String observationsQoWEarthWorkGradingGrade) {
		this.observationsQoWEarthWorkGradingGrade = observationsQoWEarthWorkGradingGrade;
	}
	public String getObservationsQoWEarthWorkGradingRemarks() {
		return observationsQoWEarthWorkGradingRemarks;
	}
	public void setObservationsQoWEarthWorkGradingRemarks(String observationsQoWEarthWorkGradingRemarks) {
		this.observationsQoWEarthWorkGradingRemarks = observationsQoWEarthWorkGradingRemarks;
	}

}
