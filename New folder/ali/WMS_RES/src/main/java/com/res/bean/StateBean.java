/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.bean;

public class StateBean{

	private Long stateId;

    private String stateName;

    private String stateNameH;
    
    private Short enabled;
    
    public StateBean() {
    }

    public StateBean(Long stateId) {
        this.stateId = stateId;
    }

    public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateNameH() {
		return stateNameH;
	}

	public void setStateNameH(String stateNameH) {
		this.stateNameH = stateNameH;
	}
    
    public Short getEnabled() {
        return enabled;
    }

    public void setEnabled(Short enabled) {
        this.enabled = enabled;
    }
}
