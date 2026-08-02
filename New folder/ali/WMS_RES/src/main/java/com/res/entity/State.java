/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MST_STATE")
public class State implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATE_ID")
    private Long stateId;

    @Column(name = "STATE_NAME")
    private String stateName;

    @Column(name = "STATE_NAME_H")
    private String stateNameH;
    
    @Column(name = "ENABLED")
    private Short enabled;
    
    public State() {
    }

    public State(Long stateId) {
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
