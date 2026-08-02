package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RoadFurnitureObservations {
	
	private String roadFurnitureMainBoardFixed;
	
	public RoadFurnitureObservations() {
		
	}
	
	public RoadFurnitureObservations(String roadFurnitureMainBoardFixed) {
		super();
		this.roadFurnitureMainBoardFixed = roadFurnitureMainBoardFixed;
		
		
	}

	public String getRoadFurnitureMainBoardFixed() {
		return roadFurnitureMainBoardFixed;
	}

	public void setRoadFurnitureMainBoardFixed(String roadFurnitureMainBoardFixed) {
		this.roadFurnitureMainBoardFixed = roadFurnitureMainBoardFixed;
	}

}
