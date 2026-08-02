package com.res.entity.sarovar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObservationsQoWEarthWork {
	
	private ObservationsQoMaterialForEmbankment observationsQoMaterialForEmbankment;
	private ObservationsQoWEarthWorkGrading observationsQoWEarthWorkGrading;
	private ObservationsWorkmanship observationsWorkmanship;
	private ObservationsWorkmanshipGrading observationsWorkmanshipGrading;
	private ObservationsSlide observationsSlide;
	
public ObservationsQoWEarthWork() {
		
	}
	
	
	public ObservationsQoWEarthWork(ObservationsQoMaterialForEmbankment observationsQoMaterialForEmbankment, ObservationsQoWEarthWorkGrading observationsQoWEarthWorkGrading, 
			ObservationsWorkmanship observationsWorkmanship,ObservationsWorkmanshipGrading observationsWorkmanshipGrading ,ObservationsSlide observationsSlide) {
		super();
		this.observationsQoMaterialForEmbankment = observationsQoMaterialForEmbankment;
		this.observationsQoWEarthWorkGrading = observationsQoWEarthWorkGrading;
		this.observationsWorkmanship = observationsWorkmanship;
		this.observationsWorkmanshipGrading = observationsWorkmanshipGrading;
		this.observationsSlide = observationsSlide;
		
		
	}


	public ObservationsQoMaterialForEmbankment getObservationsQoMaterialForEmbankment() {
		return observationsQoMaterialForEmbankment;
	}


	public void setObservationsQoMaterialForEmbankment(
			ObservationsQoMaterialForEmbankment observationsQoMaterialForEmbankment) {
		this.observationsQoMaterialForEmbankment = observationsQoMaterialForEmbankment;
	}


	public ObservationsQoWEarthWorkGrading getObservationsQoWEarthWorkGrading() {
		return observationsQoWEarthWorkGrading;
	}


	public void setObservationsQoWEarthWorkGrading(ObservationsQoWEarthWorkGrading observationsQoWEarthWorkGrading) {
		this.observationsQoWEarthWorkGrading = observationsQoWEarthWorkGrading;
	}


	public ObservationsWorkmanship getObservationsWorkmanship() {
		return observationsWorkmanship;
	}


	public void setObservationsWorkmanship(ObservationsWorkmanship observationsWorkmanship) {
		this.observationsWorkmanship = observationsWorkmanship;
	}


	public ObservationsWorkmanshipGrading getObservationsWorkmanshipGrading() {
		return observationsWorkmanshipGrading;
	}


	public void setObservationsWorkmanshipGrading(ObservationsWorkmanshipGrading observationsWorkmanshipGrading) {
		this.observationsWorkmanshipGrading = observationsWorkmanshipGrading;
	}


	public ObservationsSlide getObservationsSlide() {
		return observationsSlide;
	}


	public void setObservationsSlide(ObservationsSlide observationsSlide) {
		this.observationsSlide = observationsSlide;
	}

}
