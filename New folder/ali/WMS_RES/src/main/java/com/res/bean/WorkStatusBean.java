/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.bean;

public class WorkStatusBean{

	private Long workStatusid;

    private String workStatusNameE;

    private String workStatusNameH;
    
    private Short enabled;
    
    public WorkStatusBean() {
    }
    
	public Long getWorkStatusid() {
		return workStatusid;
	}
	
	public void setWorkStatusid(Long workStatusid) {
		this.workStatusid = workStatusid;
	}

	public String getWorkStatusNameE() {
		return workStatusNameE;
	}

	public void setWorkStatusNameE(String workStatusNameE) {
		this.workStatusNameE = workStatusNameE;
	}

	public String getWorkStatusNameH() {
		return workStatusNameH;
	}

	public void setWorkStatusNameH(String workStatusNameH) {
		this.workStatusNameH = workStatusNameH;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}
}
