package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RoadFurnitureQualityRoad {
	
	private String roadFurnitureQualityRoadLogoFixed;
	private String roadFurnitureQualityRoad200mStoneFixed;
	private String roadFurnitureQualityRoad1kmStoneFixed;
	private String roadFurnitureQualityRoad1kmStoneCurves;
	private String roadFurnitureQualityRoadSignage;
	private String roadFurnitureQualityRoadGrade;
	private String roadFurnitureQualityRoadGradeReasons;
	 
	 public RoadFurnitureQualityRoad() {
		 
	 }
	 
	 public RoadFurnitureQualityRoad(String roadFurnitureQualityRoadLogoFixed, String roadFurnitureQualityRoad200mStoneFixed, String roadFurnitureQualityRoad1kmStoneFixed,
				String roadFurnitureQualityRoad1kmStoneCurves, String roadFurnitureQualityRoadSignage,String roadFurnitureQualityRoadGrade,String roadFurnitureQualityRoadGradeReasons) {
			super();
			this.roadFurnitureQualityRoadLogoFixed = roadFurnitureQualityRoadLogoFixed;
			this.roadFurnitureQualityRoad200mStoneFixed = roadFurnitureQualityRoad200mStoneFixed;
			this.roadFurnitureQualityRoad1kmStoneFixed = roadFurnitureQualityRoad1kmStoneFixed;
			this.roadFurnitureQualityRoad1kmStoneCurves = roadFurnitureQualityRoad1kmStoneCurves;
			this.roadFurnitureQualityRoadSignage = roadFurnitureQualityRoadSignage;
			this.roadFurnitureQualityRoadGrade = roadFurnitureQualityRoadGrade;
			this.roadFurnitureQualityRoadGradeReasons = roadFurnitureQualityRoadGradeReasons;
			
		}

	public String getRoadFurnitureQualityRoadLogoFixed() {
		return roadFurnitureQualityRoadLogoFixed;
	}

	public void setRoadFurnitureQualityRoadLogoFixed(String roadFurnitureQualityRoadLogoFixed) {
		this.roadFurnitureQualityRoadLogoFixed = roadFurnitureQualityRoadLogoFixed;
	}

	public String getRoadFurnitureQualityRoad200mStoneFixed() {
		return roadFurnitureQualityRoad200mStoneFixed;
	}

	public void setRoadFurnitureQualityRoad200mStoneFixed(String roadFurnitureQualityRoad200mStoneFixed) {
		this.roadFurnitureQualityRoad200mStoneFixed = roadFurnitureQualityRoad200mStoneFixed;
	}

	public String getRoadFurnitureQualityRoad1kmStoneFixed() {
		return roadFurnitureQualityRoad1kmStoneFixed;
	}

	public void setRoadFurnitureQualityRoad1kmStoneFixed(String roadFurnitureQualityRoad1kmStoneFixed) {
		this.roadFurnitureQualityRoad1kmStoneFixed = roadFurnitureQualityRoad1kmStoneFixed;
	}

	public String getRoadFurnitureQualityRoad1kmStoneCurves() {
		return roadFurnitureQualityRoad1kmStoneCurves;
	}

	public void setRoadFurnitureQualityRoad1kmStoneCurves(String roadFurnitureQualityRoad1kmStoneCurves) {
		this.roadFurnitureQualityRoad1kmStoneCurves = roadFurnitureQualityRoad1kmStoneCurves;
	}

	public String getRoadFurnitureQualityRoadSignage() {
		return roadFurnitureQualityRoadSignage;
	}

	public void setRoadFurnitureQualityRoadSignage(String roadFurnitureQualityRoadSignage) {
		this.roadFurnitureQualityRoadSignage = roadFurnitureQualityRoadSignage;
	}

	public String getRoadFurnitureQualityRoadGrade() {
		return roadFurnitureQualityRoadGrade;
	}

	public void setRoadFurnitureQualityRoadGrade(String roadFurnitureQualityRoadGrade) {
		this.roadFurnitureQualityRoadGrade = roadFurnitureQualityRoadGrade;
	}

	public String getRoadFurnitureQualityRoadGradeReasons() {
		return roadFurnitureQualityRoadGradeReasons;
	}

	public void setRoadFurnitureQualityRoadGradeReasons(String roadFurnitureQualityRoadGradeReasons) {
		this.roadFurnitureQualityRoadGradeReasons = roadFurnitureQualityRoadGradeReasons;
	}

}
