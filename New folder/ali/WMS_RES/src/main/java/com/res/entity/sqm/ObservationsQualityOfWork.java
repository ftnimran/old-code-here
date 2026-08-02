package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObservationsQualityOfWork {
	private ObservationsQoWEarthWork observationsQoWEarthWork;
	private BaseCourse baseCourse;
	private SurfaceCourse surfaceCourse;
	private ObservationsQoS observationsQoS;
	private CrossDrainageWorks crossDrainageWorks;
	private SideDrains sideDrains;
	private CcSemiRigid ccSemiRigid;
	private RoadFurnitureMarkings roadFurnitureMarkings;
	
	public ObservationsQualityOfWork() {
		
	}
	
	public ObservationsQualityOfWork(ObservationsQoWEarthWork observationsQoWEarthWork, BaseCourse baseCourse, SurfaceCourse surfaceCourse,
			ObservationsQoS observationsQoS, CrossDrainageWorks crossDrainageWorks,SideDrains sideDrains,CcSemiRigid ccSemiRigid,RoadFurnitureMarkings roadFurnitureMarkings) {
		super();
		this.observationsQoWEarthWork = observationsQoWEarthWork;
		this.baseCourse = baseCourse;
		this.surfaceCourse = surfaceCourse;
		this.observationsQoS = observationsQoS;
		this.crossDrainageWorks = crossDrainageWorks;
		this.sideDrains = sideDrains;
		this.ccSemiRigid = ccSemiRigid;
		this.roadFurnitureMarkings = roadFurnitureMarkings;
		
	}

	public ObservationsQoWEarthWork getObservationsQoWEarthWork() {
		return observationsQoWEarthWork;
	}

	public void setObservationsQoWEarthWork(ObservationsQoWEarthWork observationsQoWEarthWork) {
		this.observationsQoWEarthWork = observationsQoWEarthWork;
	}

	public BaseCourse getBaseCourse() {
		return baseCourse;
	}

	public void setBaseCourse(BaseCourse baseCourse) {
		this.baseCourse = baseCourse;
	}

	public SurfaceCourse getSurfaceCourse() {
		return surfaceCourse;
	}

	public void setSurfaceCourse(SurfaceCourse surfaceCourse) {
		this.surfaceCourse = surfaceCourse;
	}

	public ObservationsQoS getObservationsQoS() {
		return observationsQoS;
	}

	public void setObservationsQoS(ObservationsQoS observationsQoS) {
		this.observationsQoS = observationsQoS;
	}

	public CrossDrainageWorks getCrossDrainageWorks() {
		return crossDrainageWorks;
	}

	public void setCrossDrainageWorks(CrossDrainageWorks crossDrainageWorks) {
		this.crossDrainageWorks = crossDrainageWorks;
	}

	public SideDrains getSideDrains() {
		return sideDrains;
	}

	public void setSideDrains(SideDrains sideDrains) {
		this.sideDrains = sideDrains;
	}

	public CcSemiRigid getCcSemiRigid() {
		return ccSemiRigid;
	}

	public void setCcSemiRigid(CcSemiRigid ccSemiRigid) {
		this.ccSemiRigid = ccSemiRigid;
	}

	public RoadFurnitureMarkings getRoadFurnitureMarkings() {
		return roadFurnitureMarkings;
	}

	public void setRoadFurnitureMarkings(RoadFurnitureMarkings roadFurnitureMarkings) {
		this.roadFurnitureMarkings = roadFurnitureMarkings;
	}

}
