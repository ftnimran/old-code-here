/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.bean;

public class DesignationBean {

    private Long id;

    private String designation;
    
    private String measuredByDesignation;
    
    private String designationH;

    private Short enabled;
    
    public DesignationBean() {
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

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDesignationH() {
		return designationH;
	}

	public void setDesignationH(String designationH) {
		this.designationH = designationH;
	}

	public String getMeasuredByDesignation() {
		return measuredByDesignation;
	}

	public void setMeasuredByDesignation(String measuredByDesignation) {
		this.measuredByDesignation = measuredByDesignation;
	}
}
