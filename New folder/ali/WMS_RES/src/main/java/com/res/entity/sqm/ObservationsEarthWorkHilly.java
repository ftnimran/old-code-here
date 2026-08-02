package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObservationsEarthWorkHilly {
	
	private String observationsEarthWorkHillyLocation;
	private String observationsEarthWorkHillyStable;
	private String observationsEarthWorkHillyAdequateExecuted;
	private String observationsEarthWorkHillyFormationWorthy;
	
	public ObservationsEarthWorkHilly() {
		
	}
	
	public ObservationsEarthWorkHilly(String observationsEarthWorkHillyLocation, String observationsEarthWorkHillyStable, String observationsEarthWorkHillyAdequateExecuted,
			String observationsEarthWorkHillyFormationWorthy) {
		super();
		this.observationsEarthWorkHillyLocation = observationsEarthWorkHillyLocation;
		this.observationsEarthWorkHillyStable = observationsEarthWorkHillyStable;
		this.observationsEarthWorkHillyAdequateExecuted = observationsEarthWorkHillyAdequateExecuted;
		this.observationsEarthWorkHillyFormationWorthy = observationsEarthWorkHillyFormationWorthy;
		
		
	}

	public String getObservationsEarthWorkHillyLocation() {
		return observationsEarthWorkHillyLocation;
	}

	public void setObservationsEarthWorkHillyLocation(String observationsEarthWorkHillyLocation) {
		this.observationsEarthWorkHillyLocation = observationsEarthWorkHillyLocation;
	}

	public String getObservationsEarthWorkHillyStable() {
		return observationsEarthWorkHillyStable;
	}

	public void setObservationsEarthWorkHillyStable(String observationsEarthWorkHillyStable) {
		this.observationsEarthWorkHillyStable = observationsEarthWorkHillyStable;
	}

	public String getObservationsEarthWorkHillyAdequateExecuted() {
		return observationsEarthWorkHillyAdequateExecuted;
	}

	public void setObservationsEarthWorkHillyAdequateExecuted(String observationsEarthWorkHillyAdequateExecuted) {
		this.observationsEarthWorkHillyAdequateExecuted = observationsEarthWorkHillyAdequateExecuted;
	}

	public String getObservationsEarthWorkHillyFormationWorthy() {
		return observationsEarthWorkHillyFormationWorthy;
	}

	public void setObservationsEarthWorkHillyFormationWorthy(String observationsEarthWorkHillyFormationWorthy) {
		this.observationsEarthWorkHillyFormationWorthy = observationsEarthWorkHillyFormationWorthy;
	}

}
