package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObservationsQoWEarthWork {
	
	private ObservationsQoMaterialForEmbankment observationsQoMaterialForEmbankment;
	private ObservationsQoWEarthWorkGrading observationsQoWEarthWorkGrading;
	private ObservationsWorkmanshipGrading observationsWorkmanshipGrading;
	private ObservationsLongitudinalGrading observationsLongitudinalGrading;
	private ObservationsWorkmanship observationsWorkmanship;
	
	private ObservationsSlide observationsSlide;
	private ObservationsEarthWorkHilly observationsEarthWorkHilly;
	private ObservationsLongitudinal observationsLongitudinal;
	
	public ObservationsQoWEarthWork() {
		
	}
	
	public ObservationsQoWEarthWork(ObservationsQoMaterialForEmbankment observationsQoMaterialForEmbankment, ObservationsQoWEarthWorkGrading observationsQoWEarthWorkGrading,ObservationsWorkmanshipGrading observationsWorkmanshipGrading,ObservationsLongitudinalGrading observationsLongitudinalGrading, ObservationsWorkmanship observationsWorkmanship,
			ObservationsSlide observationsSlide, ObservationsEarthWorkHilly observationsEarthWorkHilly,ObservationsLongitudinal observationsLongitudinal) {
		super();
		this.observationsQoMaterialForEmbankment = observationsQoMaterialForEmbankment;
		this.observationsQoWEarthWorkGrading = observationsQoWEarthWorkGrading;
		this.observationsWorkmanshipGrading = observationsWorkmanshipGrading;
		this.observationsLongitudinalGrading = observationsLongitudinalGrading;
		this.observationsWorkmanship = observationsWorkmanship;
		this.observationsSlide = observationsSlide;
		this.observationsEarthWorkHilly = observationsEarthWorkHilly;
		this.observationsLongitudinal = observationsLongitudinal;
		
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

	public ObservationsWorkmanshipGrading getObservationsWorkmanshipGrading() {
		return observationsWorkmanshipGrading;
	}

	public void setObservationsWorkmanshipGrading(ObservationsWorkmanshipGrading observationsWorkmanshipGrading) {
		this.observationsWorkmanshipGrading = observationsWorkmanshipGrading;
	}

	public ObservationsLongitudinalGrading getObservationsLongitudinalGrading() {
		return observationsLongitudinalGrading;
	}

	public void setObservationsLongitudinalGrading(ObservationsLongitudinalGrading observationsLongitudinalGrading) {
		this.observationsLongitudinalGrading = observationsLongitudinalGrading;
	}

	public ObservationsWorkmanship getObservationsWorkmanship() {
		return observationsWorkmanship;
	}
	public void setObservationsWorkmanship(ObservationsWorkmanship observationsWorkmanship) {
		this.observationsWorkmanship = observationsWorkmanship;
	}
	public ObservationsSlide getObservationsSlide() {
		return observationsSlide;
	}
	public void setObservationsSlide(ObservationsSlide observationsSlide) {
		this.observationsSlide = observationsSlide;
	}
	public ObservationsEarthWorkHilly getObservationsEarthWorkHilly() {
		return observationsEarthWorkHilly;
	}
	public void setObservationsEarthWorkHilly(ObservationsEarthWorkHilly observationsEarthWorkHilly) {
		this.observationsEarthWorkHilly = observationsEarthWorkHilly;
	}
	public ObservationsLongitudinal getObservationsLongitudinal() {
		return observationsLongitudinal;
	}
	public void setObservationsLongitudinal(ObservationsLongitudinal observationsLongitudinal) {
		this.observationsLongitudinal = observationsLongitudinal;
	}
	
	

}
