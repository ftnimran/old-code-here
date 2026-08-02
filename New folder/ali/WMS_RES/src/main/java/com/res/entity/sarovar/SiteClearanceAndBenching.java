package com.res.entity.sarovar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SiteClearanceAndBenching {
	
	private String clearingAndBenchingDone;
	private String materialAvailableReused;
	private String nameReusableMaterial;
    private SiteClearanceGrading siteClearanceGrading;
    
public SiteClearanceAndBenching() {
		
	}
	
	
	public SiteClearanceAndBenching(String clearingAndBenchingDone, String materialAvailableReused, String nameReusableMaterial,
			SiteClearanceGrading siteClearanceGrading) {
		super();
		this.clearingAndBenchingDone = clearingAndBenchingDone;
		this.materialAvailableReused = materialAvailableReused;
		this.nameReusableMaterial = nameReusableMaterial;
		this.siteClearanceGrading = siteClearanceGrading;
		
		
	}
    
    
	public String getClearingAndBenchingDone() {
		return clearingAndBenchingDone;
	}
	public void setClearingAndBenchingDone(String clearingAndBenchingDone) {
		this.clearingAndBenchingDone = clearingAndBenchingDone;
	}
	public String getMaterialAvailableReused() {
		return materialAvailableReused;
	}
	public void setMaterialAvailableReused(String materialAvailableReused) {
		this.materialAvailableReused = materialAvailableReused;
	}
	public String getNameReusableMaterial() {
		return nameReusableMaterial;
	}
	public void setNameReusableMaterial(String nameReusableMaterial) {
		this.nameReusableMaterial = nameReusableMaterial;
	}
	public SiteClearanceGrading getSiteClearanceGrading() {
		return siteClearanceGrading;
	}
	public void setSiteClearanceGrading(SiteClearanceGrading siteClearanceGrading) {
		this.siteClearanceGrading = siteClearanceGrading;
	}
      

}
