package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CcSemiRigidObservations {
	
	private String ccSemiRigidReference;
	private String ccSemiRigidRD;
	private String ccSemiRigidThickness;
	private String ccSemiRigidThicknessAcceptable;
	private String ccSemiRigidGeneralMaterialAcceptable;
	private String ccSemiRigidGeneralWorkmanshipAcceptable;
    
    public CcSemiRigidObservations() {
    	
    }
    
    public CcSemiRigidObservations(String ccSemiRigidReference, String ccSemiRigidRD, String ccSemiRigidThickness,
			String ccSemiRigidThicknessAcceptable, String ccSemiRigidGeneralMaterialAcceptable,String ccSemiRigidGeneralWorkmanshipAcceptable) {
		super();
		this.ccSemiRigidReference = ccSemiRigidReference;
		this.ccSemiRigidRD = ccSemiRigidRD;
		this.ccSemiRigidThickness = ccSemiRigidThickness;
		this.ccSemiRigidThicknessAcceptable = ccSemiRigidThicknessAcceptable;
		this.ccSemiRigidGeneralMaterialAcceptable = ccSemiRigidGeneralMaterialAcceptable;
		this.ccSemiRigidGeneralWorkmanshipAcceptable = ccSemiRigidGeneralWorkmanshipAcceptable;

		
	}

	public String getCcSemiRigidReference() {
		return ccSemiRigidReference;
	}

	public void setCcSemiRigidReference(String ccSemiRigidReference) {
		this.ccSemiRigidReference = ccSemiRigidReference;
	}

	public String getCcSemiRigidRD() {
		return ccSemiRigidRD;
	}

	public void setCcSemiRigidRD(String ccSemiRigidRD) {
		this.ccSemiRigidRD = ccSemiRigidRD;
	}

	
	

	public String getCcSemiRigidThicknessAcceptable() {
		return ccSemiRigidThicknessAcceptable;
	}

	public void setCcSemiRigidThicknessAcceptable(String ccSemiRigidThicknessAcceptable) {
		this.ccSemiRigidThicknessAcceptable = ccSemiRigidThicknessAcceptable;
	}

	public String getCcSemiRigidGeneralMaterialAcceptable() {
		return ccSemiRigidGeneralMaterialAcceptable;
	}

	public void setCcSemiRigidGeneralMaterialAcceptable(String ccSemiRigidGeneralMaterialAcceptable) {
		this.ccSemiRigidGeneralMaterialAcceptable = ccSemiRigidGeneralMaterialAcceptable;
	}

	public String getCcSemiRigidGeneralWorkmanshipAcceptable() {
		return ccSemiRigidGeneralWorkmanshipAcceptable;
	}

	public void setCcSemiRigidGeneralWorkmanshipAcceptable(String ccSemiRigidGeneralWorkmanshipAcceptable) {
		this.ccSemiRigidGeneralWorkmanshipAcceptable = ccSemiRigidGeneralWorkmanshipAcceptable;
	}

	public String getCcSemiRigidThickness() {
		return ccSemiRigidThickness;
	}

	public void setCcSemiRigidThickness(String ccSemiRigidThickness) {
		this.ccSemiRigidThickness = ccSemiRigidThickness;
	}

}
