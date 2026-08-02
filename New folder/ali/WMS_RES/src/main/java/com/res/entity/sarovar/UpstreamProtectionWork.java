package com.res.entity.sarovar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UpstreamProtectionWork {
	
	private BaseCourseObservationsQoM  baseCourseObservationsQoM; 
	private String observations;
	private UpStreamGrading upStreamGrading;
	
public UpstreamProtectionWork() {
		
	}
	
	
	public UpstreamProtectionWork(BaseCourseObservationsQoM  baseCourseObservationsQoM, String observations, UpStreamGrading upStreamGrading) {
		super();
		this.baseCourseObservationsQoM = baseCourseObservationsQoM;
		this.observations = observations;
		this.upStreamGrading = upStreamGrading;
		
		
	}


	public BaseCourseObservationsQoM getBaseCourseObservationsQoM() {
		return baseCourseObservationsQoM;
	}


	public void setBaseCourseObservationsQoM(BaseCourseObservationsQoM baseCourseObservationsQoM) {
		this.baseCourseObservationsQoM = baseCourseObservationsQoM;
	}


	public String getObservations() {
		return observations;
	}


	public void setObservations(String observations) {
		this.observations = observations;
	}


	public UpStreamGrading getUpStreamGrading() {
		return upStreamGrading;
	}


	public void setUpStreamGrading(UpStreamGrading upStreamGrading) {
		this.upStreamGrading = upStreamGrading;
	}
	
	
	
	
	
	
	

}
