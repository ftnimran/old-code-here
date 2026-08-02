package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CrossDrainageWorksObservations {
	
	private String crossDrainageWorksRD;
	private String crossDrainageWorksTypeofCD;
	private String crossDrainageWorksQoMAcceptable;
	private String crossDrainageWorksWorkMAcceptable;
	
	public CrossDrainageWorksObservations() {
		
	}
	
	public CrossDrainageWorksObservations(String crossDrainageWorksRD, String crossDrainageWorksTypeofCD, String crossDrainageWorksQoMAcceptable,
			String crossDrainageWorksWorkMAcceptable) {
		super();
		this.crossDrainageWorksRD = crossDrainageWorksRD;
		this.crossDrainageWorksTypeofCD = crossDrainageWorksTypeofCD;
		this.crossDrainageWorksQoMAcceptable = crossDrainageWorksQoMAcceptable;
		this.crossDrainageWorksWorkMAcceptable = crossDrainageWorksWorkMAcceptable;
		
		
	}
	
	public String getCrossDrainageWorksRD() {
		return crossDrainageWorksRD;
	}
	public void setCrossDrainageWorksRD(String crossDrainageWorksRD) {
		this.crossDrainageWorksRD = crossDrainageWorksRD;
	}
	public String getCrossDrainageWorksTypeofCD() {
		return crossDrainageWorksTypeofCD;
	}
	public void setCrossDrainageWorksTypeofCD(String crossDrainageWorksTypeofCD) {
		this.crossDrainageWorksTypeofCD = crossDrainageWorksTypeofCD;
	}
	public String getCrossDrainageWorksQoMAcceptable() {
		return crossDrainageWorksQoMAcceptable;
	}
	public void setCrossDrainageWorksQoMAcceptable(String crossDrainageWorksQoMAcceptable) {
		this.crossDrainageWorksQoMAcceptable = crossDrainageWorksQoMAcceptable;
	}
	public String getCrossDrainageWorksWorkMAcceptable() {
		return crossDrainageWorksWorkMAcceptable;
	}
	public void setCrossDrainageWorksWorkMAcceptable(String crossDrainageWorksWorkMAcceptable) {
		this.crossDrainageWorksWorkMAcceptable = crossDrainageWorksWorkMAcceptable;
	}

}
