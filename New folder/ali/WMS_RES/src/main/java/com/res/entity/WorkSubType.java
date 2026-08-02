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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MST_WORK_SUB_TYPE")
public class WorkSubType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "WORK_SUB_TYPE_ID")
    private Long workSubTypeId;

    @Column(name = "WORK_SUB_TYPE_NAME_E")
    private String workSubTypeNameE;

    @Column(name = "WORK_SUB_TYPE_NAME_H")
    private String workSubTypeNameH;
    
    @Column(name = "ENABLED")
    private Short enabled;
    
    @JoinColumn(name = "WORK_TYPE_ID", referencedColumnName = "WORK_TYPE_ID")
	@ManyToOne
	private WorkType workType;
    
    public WorkSubType() {
    }

	public WorkSubType(Long workSubTypeId) {
		this.workSubTypeId = workSubTypeId;
	}

	public Long getWorkSubTypeId() {
		return workSubTypeId;
	}

	public void setWorkSubTypeId(Long workSubTypeId) {
		this.workSubTypeId = workSubTypeId;
	}

	public String getWorkSubTypeNameE() {
		return workSubTypeNameE;
	}

	public void setWorkSubTypeNameE(String workSubTypeNameE) {
		this.workSubTypeNameE = workSubTypeNameE;
	}

	public String getWorkSubTypeNameH() {
		return workSubTypeNameH;
	}

	public void setWorkSubTypeNameH(String workSubTypeNameH) {
		this.workSubTypeNameH = workSubTypeNameH;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	public WorkType getWorkType() {
		return workType;
	}

	public void setWorkType(WorkType workType) {
		this.workType = workType;
	}
    
}
