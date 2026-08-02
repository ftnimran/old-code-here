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
@Table(name = "MST_AGENCY_TYPE")
public class AgencyType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "AGENCY_TYPE_ID")
    private Long agencyTypeId;

    @Column(name = "AGENCY_TYPE_NAME_E")
    private String agencyTypeNameE;

    @Column(name = "AGENCY_TYPE_NAME_H")
    private String agencyTypeNameH;
    
    @Column(name = "ENABLED")
    private Short enabled;
    
    public AgencyType() {
    }

    public AgencyType(Long agencyTypeId) {
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
