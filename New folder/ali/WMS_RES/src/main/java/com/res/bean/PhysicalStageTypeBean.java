/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.bean;

public class PhysicalStageTypeBean{

	private Long physicalStageId;

    private String physicalStageNameE;

    private String physicalStageNameH;
    
    private Short enabled;
    
    private Integer order;
    
    public PhysicalStageTypeBean() {
    }

    public PhysicalStageTypeBean(Long physicalStageId) {
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
    
    
}
