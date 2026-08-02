/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.bean;

public class AgencyTypeBean{

	private Long agencyTypeId;

    private String agencyTypeNameE;

    private String agencyTypeNameH;
    
    private Short enabled;
    
    public AgencyTypeBean() {
    }

    public AgencyTypeBean(Long agencyTypeId) {
        this.agencyTypeId = agencyTypeId;
    }

	public Long getAgencyTypeId() {
		return agencyTypeId;
	}

	public void setAgencyTypeId(Long agencyTypeId) {
		this.agencyTypeId = agencyTypeId;
	}

	public String getAgencyTypeNameE() {
		return agencyTypeNameE;
	}

	public void setAgencyTypeNameE(String agencyTypeNameE) {
		this.agencyTypeNameE = agencyTypeNameE;
	}

	public String getAgencyTypeNameH() {
		return agencyTypeNameH;
	}

	public void setAgencyTypeNameH(String agencyTypeNameH) {
		this.agencyTypeNameH = agencyTypeNameH;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}
}
