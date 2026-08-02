/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.bean;

public class AdministrationSanctionTypeBean{

	private Long administrationSanctionTypeId;

    private String administrationSanctionType;

    private String administrationSanctionTypeH;
    
    private Short enabled;
    
    public AdministrationSanctionTypeBean() {
    }

	public Long getAdministrationSanctionTypeId() {
		return administrationSanctionTypeId;
	}

	public void setAdministrationSanctionTypeId(Long administrationSanctionTypeId) {
		this.administrationSanctionTypeId = administrationSanctionTypeId;
	}

	public String getAdministrationSanctionType() {
		return administrationSanctionType;
	}

	public void setAdministrationSanctionType(String administrationSanctionType) {
		this.administrationSanctionType = administrationSanctionType;
	}

	public String getAdministrationSanctionTypeH() {
		return administrationSanctionTypeH;
	}

	public void setAdministrationSanctionTypeH(String administrationSanctionTypeH) {
		this.administrationSanctionTypeH = administrationSanctionTypeH;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}
}
