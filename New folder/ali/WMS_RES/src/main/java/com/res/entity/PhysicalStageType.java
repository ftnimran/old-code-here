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
@Table(name = "MST_PHYSICAL_STAGE")
public class PhysicalStageType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PHYSICAL_STAGE_ID")
    private Long physicalStageId;

    @Column(name = "PHYSICAL_STAGE_NAME_E")
    private String physicalStageNameE;

    @Column(name = "PHYSICAL_STAGE_NAME_H")
    private String physicalStageNameH;
    
    @Column(name = "ENABLED")
    private Short enabled;
    
    @Column(name = "ORDERING")
	private Integer order;
    
    @ManyToOne
    @JoinColumn(name="WORK_TYPE_ID",referencedColumnName="WORK_TYPE_ID")
	private WorkType workType;
    
    public PhysicalStageType() {
    }

    public PhysicalStageType(Long physicalStageId) {
        this.physicalStageId = physicalStageId;
    }

	public Long getPhysicalStageId() {
		return physicalStageId;
	}

	public void setPhysicalStageId(Long physicalStageId) {
		this.physicalStageId = physicalStageId;
	}

	public String getPhysicalStageNameE() {
		return physicalStageNameE;
	}

	public void setPhysicalStageNameE(String physicalStageNameE) {
		this.physicalStageNameE = physicalStageNameE;
	}

	public String getPhysicalStageNameH() {
		return physicalStageNameH;
	}

	public void setPhysicalStageNameH(String physicalStageNameH) {
		this.physicalStageNameH = physicalStageNameH;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public WorkType getWorkType() {
		return workType;
	}

	public void setWorkType(WorkType workType) {
		this.workType = workType;
	}
}
