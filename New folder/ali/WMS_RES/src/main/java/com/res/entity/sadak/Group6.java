package com.res.entity.sadak;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group6 {
	private String roadFurniture;

	
	public Group6() {}
	
	public Group6(String roadFurniture) {
		super();
		this.roadFurniture = roadFurniture;
	}

	public String getRoadFurniture() {
		return roadFurniture;
	}

	public void setRoadFurniture(String roadFurniture) {
		this.roadFurniture = roadFurniture;
	}
}
