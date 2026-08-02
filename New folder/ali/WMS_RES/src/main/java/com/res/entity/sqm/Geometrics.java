package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Geometrics {
	
	private GeometricsObservationsRoadWay geometricsObservationsRoadWay;
	private GeometricsObservationsSuper geometricsObservationsSuper;
	private GeometricsGrading geometricsGrading;
	
	public Geometrics() {
		
	}
	
	public Geometrics(GeometricsObservationsRoadWay geometricsObservationsRoadWay, GeometricsObservationsSuper geometricsObservationsSuper, GeometricsGrading geometricsGrading) {
		super();
		this.geometricsObservationsRoadWay = geometricsObservationsRoadWay;
		this.geometricsObservationsSuper = geometricsObservationsSuper;
		this.geometricsGrading = geometricsGrading;
		
	}

	public GeometricsObservationsRoadWay getGeometricsObservationsRoadWay() {
		return geometricsObservationsRoadWay;
	}

	public void setGeometricsObservationsRoadWay(GeometricsObservationsRoadWay geometricsObservationsRoadWay) {
		this.geometricsObservationsRoadWay = geometricsObservationsRoadWay;
	}

	public GeometricsObservationsSuper getGeometricsObservationsSuper() {
		return geometricsObservationsSuper;
	}

	public void setGeometricsObservationsSuper(GeometricsObservationsSuper geometricsObservationsSuper) {
		this.geometricsObservationsSuper = geometricsObservationsSuper;
	}

	public GeometricsGrading getGeometricsGrading() {
		return geometricsGrading;
	}

	public void setGeometricsGrading(GeometricsGrading geometricsGrading) {
		this.geometricsGrading = geometricsGrading;
	}

	

}
