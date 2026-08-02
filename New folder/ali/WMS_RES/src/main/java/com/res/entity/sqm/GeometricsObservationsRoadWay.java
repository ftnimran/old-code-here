package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GeometricsObservationsRoadWay {
	
	private String geometricsObservationsRoadWayRefRD;
	private String geometricsObservationsRoadWayRoadwayWidth;
	private String geometricsObservationsRoadWayCarriageWayWidth;
	private String geometricsObservationsRoadWayCamberIn;
	
	public GeometricsObservationsRoadWay() {
		
	}
	
	public GeometricsObservationsRoadWay(String geometricsObservationsRoadWayRefRD, String geometricsObservationsRoadWayRoadwayWidth, String geometricsObservationsRoadWayCarriageWayWidth,
			String geometricsObservationsRoadWayCamberIn) {
		super();
		this.geometricsObservationsRoadWayRefRD = geometricsObservationsRoadWayRefRD;
		this.geometricsObservationsRoadWayRoadwayWidth = geometricsObservationsRoadWayRoadwayWidth;
		this.geometricsObservationsRoadWayCarriageWayWidth = geometricsObservationsRoadWayCarriageWayWidth;
		this.geometricsObservationsRoadWayCamberIn = geometricsObservationsRoadWayCamberIn;
		
		
	}
	
	
	
	public String getGeometricsObservationsRoadWayRefRD() {
		return geometricsObservationsRoadWayRefRD;
	}
	public void setGeometricsObservationsRoadWayRefRD(String geometricsObservationsRoadWayRefRD) {
		this.geometricsObservationsRoadWayRefRD = geometricsObservationsRoadWayRefRD;
	}
	public String getGeometricsObservationsRoadWayRoadwayWidth() {
		return geometricsObservationsRoadWayRoadwayWidth;
	}
	public void setGeometricsObservationsRoadWayRoadwayWidth(String geometricsObservationsRoadWayRoadwayWidth) {
		this.geometricsObservationsRoadWayRoadwayWidth = geometricsObservationsRoadWayRoadwayWidth;
	}
	public String getGeometricsObservationsRoadWayCarriageWayWidth() {
		return geometricsObservationsRoadWayCarriageWayWidth;
	}
	public void setGeometricsObservationsRoadWayCarriageWayWidth(String geometricsObservationsRoadWayCarriageWayWidth) {
		this.geometricsObservationsRoadWayCarriageWayWidth = geometricsObservationsRoadWayCarriageWayWidth;
	}
	public String getGeometricsObservationsRoadWayCamberIn() {
		return geometricsObservationsRoadWayCamberIn;
	}
	public void setGeometricsObservationsRoadWayCamberIn(String geometricsObservationsRoadWayCamberIn) {
		this.geometricsObservationsRoadWayCamberIn = geometricsObservationsRoadWayCamberIn;
	}

}
