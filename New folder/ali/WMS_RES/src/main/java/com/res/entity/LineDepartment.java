/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MST_LINE_DEPARTMENT")
public class LineDepartment extends Auditable implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LINE_DEPARTMENT_ID")
    private Long lineDepartmentId;

    @Column(name = "LINE_DEPARTMENT_NAME_E")
    private String lineDepartmentNameE;

    @Column(name = "LINE_DEPARTMENT_NAME_H")
    private String lineDepartmentNameH;
    
    @Column(name = "ENABLED")
    private Short enabled;
    
    public LineDepartment() {
    }

    public LineDepartment(Long lineDepartmentId) {
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
}
