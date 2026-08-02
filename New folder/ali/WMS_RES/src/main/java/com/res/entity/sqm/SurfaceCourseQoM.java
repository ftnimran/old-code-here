package com.res.entity.sqm;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SurfaceCourseQoM {
	
	
	private String surfaceCourseQoMLocation;
	private String surfaceCourseQoMThickness;
	private String surfaceCourseQoMThicknessAdequate;
	private String surfaceCourseQoMMatConfirms;
	private String surfaceCourseQoMAdequateCompaction;
	
	public SurfaceCourseQoM() {
		
	}
	
	public SurfaceCourseQoM(String surfaceCourseQoMLocation, String surfaceCourseQoMThickness, String surfaceCourseQoMThicknessAdequate,
			String surfaceCourseQoMMatConfirms, String surfaceCourseQoMAdequateCompaction) {
		super();
		this.surfaceCourseQoMLocation = surfaceCourseQoMLocation;
		this.surfaceCourseQoMThickness = surfaceCourseQoMThickness;
		this.surfaceCourseQoMThicknessAdequate = surfaceCourseQoMThicknessAdequate;
		this.surfaceCourseQoMMatConfirms = surfaceCourseQoMMatConfirms;
		this.surfaceCourseQoMAdequateCompaction = surfaceCourseQoMAdequateCompaction;
		
	}
	
	
	public String getSurfaceCourseQoMLocation() {
		return surfaceCourseQoMLocation;
	}
	public void setSurfaceCourseQoMLocation(String surfaceCourseQoMLocation) {
		this.surfaceCourseQoMLocation = surfaceCourseQoMLocation;
	}
	
	

	public String getSurfaceCourseQoMThicknessAdequate() {
		return surfaceCourseQoMThicknessAdequate;
	}
	public void setSurfaceCourseQoMThicknessAdequate(String surfaceCourseQoMThicknessAdequate) {
		this.surfaceCourseQoMThicknessAdequate = surfaceCourseQoMThicknessAdequate;
	}
	public String getSurfaceCourseQoMMatConfirms() {
		return surfaceCourseQoMMatConfirms;
	}
	public void setSurfaceCourseQoMMatConfirms(String surfaceCourseQoMMatConfirms) {
		this.surfaceCourseQoMMatConfirms = surfaceCourseQoMMatConfirms;
	}
	public String getSurfaceCourseQoMAdequateCompaction() {
		return surfaceCourseQoMAdequateCompaction;
	}
	public void setSurfaceCourseQoMAdequateCompaction(String surfaceCourseQoMAdequateCompaction) {
		this.surfaceCourseQoMAdequateCompaction = surfaceCourseQoMAdequateCompaction;
	}

	public String getSurfaceCourseQoMThickness() {
		return surfaceCourseQoMThickness;
	}

	public void setSurfaceCourseQoMThickness(String surfaceCourseQoMThickness) {
		this.surfaceCourseQoMThickness = surfaceCourseQoMThickness;
	}

}
