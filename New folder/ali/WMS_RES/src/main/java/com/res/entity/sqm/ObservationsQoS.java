package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObservationsQoS {
	
	private String observationsQoSRD;
	private String observationsQoSThickness;
	private String observationsQoSQoM;
	private String observationsQoSAcceptable;
	private String observationsQoSBaseCourse;
	
	public ObservationsQoS() {
		
	}
	
	public ObservationsQoS(String observationsQoSRD, String observationsQoSThickness, String observationsQoSQoM,
			String observationsQoSAcceptable, String observationsQoSBaseCourse) {
		super();
		this.observationsQoSRD = observationsQoSRD;
		this.observationsQoSThickness = observationsQoSThickness;
		this.observationsQoSQoM = observationsQoSQoM;
		this.observationsQoSAcceptable = observationsQoSAcceptable;
		this.observationsQoSBaseCourse = observationsQoSBaseCourse;
		
	}

	public String getObservationsQoSRD() {
		return observationsQoSRD;
	}

	public void setObservationsQoSRD(String observationsQoSRD) {
		this.observationsQoSRD = observationsQoSRD;
	}

	
	

	public String getObservationsQoSQoM() {
		return observationsQoSQoM;
	}

	public void setObservationsQoSQoM(String observationsQoSQoM) {
		this.observationsQoSQoM = observationsQoSQoM;
	}

	public String getObservationsQoSAcceptable() {
		return observationsQoSAcceptable;
	}

	public void setObservationsQoSAcceptable(String observationsQoSAcceptable) {
		this.observationsQoSAcceptable = observationsQoSAcceptable;
	}

	public String getObservationsQoSBaseCourse() {
		return observationsQoSBaseCourse;
	}

	public void setObservationsQoSBaseCourse(String observationsQoSBaseCourse) {
		this.observationsQoSBaseCourse = observationsQoSBaseCourse;
	}

	public String getObservationsQoSThickness() {
		return observationsQoSThickness;
	}

	public void setObservationsQoSThickness(String observationsQoSThickness) {
		this.observationsQoSThickness = observationsQoSThickness;
	}

}
