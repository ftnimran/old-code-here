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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MST_OFFICE_TYPES")
public class OfficeType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;

    public OfficeType(Long id) {
		super();
		this.id = id;
	}

	@Column(name = "OFFICE_TYPE")
    private String officeType;

    @Column(name = "OFFICE_TYPE_H")
    private String officeTypeH;
    
    @Column(name = "ENABLED")
    private Short enabled;
    
    public OfficeType() {
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
