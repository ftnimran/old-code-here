package com.res.entity.sarovar;
import javax.xml.bind.annotation.XmlRootElement;
import com.res.entity.sadak.Group1;
import com.res.entity.sadak.Group2;
import com.res.entity.sqm.Group10;
import com.res.entity.sqm.Group11;
import com.res.entity.sqm.OverallObservation;
import com.res.entity.sqm.QualityArrangements;

@XmlRootElement
public class Data {
	private Group1 group1;
	private Group2 group2;
    private String siteSelection;
    private String earthenDamHeight;
    private String whetherStabilityAnalysisIsDone;
	private Meta meta;
	private SettingOut settingOut;
	private SiteClearanceAndBenching siteClearanceAndBenching;
	private QualityArrangements qualityArrangements;
	private ObservationsQualityOfWork observationsQualityOfWork;
	private GeneralObsSQM generalObsSQM;
	private String otherObservations;
	private String stageOfWork;
	private Group10 group10;
	private Group11 group11;
	private OverallObservation overallObservation;
	
	
	
	
	public Data() {
		
	}
	
	
	public Data( Group1 group1,Group2 group2,String siteSelection,String earthenDamHeight, String whetherStabilityAnalysisIsDone, Meta meta,
			SettingOut settingOut, SiteClearanceAndBenching siteClearanceAndBenching, QualityArrangements qualityArrangements,ObservationsQualityOfWork observationsQualityOfWork,GeneralObsSQM generalObsSQM2
	,String otherObservations,
	String stageOfWork,Group10 group10,Group11 group11,OverallObservation overallObservation) {
		super();
		this.group1 = group1;
		this.group2 = group2;
		this.siteSelection = siteSelection;
		this.earthenDamHeight = earthenDamHeight;
		this.whetherStabilityAnalysisIsDone = whetherStabilityAnalysisIsDone;
		this.meta = meta;
		this.settingOut = settingOut;
		this.siteClearanceAndBenching = siteClearanceAndBenching;
		this.qualityArrangements = qualityArrangements;
		this.observationsQualityOfWork = observationsQualityOfWork;
		this.generalObsSQM = generalObsSQM2;
		this.otherObservations = otherObservations;
		this.stageOfWork = stageOfWork;
		this.group10 = group10;
		this.group11 = group11;
		this.overallObservation = overallObservation;
		
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


	public String getSiteSelection() {
		return siteSelection;
	}


	public void setSiteSelection(String siteSelection) {
		this.siteSelection = siteSelection;
	}


	


	public String getWhetherStabilityAnalysisIsDone() {
		return whetherStabilityAnalysisIsDone;
	}


	public void setWhetherStabilityAnalysisIsDone(String whetherStabilityAnalysisIsDone) {
		this.whetherStabilityAnalysisIsDone = whetherStabilityAnalysisIsDone;
	}


	public Meta getMeta() {
		return meta;
	}


	public void setMeta(Meta meta) {
		this.meta = meta;
	}


	public SettingOut getSettingOut() {
		return settingOut;
	}


	public void setSettingOut(SettingOut settingOut) {
		this.settingOut = settingOut;
	}


	public SiteClearanceAndBenching getSiteClearanceAndBenching() {
		return siteClearanceAndBenching;
	}


	public void setSiteClearanceAndBenching(SiteClearanceAndBenching siteClearanceAndBenching) {
		this.siteClearanceAndBenching = siteClearanceAndBenching;
	}


	public QualityArrangements getQualityArrangements() {
		return qualityArrangements;
	}


	public void setQualityArrangements(QualityArrangements qualityArrangements) {
		this.qualityArrangements = qualityArrangements;
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


	public String getStageOfWork() {
		return stageOfWork;
	}


	public void setStageOfWork(String stageOfWork) {
		this.stageOfWork = stageOfWork;
	}


	public String getEarthenDamHeight() {
		return earthenDamHeight;
	}


	public void setEarthenDamHeight(String earthenDamHeight) {
		this.earthenDamHeight = earthenDamHeight;
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
