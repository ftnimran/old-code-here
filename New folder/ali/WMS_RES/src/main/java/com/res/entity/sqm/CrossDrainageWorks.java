package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CrossDrainageWorks {
	private CrossDrainageWorksObservations crossDrainageWorksObservations;
	private CrossDrainageWorksGrading crossDrainageWorksGrading;
	private String crossDrainageWorksComments;
	
	public CrossDrainageWorks() {
		
	}
	
	public CrossDrainageWorks(CrossDrainageWorksObservations crossDrainageWorksObservations, CrossDrainageWorksGrading crossDrainageWorksGrading, String crossDrainageWorksComments) {
		super();
		this.crossDrainageWorksObservations = crossDrainageWorksObservations;
		this.crossDrainageWorksGrading = crossDrainageWorksGrading;
		this.crossDrainageWorksComments = crossDrainageWorksComments;
		
	}

	

	public CrossDrainageWorksObservations getCrossDrainageWorksObservations() {
		return crossDrainageWorksObservations;
	}

	public void setCrossDrainageWorksObservations(CrossDrainageWorksObservations crossDrainageWorksObservations) {
		this.crossDrainageWorksObservations = crossDrainageWorksObservations;
	}

	

	public CrossDrainageWorksGrading getCrossDrainageWorksGrading() {
		return crossDrainageWorksGrading;
	}

	public void setCrossDrainageWorksGrading(CrossDrainageWorksGrading crossDrainageWorksGrading) {
		this.crossDrainageWorksGrading = crossDrainageWorksGrading;
	}

	public String getCrossDrainageWorksComments() {
		return crossDrainageWorksComments;
	}

	public void setCrossDrainageWorksComments(String crossDrainageWorksComments) {
		this.crossDrainageWorksComments = crossDrainageWorksComments;
	}

}
