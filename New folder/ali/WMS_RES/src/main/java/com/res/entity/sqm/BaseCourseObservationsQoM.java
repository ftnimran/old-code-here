package com.res.entity.sqm;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BaseCourseObservationsQoM {
	private String baseCourseObservationsQoMLocation;
	private String baseCourseObservationsQoMConfirmsGrading;
	private String baseCourseObservationsQoMSuitable;
	private String baseCourseObservationsQoMCompaction;
	private String baseCourseObservationsQoMObservedThickness;
	private String baseCourseObservationsQoMPrescribedThickness;
	private String baseCourseObservationsQoMCBR;
	
	public BaseCourseObservationsQoM() {
		
	}
	
	public BaseCourseObservationsQoM(String baseCourseObservationsQoMLocation, String baseCourseObservationsQoMConfirmsGrading, String baseCourseObservationsQoMSuitable,
			String baseCourseObservationsQoMCompaction, String baseCourseObservationsQoMObservedThickness,String baseCourseObservationsQoMPrescribedThickness,String baseCourseObservationsQoMCBR) {
		super();
		this.baseCourseObservationsQoMLocation = baseCourseObservationsQoMLocation;
		this.baseCourseObservationsQoMConfirmsGrading = baseCourseObservationsQoMConfirmsGrading;
		this.baseCourseObservationsQoMSuitable = baseCourseObservationsQoMSuitable;
		this.baseCourseObservationsQoMCompaction = baseCourseObservationsQoMCompaction;
		this.baseCourseObservationsQoMObservedThickness = baseCourseObservationsQoMObservedThickness;
		this.baseCourseObservationsQoMPrescribedThickness = baseCourseObservationsQoMPrescribedThickness;
		this.baseCourseObservationsQoMCBR = baseCourseObservationsQoMCBR;
		
	}
	
	public String getBaseCourseObservationsQoMLocation() {
		return baseCourseObservationsQoMLocation;
	}
	public void setBaseCourseObservationsQoMLocation(String baseCourseObservationsQoMLocation) {
		this.baseCourseObservationsQoMLocation = baseCourseObservationsQoMLocation;
	}
	public String getBaseCourseObservationsQoMConfirmsGrading() {
		return baseCourseObservationsQoMConfirmsGrading;
	}
	public void setBaseCourseObservationsQoMConfirmsGrading(String baseCourseObservationsQoMConfirmsGrading) {
		this.baseCourseObservationsQoMConfirmsGrading = baseCourseObservationsQoMConfirmsGrading;
	}
	public String getBaseCourseObservationsQoMSuitable() {
		return baseCourseObservationsQoMSuitable;
	}
	public void setBaseCourseObservationsQoMSuitable(String baseCourseObservationsQoMSuitable) {
		this.baseCourseObservationsQoMSuitable = baseCourseObservationsQoMSuitable;
	}
	public String getBaseCourseObservationsQoMCompaction() {
		return baseCourseObservationsQoMCompaction;
	}
	public void setBaseCourseObservationsQoMCompaction(String baseCourseObservationsQoMCompaction) {
		this.baseCourseObservationsQoMCompaction = baseCourseObservationsQoMCompaction;
	}
	
	

	public String getBaseCourseObservationsQoMPrescribedThickness() {
		return baseCourseObservationsQoMPrescribedThickness;
	}
	public void setBaseCourseObservationsQoMPrescribedThickness(String baseCourseObservationsQoMPrescribedThickness) {
		this.baseCourseObservationsQoMPrescribedThickness = baseCourseObservationsQoMPrescribedThickness;
	}
	public String getBaseCourseObservationsQoMCBR() {
		return baseCourseObservationsQoMCBR;
	}
	public void setBaseCourseObservationsQoMCBR(String baseCourseObservationsQoMCBR) {
		this.baseCourseObservationsQoMCBR = baseCourseObservationsQoMCBR;
	}

	public String getBaseCourseObservationsQoMObservedThickness() {
		return baseCourseObservationsQoMObservedThickness;
	}

	public void setBaseCourseObservationsQoMObservedThickness(String baseCourseObservationsQoMObservedThickness) {
		this.baseCourseObservationsQoMObservedThickness = baseCourseObservationsQoMObservedThickness;
	}

}
