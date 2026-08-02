package com.res.entity.sarovar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExternalDrainageSystem {
	
	private QualityofMaterialandWorkmanship qualityofMaterialandWorkmanship;
	private ExternalDrainageGrading externalDrainageGrading;
	
public ExternalDrainageSystem() {
		
	}
	
	
	public ExternalDrainageSystem(QualityofMaterialandWorkmanship qualityofMaterialandWorkmanship, ExternalDrainageGrading externalDrainageGrading) {
		super();
		this.qualityofMaterialandWorkmanship = qualityofMaterialandWorkmanship;
		this.externalDrainageGrading = externalDrainageGrading;
		
		
	}


	public QualityofMaterialandWorkmanship getQualityofMaterialandWorkmanship() {
		return qualityofMaterialandWorkmanship;
	}


	public void setQualityofMaterialandWorkmanship(QualityofMaterialandWorkmanship qualityofMaterialandWorkmanship) {
		this.qualityofMaterialandWorkmanship = qualityofMaterialandWorkmanship;
	}


	public ExternalDrainageGrading getExternalDrainageGrading() {
		return externalDrainageGrading;
	}


	public void setExternalDrainageGrading(ExternalDrainageGrading externalDrainageGrading) {
		this.externalDrainageGrading = externalDrainageGrading;
	}

}
