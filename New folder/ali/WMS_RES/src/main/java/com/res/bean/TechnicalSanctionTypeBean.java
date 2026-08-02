/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.bean;

public class TechnicalSanctionTypeBean{

	private Long technicalSanctionTypeId;

    private String technicalSanctionType;

    private String technicalSanctionTypeH;
    
    private Short enabled;
    
    public TechnicalSanctionTypeBean() {
    }

    public TechnicalSanctionTypeBean(Long technicalSanctionTypeId) {
        this.technicalSanctionTypeId = technicalSanctionTypeId;
    }

	public Long getTechnicalSanctionTypeId() {
		return technicalSanctionTypeId;
	}

	public void setTechnicalSanctionTypeId(Long technicalSanctionTypeId) {
		this.technicalSanctionTypeId = technicalSanctionTypeId;
	}

	public String getTechnicalSanctionType() {
		return technicalSanctionType;
	}

	public void setTechnicalSanctionType(String technicalSanctionType) {
		this.technicalSanctionType = technicalSanctionType;
	}

	public String getTechnicalSanctionTypeH() {
		return technicalSanctionTypeH;
	}

	public void setTechnicalSanctionTypeH(String technicalSanctionTypeH) {
		this.technicalSanctionTypeH = technicalSanctionTypeH;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	
}
