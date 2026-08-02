package com.res.entity.sarovar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExternalDrainageGrading {
	
	private String externalDrainageGrad;
    private String externalDrainageReasons;
    
    
    
public ExternalDrainageGrading() {
		
	}
	
	
	public ExternalDrainageGrading(String externalDrainageGrad, String externalDrainageReasons) {
		super();
		this.externalDrainageGrad = externalDrainageGrad;
		this.externalDrainageReasons = externalDrainageReasons;
		
		
	}


	public String getExternalDrainageGrad() {
		return externalDrainageGrad;
	}


	public void setExternalDrainageGrad(String externalDrainageGrad) {
		this.externalDrainageGrad = externalDrainageGrad;
	}


	public String getExternalDrainageReasons() {
		return externalDrainageReasons;
	}


	public void setExternalDrainageReasons(String externalDrainageReasons) {
		this.externalDrainageReasons = externalDrainageReasons;
	}

}
