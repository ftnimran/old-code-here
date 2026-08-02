/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.bean;

public class WorkNatureBean{

	private Long workNatureId;

    private String workNatureNameE;

    private String workNatureNameH;
    
    private Short enabled;
    
    public WorkNatureBean() {
    }

   

	public Long getWorkNatureId() {
		return workNatureId;
	}



	public void setWorkNatureId(Long workNatureId) {
		this.workNatureId = workNatureId;
	}



	public String getWorkNatureNameE() {
		return workNatureNameE;
	}



	public void setWorkNatureNameE(String workNatureNameE) {
		this.workNatureNameE = workNatureNameE;
	}



	public String getWorkNatureNameH() {
		return workNatureNameH;
	}



	public void setWorkNatureNameH(String workNatureNameH) {
		this.workNatureNameH = workNatureNameH;
	}



	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}
}
