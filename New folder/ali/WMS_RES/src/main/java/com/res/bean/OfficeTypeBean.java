/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.bean;

public class OfficeTypeBean {

	private Long id;

    private String officeType;

    private String officeTypeH;
    
    private Short enabled;
    
    public OfficeTypeBean() {
    }

	public Short getEnabled() {
        return enabled;
    }

    public void setEnabled(Short enabled) {
        this.enabled = enabled;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOfficeType() {
		return officeType;
	}

	public void setOfficeType(String officeType) {
		this.officeType = officeType;
	}

	public String getOfficeTypeH() {
		return officeTypeH;
	}

	public void setOfficeTypeH(String officeTypeH) {
		this.officeTypeH = officeTypeH;
	}
}
