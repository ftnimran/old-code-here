package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObservationsLongitudinal {
	
	private String observationsLongitudinalRef;
	private String observationsLongitudinalGradient;
	private String observationsLongitudinalSU;
	
	public ObservationsLongitudinal() {
		
	}
	
	public ObservationsLongitudinal(String observationsLongitudinalRef, String observationsLongitudinalGradient, String observationsLongitudinalSU) {
		super();
		this.observationsLongitudinalRef = observationsLongitudinalRef;
		this.observationsLongitudinalGradient = observationsLongitudinalGradient;
		this.observationsLongitudinalSU = observationsLongitudinalSU;
		
		
	}

	public String getObservationsLongitudinalRef() {
		return observationsLongitudinalRef;
	}

	public void setObservationsLongitudinalRef(String observationsLongitudinalRef) {
		this.observationsLongitudinalRef = observationsLongitudinalRef;
	}

	public String getObservationsLongitudinalGradient() {
		return observationsLongitudinalGradient;
	}

	public void setObservationsLongitudinalGradient(String observationsLongitudinalGradient) {
		this.observationsLongitudinalGradient = observationsLongitudinalGradient;
	}

	public String getObservationsLongitudinalSU() {
		return observationsLongitudinalSU;
	}

	public void setObservationsLongitudinalSU(String observationsLongitudinalSU) {
		this.observationsLongitudinalSU = observationsLongitudinalSU;
	}

}
