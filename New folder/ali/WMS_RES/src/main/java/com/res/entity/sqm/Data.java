package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

import com.res.entity.sadak.Group1;
import com.res.entity.sadak.Group2;

@XmlRootElement
public class Data {
	private Group1 group1;
	private Group2 group2;
	private Meta meta;
	private SettingOutAndWorking settingOutAndWorking;
	private SiteClearanceAndGrubbing siteClearanceAndGrubbing;
	private QualityArrangements qualityArrangements;
	private Geometrics geometrics;
	private ObservationsQualityOfWork observationsQualityOfWork;
	private GeneralObsSQM generalObsSQM;
	private String otherObservations;
	private String stageOfWork;
	private Group10 group10;
	private Group11 group11;
	private OverallObservation overallObservation;
	
	
	public Data() {
		
	}
	
	
	public Data( Group1 group1,Group2 group2,Meta meta,SettingOutAndWorking settingOutAndWorking, SiteClearanceAndGrubbing siteClearanceAndGrubbing, QualityArrangements qualityArrangements,
			Geometrics geometrics, ObservationsQualityOfWork observationsQualityOfWork, GeneralObsSQM generalObsSQM,String otherObservations,String stageOfWork,Group10 group10,Group11 group11
			,OverallObservation overallObservation) {
		super();
		this.group1 = group1;
		this.group2 = group2;
		this.meta = meta;
		this.settingOutAndWorking = settingOutAndWorking;
		this.siteClearanceAndGrubbing = siteClearanceAndGrubbing;
		this.qualityArrangements = qualityArrangements;
		this.geometrics = geometrics;
		this.observationsQualityOfWork = observationsQualityOfWork;
		this.generalObsSQM = generalObsSQM;
		this.otherObservations = otherObservations;
		this.stageOfWork = stageOfWork;
		this.group10=group10;
		this.group11=group11;
		this.overallObservation=overallObservation;
		
	}
	
	public String getStageOfWork() {
		return stageOfWork;
	}


	public void setStageOfWork(String stageOfWork) {
		this.stageOfWork = stageOfWork;
	}


	public Group1 getGroup1() {
		return group1;
	}


	public void setGroup1(Group1 group1) {
		this.group1 = group1;
	}


	public Group2 getGroup2() {
		return group2;
	}


	public void setGroup2(Group2 group2) {
		this.group2 = group2;
	}


	public Meta getMeta() {
		return meta;
	}
	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	public SettingOutAndWorking getSettingOutAndWorking() {
		return settingOutAndWorking;
	}
	public void setSettingOutAndWorking(SettingOutAndWorking settingOutAndWorking) {
		this.settingOutAndWorking = settingOutAndWorking;
	}
	public SiteClearanceAndGrubbing getSiteClearanceAndGrubbing() {
		return siteClearanceAndGrubbing;
	}
	public void setSiteClearanceAndGrubbing(SiteClearanceAndGrubbing siteClearanceAndGrubbing) {
		this.siteClearanceAndGrubbing = siteClearanceAndGrubbing;
	}
	public QualityArrangements getQualityArrangements() {
		return qualityArrangements;
	}
	public void setQualityArrangements(QualityArrangements qualityArrangements) {
		this.qualityArrangements = qualityArrangements;
	}
	public Geometrics getGeometrics() {
		return geometrics;
	}
	public void setGeometrics(Geometrics geometrics) {
		this.geometrics = geometrics;
	}
	public ObservationsQualityOfWork getObservationsQualityOfWork() {
		return observationsQualityOfWork;
	}
	public void setObservationsQualityOfWork(ObservationsQualityOfWork observationsQualityOfWork) {
		this.observationsQualityOfWork = observationsQualityOfWork;
	}
	public GeneralObsSQM getGeneralObsSQM() {
		return generalObsSQM;
	}
	public void setGeneralObsSQM(GeneralObsSQM generalObsSQM) {
		this.generalObsSQM = generalObsSQM;
	}
	public String getOtherObservations() {
		return otherObservations;
	}
	public void setOtherObservations(String otherObservations) {
		this.otherObservations = otherObservations;
	}


	public Group10 getGroup10() {
		return group10;
	}


	public void setGroup10(Group10 group10) {
		this.group10 = group10;
	}


	public Group11 getGroup11() {
		return group11;
	}


	public void setGroup11(Group11 group11) {
		this.group11 = group11;
	}


	public OverallObservation getOverallObservation() {
		return overallObservation;
	}


	public void setOverallObservation(OverallObservation overallObservation) {
		this.overallObservation = overallObservation;
	}
	
	
	

}
