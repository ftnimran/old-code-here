package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObservationsQoMaterialForEmbankment {
	
	private String observationsQoMEmbankmentLocation;
	private String observationsQoMEmbankmentVisual;
	private String observationsQoMEmbankmentAcceptable;
	
	public ObservationsQoMaterialForEmbankment() {
		
	}
	
	
	public ObservationsQoMaterialForEmbankment(String observationsQoMEmbankmentLocation, String observationsQoMEmbankmentVisual, String observationsQoMEmbankmentAcceptable) {
		super();
		this.observationsQoMEmbankmentLocation = observationsQoMEmbankmentLocation;
		this.observationsQoMEmbankmentVisual = observationsQoMEmbankmentVisual;
		this.observationsQoMEmbankmentAcceptable = observationsQoMEmbankmentAcceptable;
		
		
	}

	public String getObservationsQoMEmbankmentLocation() {
		return observationsQoMEmbankmentLocation;
	}

	public void setObservationsQoMEmbankmentLocation(String observationsQoMEmbankmentLocation) {
		this.observationsQoMEmbankmentLocation = observationsQoMEmbankmentLocation;
	}

	public String getObservationsQoMEmbankmentVisual() {
		return observationsQoMEmbankmentVisual;
	}

	public void setObservationsQoMEmbankmentVisual(String observationsQoMEmbankmentVisual) {
		this.observationsQoMEmbankmentVisual = observationsQoMEmbankmentVisual;
	}

	public String getObservationsQoMEmbankmentAcceptable() {
		return observationsQoMEmbankmentAcceptable;
	}

	public void setObservationsQoMEmbankmentAcceptable(String observationsQoMEmbankmentAcceptable) {
		this.observationsQoMEmbankmentAcceptable = observationsQoMEmbankmentAcceptable;
	}

}
