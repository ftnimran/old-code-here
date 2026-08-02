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
@Table(name = "MST_FINANCIAL_YEAR")
public class FinancialYear implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;

    @Column(name = "FINANCIAL_YEAR")
    private String financialYear;
    
    @Column(name = "ENABLED")
    private Short enabled;
    
    @Column(name = "FINANC_YEAR")
    private String financYear;
    
    public FinancialYear() {
    }
    
    public FinancialYear(Long Id) {
    	this.id=Id;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	public String getFinancYear() {
		return financYear;
	}

	public void setFinancYear(String financYear) {
		this.financYear = financYear;
	}
    
}
