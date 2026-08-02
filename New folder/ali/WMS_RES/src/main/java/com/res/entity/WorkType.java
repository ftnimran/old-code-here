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
@Table(name = "mst_work_type")
public class WorkType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "WORK_TYPE_ID")
    private Long workTypeId;

    @Column(name = "WORK_TYPE_NAME_E")
    private String workTypeNameE;

    @Column(name = "WORK_TYPE_NAME_H")
    private String workTypeNameH;
    
    @Column(name = "ENABLED")
    private Short enabled;
    
    public WorkType() {
    }

    public WorkType(Long workTypeId) {
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
