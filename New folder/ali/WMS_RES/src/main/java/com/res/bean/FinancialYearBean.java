/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.res.bean;

public class FinancialYearBean{

	private Long financialYearId;

    private String financialYearNameE;

    private String financialYearNameH;
    
    private String financYear;
    
    private Short enabled;
    
    public FinancialYearBean() {
    }
    
    public FinancialYearBean(Long financialYearId) {
    	this.financialYearId=financialYearId;
    }

	public Long getFinancialYearId() {
		return financialYearId;
	}

	public void setFinancialYearId(Long financialYearId) {
		this.financialYearId = financialYearId;
	}

	public String getFinancialYearNameE() {
		return financialYearNameE;
	}

	public void setFinancialYearNameE(String financialYearNameE) {
		this.financialYearNameE = financialYearNameE;
	}

	public String getFinancialYearNameH() {
		return financialYearNameH;
	}

	public void setFinancialYearNameH(String financialYearNameH) {
		this.financialYearNameH = financialYearNameH;
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
