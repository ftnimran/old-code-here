package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObservationsWorkmanship {
	
	
	private String observationsWorkmanshipLocation;
	private String observationsWorkmanshipMdd;
	private String observationsWorkmanshipFieldContent;
	private String observationsWorkmanshipFieldDensity;
	private String observationsWorkmanshipDryDensity;
	private String observationsWorkmanshipCompaction;
	
	public ObservationsWorkmanship() {
		
	}
	
	public ObservationsWorkmanship(String observationsWorkmanshipLocation, String observationsWorkmanshipMdd, String observationsWorkmanshipFieldContent,
			String observationsWorkmanshipFieldDensity, String observationsWorkmanshipDryDensity,String observationsWorkmanshipCompaction) {
		super();
		this.observationsWorkmanshipLocation = observationsWorkmanshipLocation;
		this.observationsWorkmanshipMdd = observationsWorkmanshipMdd;
		this.observationsWorkmanshipFieldContent = observationsWorkmanshipFieldContent;
		this.observationsWorkmanshipFieldDensity = observationsWorkmanshipFieldDensity;
		this.observationsWorkmanshipDryDensity = observationsWorkmanshipDryDensity;
		this.observationsWorkmanshipCompaction = observationsWorkmanshipCompaction;
		
	}
	
	
	public String getObservationsWorkmanshipLocation() {
		return observationsWorkmanshipLocation;
	}
	public void setObservationsWorkmanshipLocation(String observationsWorkmanshipLocation) {
		this.observationsWorkmanshipLocation = observationsWorkmanshipLocation;
	}
	public String getObservationsWorkmanshipMdd() {
		return observationsWorkmanshipMdd;
	}
	public void setObservationsWorkmanshipMdd(String observationsWorkmanshipMdd) {
		this.observationsWorkmanshipMdd = observationsWorkmanshipMdd;
	}
	public String getObservationsWorkmanshipFieldContent() {
		return observationsWorkmanshipFieldContent;
	}
	public void setObservationsWorkmanshipFieldContent(String observationsWorkmanshipFieldContent) {
		this.observationsWorkmanshipFieldContent = observationsWorkmanshipFieldContent;
	}

	public String getObservationsWorkmanshipFieldDensity() {
		return observationsWorkmanshipFieldDensity;
	}

	public void setObservationsWorkmanshipFieldDensity(String observationsWorkmanshipFieldDensity) {
		this.observationsWorkmanshipFieldDensity = observationsWorkmanshipFieldDensity;
	}

	public String getObservationsWorkmanshipDryDensity() {
		return observationsWorkmanshipDryDensity;
	}

	public void setObservationsWorkmanshipDryDensity(String observationsWorkmanshipDryDensity) {
		this.observationsWorkmanshipDryDensity = observationsWorkmanshipDryDensity;
	}

	public String getObservationsWorkmanshipCompaction() {
		return observationsWorkmanshipCompaction;
	}

	public void setObservationsWorkmanshipCompaction(String observationsWorkmanshipCompaction) {
		this.observationsWorkmanshipCompaction = observationsWorkmanshipCompaction;
	}
	

}
