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
@Table(name = "MST_TECHNICAL_SANCTION_TYPE")
public class TechnicalSanctionType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TECHNICAL_SANCTION_TYPE_ID")
    private Long technicalSanctionTypeId;

    @Column(name = "TECHNICAL_SANCTION_TYPE")
    private String technicalSanctionType;

    @Column(name = "TECHNICAL_SANCTION_TYPE_H")
    private String technicalSanctionTypeH;
    
    @Column(name = "ENABLED")
    private Short enabled;
    
    public TechnicalSanctionType() {
    }

    public TechnicalSanctionType(Long technicalSanctionTypeId) {
        this.technicalSanctionTypeId = technicalSanctionTypeId;
    }

	public Long getTechnicalSanctionTypeId() {
		return technicalSanctionTypeId;
	}

	public void setTechnicalSanctionTypeId(Long technicalSanctionTypeId) {
		this.technicalSanctionTypeId = technicalSanctionTypeId;
	}

	public String getTechnicalSanctionType() {
		return technicalSanctionType;
	}

	public void setTechnicalSanctionType(String technicalSanctionType) {
		this.technicalSanctionType = technicalSanctionType;
	}

	public String getTechnicalSanctionTypeH() {
		return technicalSanctionTypeH;
	}

	public void setTechnicalSanctionTypeH(String technicalSanctionTypeH) {
		this.technicalSanctionTypeH = technicalSanctionTypeH;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}
    
}
