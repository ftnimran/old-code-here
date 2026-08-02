package com.res.entity.sarovar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QualityofMaterialandWorkmanship {
	
	
	private String itomOfWorkDrain;
    private String materialConfirmstoGrading;
    private String suitableforItemofWork;
    private String provisionsareasperRDnorms;
    private String observedThicknessofLayer;
    private String prescribedThicknessProvided;
    private String mandatorytestsareconducted;
    
public QualityofMaterialandWorkmanship() {
		
	}
	
	
	public QualityofMaterialandWorkmanship(String itomOfWorkDrain, String materialConfirmstoGrading, String suitableforItemofWork,
			String provisionsareasperRDnorms,String observedThicknessofLayer ,String prescribedThicknessProvided,String mandatorytestsareconducted) {
		super();
		this.itomOfWorkDrain = itomOfWorkDrain;
		this.materialConfirmstoGrading = materialConfirmstoGrading;
		this.suitableforItemofWork = suitableforItemofWork;
		this.provisionsareasperRDnorms = provisionsareasperRDnorms;
		this.observedThicknessofLayer = observedThicknessofLayer;
		this.prescribedThicknessProvided = prescribedThicknessProvided;
		this.mandatorytestsareconducted = mandatorytestsareconducted;
		
	}


	public String getItomOfWorkDrain() {
		return itomOfWorkDrain;
	}


	public void setItomOfWorkDrain(String itomOfWorkDrain) {
		this.itomOfWorkDrain = itomOfWorkDrain;
	}


	public String getMaterialConfirmstoGrading() {
		return materialConfirmstoGrading;
	}


	public void setMaterialConfirmstoGrading(String materialConfirmstoGrading) {
		this.materialConfirmstoGrading = materialConfirmstoGrading;
	}


	public String getSuitableforItemofWork() {
		return suitableforItemofWork;
	}


	public void setSuitableforItemofWork(String suitableforItemofWork) {
		this.suitableforItemofWork = suitableforItemofWork;
	}


	public String getProvisionsareasperRDnorms() {
		return provisionsareasperRDnorms;
	}


	public void setProvisionsareasperRDnorms(String provisionsareasperRDnorms) {
		this.provisionsareasperRDnorms = provisionsareasperRDnorms;
	}


	


	public String getPrescribedThicknessProvided() {
		return prescribedThicknessProvided;
	}


	public void setPrescribedThicknessProvided(String prescribedThicknessProvided) {
		this.prescribedThicknessProvided = prescribedThicknessProvided;
	}


	public String getMandatorytestsareconducted() {
		return mandatorytestsareconducted;
	}


	public void setMandatorytestsareconducted(String mandatorytestsareconducted) {
		this.mandatorytestsareconducted = mandatorytestsareconducted;
	}


	public String getObservedThicknessofLayer() {
		return observedThicknessofLayer;
	}


	public void setObservedThicknessofLayer(String observedThicknessofLayer) {
		this.observedThicknessofLayer = observedThicknessofLayer;
	}
	
    
    

}
