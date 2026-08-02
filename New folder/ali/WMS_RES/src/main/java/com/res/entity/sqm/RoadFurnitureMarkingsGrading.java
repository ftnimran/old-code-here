package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RoadFurnitureMarkingsGrading {
	
	private String roadFurnitureMarkingsGrade;
	private String roadFurnitureMarkingsGradeReasons;
	
	public RoadFurnitureMarkingsGrading() {
		
	}
	
	public RoadFurnitureMarkingsGrading(String roadFurnitureMarkingsGrade, String roadFurnitureMarkingsGradeReasons) {
		super();
		this.roadFurnitureMarkingsGrade = roadFurnitureMarkingsGrade;
		this.roadFurnitureMarkingsGradeReasons = roadFurnitureMarkingsGradeReasons;
		
		
	}

	public String getRoadFurnitureMarkingsGrade() {
		return roadFurnitureMarkingsGrade;
	}

	public void setRoadFurnitureMarkingsGrade(String roadFurnitureMarkingsGrade) {
		this.roadFurnitureMarkingsGrade = roadFurnitureMarkingsGrade;
	}

	public String getRoadFurnitureMarkingsGradeReasons() {
		return roadFurnitureMarkingsGradeReasons;
	}

	public void setRoadFurnitureMarkingsGradeReasons(String roadFurnitureMarkingsGradeReasons) {
		this.roadFurnitureMarkingsGradeReasons = roadFurnitureMarkingsGradeReasons;
	}

}
