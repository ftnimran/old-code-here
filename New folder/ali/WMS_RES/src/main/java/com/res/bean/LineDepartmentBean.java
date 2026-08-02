/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.bean;

public class LineDepartmentBean{

	private Long lineDepartmentId;

    private String lineDepartmentNameE;

    private String lineDepartmentNameH;
    
    private Short enabled;
    
	private Integer index;
	
    public LineDepartmentBean() {
    }

    public LineDepartmentBean(Long lineDepartmentId) {
        this.lineDepartmentId = lineDepartmentId;
    }

	public Long getLineDepartmentId() {
		return lineDepartmentId;
	}

	public void setLineDepartmentId(Long lineDepartmentId) {
		this.lineDepartmentId = lineDepartmentId;
	}

	public String getLineDepartmentNameE() {
		return lineDepartmentNameE;
	}

	public void setLineDepartmentNameE(String lineDepartmentNameE) {
		this.lineDepartmentNameE = lineDepartmentNameE;
	}

	public String getLineDepartmentNameH() {
		return lineDepartmentNameH;
	}

	public void setLineDepartmentNameH(String lineDepartmentNameH) {
		this.lineDepartmentNameH = lineDepartmentNameH;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
}
