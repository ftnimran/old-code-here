package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GeometricsObservationsSuper {
	
	private String geometricsObservationsSuperRefRD;
	private String geometricsObservationsSuperElevation;
	private String geometricsObservationsSuperExtraWidening;
	
	public GeometricsObservationsSuper() {
		
	}
	
	public GeometricsObservationsSuper(String geometricsObservationsSuperRefRD, String geometricsObservationsSuperElevation, String geometricsObservationsSuperExtraWidening) {
		super();
		this.geometricsObservationsSuperRefRD = geometricsObservationsSuperRefRD;
		this.geometricsObservationsSuperElevation = geometricsObservationsSuperElevation;
		this.geometricsObservationsSuperExtraWidening = geometricsObservationsSuperExtraWidening;
		
		
	}
	
	public String getGeometricsObservationsSuperRefRD() {
		return geometricsObservationsSuperRefRD;
	}
	public void setGeometricsObservationsSuperRefRD(String geometricsObservationsSuperRefRD) {
		this.geometricsObservationsSuperRefRD = geometricsObservationsSuperRefRD;
	}
	public String getGeometricsObservationsSuperElevation() {
		return geometricsObservationsSuperElevation;
	}
	public void setGeometricsObservationsSuperElevation(String geometricsObservationsSuperElevation) {
		this.geometricsObservationsSuperElevation = geometricsObservationsSuperElevation;
	}
	public String getGeometricsObservationsSuperExtraWidening() {
		return geometricsObservationsSuperExtraWidening;
	}
	public void setGeometricsObservationsSuperExtraWidening(String geometricsObservationsSuperExtraWidening) {
		this.geometricsObservationsSuperExtraWidening = geometricsObservationsSuperExtraWidening;
	}
	

}
