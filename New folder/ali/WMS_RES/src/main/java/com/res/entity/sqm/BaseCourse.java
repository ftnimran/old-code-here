package com.res.entity.sqm;

public class BaseCourse {
	
	private BaseCourseObservationsQoM baseCourseObservationsQoM;
	private BaseCourseGrading baseCourseGrading;
	
	public BaseCourse() {
		
	}
	
	public BaseCourse(BaseCourseObservationsQoM baseCourseObservationsQoM, BaseCourseGrading baseCourseGrading) {
		super();
		this.baseCourseObservationsQoM = baseCourseObservationsQoM;
		this.baseCourseGrading = baseCourseGrading;
		
	}
	
	
	public BaseCourseObservationsQoM getBaseCourseObservationsQoM() {
		return baseCourseObservationsQoM;
	}
	public void setBaseCourseObservationsQoM(BaseCourseObservationsQoM baseCourseObservationsQoM) {
		this.baseCourseObservationsQoM = baseCourseObservationsQoM;
	}

	public BaseCourseGrading getBaseCourseGrading() {
		return baseCourseGrading;
	}

	public void setBaseCourseGrading(BaseCourseGrading baseCourseGrading) {
		this.baseCourseGrading = baseCourseGrading;
	}
	
	
	

}
