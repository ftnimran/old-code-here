package com.res.entity.singlevillagepipped;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OverallGradingAndRemarks {
	 public OverallGradingAndRemarks(String overallGrading, String overallRemarks) {
		super();
		this.overallGrading = overallGrading;
		this.overallRemarks = overallRemarks;
	}

	private String overallGrading;
	 private String overallRemarks;


	 // Getter Methods 

	 public OverallGradingAndRemarks() {
		 super();
		// TODO Auto-generated constructor stub
	}

	public String getOverallGrading() {
	  return overallGrading;
	 }

	 public String getOverallRemarks() {
	  return overallRemarks;
	 }

	 // Setter Methods 

	 public void setOverallGrading(String overallGrading) {
	  this.overallGrading = overallGrading;
	 }

	 public void setOverallRemarks(String overallRemarks) {
	  this.overallRemarks = overallRemarks;
	 }
	}