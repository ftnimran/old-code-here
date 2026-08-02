package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SideDrainsObservations {
	
	private String sideDrainsObservationsReference;
	private String sideDrainsObservationsRD;
	private String sideDrainsObservationsGeneralQuality;
	private String sideDrainsObservationsSideDrains;
	
	public SideDrainsObservations() {
		
	}
	public SideDrainsObservations(String sideDrainsObservationsReference, String sideDrainsObservationsRD, String sideDrainsObservationsGeneralQuality,
			String sideDrainsObservationsSideDrains) {
		super();
		this.sideDrainsObservationsReference = sideDrainsObservationsReference;
		this.sideDrainsObservationsRD = sideDrainsObservationsRD;
		this.sideDrainsObservationsGeneralQuality = sideDrainsObservationsGeneralQuality;
		this.sideDrainsObservationsSideDrains = sideDrainsObservationsSideDrains;
		
		
	}
	public String getSideDrainsObservationsReference() {
		return sideDrainsObservationsReference;
	}
	public void setSideDrainsObservationsReference(String sideDrainsObservationsReference) {
		this.sideDrainsObservationsReference = sideDrainsObservationsReference;
	}
	public String getSideDrainsObservationsRD() {
		return sideDrainsObservationsRD;
	}
	public void setSideDrainsObservationsRD(String sideDrainsObservationsRD) {
		this.sideDrainsObservationsRD = sideDrainsObservationsRD;
	}
	public String getSideDrainsObservationsGeneralQuality() {
		return sideDrainsObservationsGeneralQuality;
	}
	public void setSideDrainsObservationsGeneralQuality(String sideDrainsObservationsGeneralQuality) {
		this.sideDrainsObservationsGeneralQuality = sideDrainsObservationsGeneralQuality;
	}
	public String getSideDrainsObservationsSideDrains() {
		return sideDrainsObservationsSideDrains;
	}
	public void setSideDrainsObservationsSideDrains(String sideDrainsObservationsSideDrains) {
		this.sideDrainsObservationsSideDrains = sideDrainsObservationsSideDrains;
	}

}
