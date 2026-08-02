package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RoadFurnitureMarkings {
	private RoadFurnitureObservations roadFurnitureObservations;
	private RoadFurnitureQualityRoad roadFurnitureQualityRoad;
	private RoadFurnitureMarkingsGrading roadFurnitureMarkingsGrading;
	
	public RoadFurnitureMarkings() {
		
	}
	
	public RoadFurnitureMarkings(RoadFurnitureObservations roadFurnitureObservations, RoadFurnitureQualityRoad roadFurnitureQualityRoad, RoadFurnitureMarkingsGrading roadFurnitureMarkingsGrading) {
		super();
		this.roadFurnitureObservations = roadFurnitureObservations;
		this.roadFurnitureQualityRoad = roadFurnitureQualityRoad;
		this.roadFurnitureMarkingsGrading = roadFurnitureMarkingsGrading;
		
	}

	public RoadFurnitureObservations getRoadFurnitureObservations() {
		return roadFurnitureObservations;
	}

	public void setRoadFurnitureObservations(RoadFurnitureObservations roadFurnitureObservations) {
		this.roadFurnitureObservations = roadFurnitureObservations;
	}

	public RoadFurnitureQualityRoad getRoadFurnitureQualityRoad() {
		return roadFurnitureQualityRoad;
	}

	public void setRoadFurnitureQualityRoad(RoadFurnitureQualityRoad roadFurnitureQualityRoad) {
		this.roadFurnitureQualityRoad = roadFurnitureQualityRoad;
	}

	public RoadFurnitureMarkingsGrading getRoadFurnitureMarkingsGrading() {
		return roadFurnitureMarkingsGrading;
	}

	public void setRoadFurnitureMarkingsGrading(RoadFurnitureMarkingsGrading roadFurnitureMarkingsGrading) {
		this.roadFurnitureMarkingsGrading = roadFurnitureMarkingsGrading;
	}

	

}
