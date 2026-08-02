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
@Table(name = "MST_WORK_NATURE")
public class WorkNature implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "WORK_NATURE_ID")
    private Long workNatureId;

    @Column(name = "WORK_NATURE_NAME_E")
    private String workNatureNameE;

    @Column(name = "WORK_NATURE_NAME_H")
    private String workNatureNameH;
    
    @Column(name = "ENABLED")
    private Short enabled;
    
   

	public WorkNature() {
	
	}
	public WorkNature(Long workNatureId) {
        this.workNatureId = workNatureId;
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
