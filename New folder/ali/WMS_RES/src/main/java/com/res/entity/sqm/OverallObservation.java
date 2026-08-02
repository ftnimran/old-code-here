package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OverallObservation {

	private String overallObservationGrading;
	private String overallObservationGradingRemarks;
	
	
	
	
	
	public OverallObservation(String overallObservationGrading, String overallObservationGradingRemarks
			) {
		super();
		this.overallObservationGrading = overallObservationGrading;
		this.overallObservationGradingRemarks = overallObservationGradingRemarks;
		
		
		
	}
	
	public OverallObservation() {
		
	}

	public String getOverallObservationGrading() {
		return overallObservationGrading;
	}

	public void setOverallObservationGrading(String overallObservationGrading) {
		this.overallObservationGrading = overallObservationGrading;
	}

	public String getOverallObservationGradingRemarks() {
		return overallObservationGradingRemarks;
	}

	public void setOverallObservationGradingRemarks(String overallObservationGradingRemarks) {
		this.overallObservationGradingRemarks = overallObservationGradingRemarks;
	}

	
	

	
	



}
