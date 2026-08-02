package com.res.entity.sqm;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CcSemiRigid {
	private CcSemiRigidObservations ccSemiRigidObservations;
	private CcSemiRigidGrading ccSemiRigidGrading;
	
	public CcSemiRigid() {
		
	}
	
	public CcSemiRigid(CcSemiRigidObservations ccSemiRigidObservations, CcSemiRigidGrading ccSemiRigidGrading) {
		super();
		this.ccSemiRigidObservations = ccSemiRigidObservations;
		this.ccSemiRigidGrading = ccSemiRigidGrading;
		
	}

	public CcSemiRigidObservations getCcSemiRigidObservations() {
		return ccSemiRigidObservations;
	}

	public void setCcSemiRigidObservations(CcSemiRigidObservations ccSemiRigidObservations) {
		this.ccSemiRigidObservations = ccSemiRigidObservations;
	}

	public CcSemiRigidGrading getCcSemiRigidGrading() {
		return ccSemiRigidGrading;
	}

	public void setCcSemiRigidGrading(CcSemiRigidGrading ccSemiRigidGrading) {
		this.ccSemiRigidGrading = ccSemiRigidGrading;
	}

	
}
