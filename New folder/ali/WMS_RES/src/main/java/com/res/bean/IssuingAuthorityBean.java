/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.bean;

public class IssuingAuthorityBean{

	private Long issuingAuthorityId;

    private String issuingAuthorityName;

    private String issuingAuthorityNameH;
    
    private Short enabled;
    
    public IssuingAuthorityBean() {
    }

	public Long getIssuingAuthorityId() {
		return issuingAuthorityId;
	}

	public void setIssuingAuthorityId(Long issuingAuthorityId) {
		this.issuingAuthorityId = issuingAuthorityId;
	}

	public String getIssuingAuthorityName() {
		return issuingAuthorityName;
	}

	public void setIssuingAuthorityName(String issuingAuthorityName) {
		this.issuingAuthorityName = issuingAuthorityName;
	}

	public String getIssuingAuthorityNameH() {
		return issuingAuthorityNameH;
	}

	public void setIssuingAuthorityNameH(String issuingAuthorityNameH) {
		this.issuingAuthorityNameH = issuingAuthorityNameH;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}
	
}
