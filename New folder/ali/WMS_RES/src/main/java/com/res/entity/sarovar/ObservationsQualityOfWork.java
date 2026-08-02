package com.res.entity.sarovar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObservationsQualityOfWork {
	
	private ObservationsQoWEarthWork observationsQoWEarthWork;
	private ExternalDrainageSystem externalDrainageSystem;
	private UpstreamProtectionWork upstreamProtectionWork;
	private SurplusingArrangements surplusingArrangements;
	
public ObservationsQualityOfWork() {
		
	}
	
	
	public ObservationsQualityOfWork(ObservationsQoWEarthWork observationsQoWEarthWork, ExternalDrainageSystem externalDrainageSystem, UpstreamProtectionWork upstreamProtectionWork,
			SurplusingArrangements surplusingArrangements) {
		super();
		this.observationsQoWEarthWork = observationsQoWEarthWork;
		this.externalDrainageSystem = externalDrainageSystem;
		this.upstreamProtectionWork = upstreamProtectionWork;
		this.surplusingArrangements = surplusingArrangements;
		
		
	}


	public ObservationsQoWEarthWork getObservationsQoWEarthWork() {
		return observationsQoWEarthWork;
	}


	public void setObservationsQoWEarthWork(ObservationsQoWEarthWork observationsQoWEarthWork) {
		this.observationsQoWEarthWork = observationsQoWEarthWork;
	}


	public ExternalDrainageSystem getExternalDrainageSystem() {
		return externalDrainageSystem;
	}


	public void setExternalDrainageSystem(ExternalDrainageSystem externalDrainageSystem) {
		this.externalDrainageSystem = externalDrainageSystem;
	}


	public UpstreamProtectionWork getUpstreamProtectionWork() {
		return upstreamProtectionWork;
	}


	public void setUpstreamProtectionWork(UpstreamProtectionWork upstreamProtectionWork) {
		this.upstreamProtectionWork = upstreamProtectionWork;
	}


	public SurplusingArrangements getSurplusingArrangements() {
		return surplusingArrangements;
	}


	public void setSurplusingArrangements(SurplusingArrangements surplusingArrangements) {
		this.surplusingArrangements = surplusingArrangements;
	}
	

}
