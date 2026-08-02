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
@Table(name = "MST_Financial_STAGE")
public class FinancialStageType implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "Financial_STAGE_ID")
	private Long financialStageId;

	@Column(name = "Financial_STAGE_NAME_E")
	private String financialStageNameE;

	@Column(name = "Financial_STAGE_NAME_H")
	private String financialStageNameH;

	@Column(name = "ENABLED")
	private Short enabled;

	@Column(name = "ORDERING")
	private Integer order;

	public FinancialStageType() {
	}

    public FinancialStageType(Long financialStageId) {
        this.financialStageId = financialStageId;
    }
	
	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Long getFinancialStageId() {
		return financialStageId;
	}

	public void setFinancialStageId(Long financialStageId) {
		this.financialStageId = financialStageId;
	}

	public String getFinancialStageNameE() {
		return financialStageNameE;
	}

	public void setFinancialStageNameE(String financialStageNameE) {
		this.financialStageNameE = financialStageNameE;
	}

	public String getFinancialStageNameH() {
		return financialStageNameH;
	}

	public void setFinancialStageNameH(String financialStageNameH) {
		this.financialStageNameH = financialStageNameH;
	}

}