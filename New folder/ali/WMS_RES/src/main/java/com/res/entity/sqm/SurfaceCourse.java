package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SurfaceCourse {
	
	private SurfaceCourseQoM surfaceCourseQoM;
	private String surfaceCourseObservations;
	private SurfaceCourseGrading surfaceCourseGrading;
	
	public SurfaceCourse() {
		
	}
	
	public SurfaceCourse(SurfaceCourseQoM surfaceCourseQoM, String surfaceCourseObservations,  SurfaceCourseGrading surfaceCourseGrading) {
		super();
		this.surfaceCourseQoM = surfaceCourseQoM;
		this.surfaceCourseObservations = surfaceCourseObservations;
		this.surfaceCourseGrading = surfaceCourseGrading;
		
	}

	public SurfaceCourseQoM getSurfaceCourseQoM() {
		return surfaceCourseQoM;
	}

	public void setSurfaceCourseQoM(SurfaceCourseQoM surfaceCourseQoM) {
		this.surfaceCourseQoM = surfaceCourseQoM;
	}

	public String getSurfaceCourseObservations() {
		return surfaceCourseObservations;
	}

	public void setSurfaceCourseObservations(String surfaceCourseObservations) {
		this.surfaceCourseObservations = surfaceCourseObservations;
	}

	public SurfaceCourseGrading getSurfaceCourseGrading() {
		return surfaceCourseGrading;
	}

	public void setSurfaceCourseGrading(SurfaceCourseGrading surfaceCourseGrading) {
		this.surfaceCourseGrading = surfaceCourseGrading;
	}

	

}
