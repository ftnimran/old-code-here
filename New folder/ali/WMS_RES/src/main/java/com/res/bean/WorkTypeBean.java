/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.bean;

public class WorkTypeBean{

	private Long workTypeId;

    private String workTypeNameE;

    private String workTypeNameH;
    
    private Short enabled;
    
    public WorkTypeBean() {
    }

    public WorkTypeBean(Long workTypeId) {
        this.workTypeId = workTypeId;
    }

	public Long getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(Long workTypeId) {
		this.workTypeId = workTypeId;
	}

	public String getWorkTypeNameE() {
		return workTypeNameE;
	}

	public void setWorkTypeNameE(String workTypeNameE) {
		this.workTypeNameE = workTypeNameE;
	}

	public String getWorkTypeNameH() {
		return workTypeNameH;
	}

	public void setWorkTypeNameH(String workTypeNameH) {
		this.workTypeNameH = workTypeNameH;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}
}
