package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GeometricsGrading {
	
	 private String geometricsGradingGrade;
     private String geometricsGradingReasons;
     
     public GeometricsGrading() {
    	 
     }
     
     public GeometricsGrading(String geometricsGradingGrade, String geometricsGradingReasons) {
 		super();
 		this.geometricsGradingGrade = geometricsGradingGrade;
 		this.geometricsGradingReasons = geometricsGradingReasons;
 		
 		
 	}
 	
     
	public String getGeometricsGradingGrade() {
		return geometricsGradingGrade;
	}
	public void setGeometricsGradingGrade(String geometricsGradingGrade) {
		this.geometricsGradingGrade = geometricsGradingGrade;
	}
	public String getGeometricsGradingReasons() {
		return geometricsGradingReasons;
	}
	public void setGeometricsGradingReasons(String geometricsGradingReasons) {
		this.geometricsGradingReasons = geometricsGradingReasons;
	}

}
