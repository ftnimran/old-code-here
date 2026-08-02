package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SiteClearanceAndGrubbing {
	
	 private String clearingAndGrubbingDone;
	 private String materialAvailableReused;
	 private String nameReusableMaterial;
	 private SiteClearanceGrading siteClearanceGrading;
	 
	 public SiteClearanceAndGrubbing() {
		 
	 }
	 
	 
	 public SiteClearanceAndGrubbing(String clearingAndGrubbingDone, String materialAvailableReused, String nameReusableMaterial,
			 SiteClearanceGrading siteClearanceGrading) {
			super();
			this.clearingAndGrubbingDone = clearingAndGrubbingDone;
			this.materialAvailableReused = materialAvailableReused;
			this.nameReusableMaterial = nameReusableMaterial;
			this.siteClearanceGrading = siteClearanceGrading;
			
		}
	 
	 
	 
	 public String getClearingAndGrubbingDone() {
		return clearingAndGrubbingDone;
	}
	public void setClearingAndGrubbingDone(String clearingAndGrubbingDone) {
		this.clearingAndGrubbingDone = clearingAndGrubbingDone;
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
