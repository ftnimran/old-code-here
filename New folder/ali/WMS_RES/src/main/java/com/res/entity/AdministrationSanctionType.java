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
@Table(name = "MST_ADMINISTRATION_SANCTION_TYPE")
public class AdministrationSanctionType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ADMINISTRATION_SANCTION_TYPE_ID")
    private Long administrationSanctionTypeId;

    @Column(name = "ADMINISTRATION_SANCTION_TYPE")
    private String administrationSanctionType;

    @Column(name = "ADMINISTRATION_SANCTION_TYPE_H")
    private String administrationSanctionTypeH;
    
    @Column(name = "ENABLED")
    private Short enabled;
    
    public AdministrationSanctionType() {
    }

    public AdministrationSanctionType(Long administrationSanctionTypeId) {
        this.administrationSanctionTypeId = administrationSanctionTypeId;
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
