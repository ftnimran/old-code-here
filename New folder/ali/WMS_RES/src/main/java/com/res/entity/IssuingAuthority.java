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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MST_ISSUING_AUTHORITY")
public class IssuingAuthority implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long issuingAuthorityId;

    @Column(name = "ISSUING_AUTHORITY_NAME")
    private String issuingAuthorityName;

    @Column(name = "ISSUING_AUTHORITY_NAME_H")
    private String issuingAuthorityNameH;
    
    @Column(name = "ENABLED")
    private Short enabled;
    
    public IssuingAuthority() {
    }

    public IssuingAuthority(Long issuingAuthorityId) {
        this.issuingAuthorityId = issuingAuthorityId;
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
