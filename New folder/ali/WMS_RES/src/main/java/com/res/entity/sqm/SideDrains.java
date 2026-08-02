package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SideDrains {
	
	private SideDrainsObservations sideDrainsObservations;
	private SideDrainsCatchGrading sideDrainsCatchGrading;
	
	public SideDrains() {
		
	}
	
	public SideDrains(SideDrainsObservations sideDrainsObservations,  SideDrainsCatchGrading sideDrainsCatchGrading) {
		super();
		this.sideDrainsObservations = sideDrainsObservations;
		this.sideDrainsCatchGrading = sideDrainsCatchGrading;
		
	}
	
	public SideDrainsObservations getSideDrainsObservations() {
		return sideDrainsObservations;
	}
	public void setSideDrainsObservations(SideDrainsObservations sideDrainsObservations) {
		this.sideDrainsObservations = sideDrainsObservations;
	}

	public SideDrainsCatchGrading getSideDrainsCatchGrading() {
		return sideDrainsCatchGrading;
	}

	public void setSideDrainsCatchGrading(SideDrainsCatchGrading sideDrainsCatchGrading) {
		this.sideDrainsCatchGrading = sideDrainsCatchGrading;
	}
	

}
